package de.thm.mni.microservices.gruppe6

import scala.util.Random
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.jdbc.Predef._

import java.time.LocalDate
import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}

class RunScenarios extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8069")
    .acceptHeader(Map(
      "Content-Type" -> "application/json"
    ))
    .userAgentHeader(
      "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"
    )

  val sessionHeaders = Map(
    "Content-Type" -> "application/json"
  )

  val jwtHeader = Map(
    "Authorization" -> "${authToken}"
  )


  /*
  Scenario base:

  1.
  Peter log in
  Peter erstellt eines user

  2.
  Erstellt Projekt

  3.
  Erstellt Projekt
  Erstellt Issue

  Peter
  GetProjects
  Create User für project
  Add user as member
  Login as user

  Für random project
  mach neues Issue
   */

  var randomString: Iterator[String] = Iterator.continually(Random.alphanumeric.take(Random.between(5,20)).mkString)

  var randomFutureDate: Iterator[String] = Iterator.continually(LocalDate.now()
    .plusDays(Random.nextInt(600))
    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))

  var randomPastDate: Iterator[String] = Iterator.continually(LocalDate.now()
    .minusDays(Random.nextInt(100*365))
    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))

  var randomStringFeeder: Iterator[Map[String, String]] = Iterator.continually(
    Map("randomString" -> randomString.next()))

  var randomFutureDateFeeder: Iterator[Map[String, String]] = Iterator.continually(
    Map("randomFutureDate" -> randomFutureDate.next()))

  var randomPastDateFeeder: Iterator[Map[String, String]] = Iterator.continually(
    Map("randomPastDate" -> randomPastDate.next()))

  def newUserFeeder(role:String): Iterator[Map[String, String]] = {
    Iterator.continually(Map(
      "username" -> randomString.next(),
      "password" -> randomString.next(),
      "name" -> randomString.next(),
      "lastName" -> randomString.next(),
      "email" -> (randomString.next() + "@mni.thm.de"),
      "dateOfBirth" -> randomPastDate.next(),
      "globalRole" -> role
    ))
  }


  object User {
    def login(username: String, password: String): ChainBuilder = {
      exec(
        http("loginUser")
          .get("/login")
          .basicAuth(username,password)
          .check(status.is(200))
          .check(header("authorization").exists)
          .check(
            header("authorization").saveAs("authToken")
          )
      )
    }

    def create(role: String): ChainBuilder = {
      feed(newUserFeeder(role))
      .exec(
        http("createProjectHTTP")
          .post(s"/api/users/")
          .headers(jwtHeader)
          .body(ElFileBody("bodies/userDTO.json")).asJson
          .check(status.is(201))
          .check(bodyString.saveAs("BODY"))
      )
    }
  }
  object Project {
    def create(projectName: String): ChainBuilder = {
      exec(
        http("createProjectHTTP")
          .post(s"/api/projects/${projectName}")
          .headers(jwtHeader)
          .headers(sessionHeaders)
          .check(status.is(201))
      )
    }

  }

  val scenarioLoginPeter: ScenarioBuilder =
    scenario("LoginAsPeter")
    .exec(User.login("Peter","password"))

  val scenarioPeterCreateProject: ScenarioBuilder =
    scenario("CreateProjectAsPeter")
    .exec(User.login("Peter","password"))
    .exec(scenarioLoginPeter, Project.create("Test"))


  /*
  val scn1 = scenario("LoginAsPeter").exec(
    feed(randomProjectNameFeeder)
      .exec(
        http("loginAsPeterHTTP")
          .get("/login")
          .headers(headers_0)
          .basicAuth("Peter_Zwegat","password")
          .check(status.is(200))
          .check(header("authorization").exists)
          .check(
            header("authorization").saveAs("authToken")
          )
      )
      .exec(
        http("createProjectHTTP")
          .post("/api/projects/test")
          .headers(sessionHeaders)
          .check(status.is(201))
      )
  )

   */

  /*val scn1 = scenario("LoginAsGuestAndCreateRoom").exec(
    feed(randomRoomNameFeeder)
      .exec(
        http("loginAsGuest")
          .post("/auth/login/guest")
          .check(status.is(200))
          .check(
            jsonPath("$..details").exists.saveAs("authToken")
          )
          .check(
            jsonPath("$..accountId").exists.saveAs("accountId")
          )
      )
      .exec(
        http("createRoom")
          .post("/room/")
          .headers(sessionHeaders)
          .body(StringBody("""{
            "name": "${randomRoomName}"
          }"""))
          .asJson
          .check(bodyString.saveAs("BODY"))
          .check(
            status.is(200)
          )
          .check(
            jsonPath("$..id").exists.saveAs("roomId")
          )
      )
  )

  val scn2 = scenario("AskQuestionAndUpvote")
    .feed(randomQuestionBody)
    .exec(scn1)
    .exec(
      http("createCommentAsParticipant")
        .post("/comment/")
        .headers(sessionHeaders)
        .body(StringBody("""{
                "roomId": "${roomId}",
                "body": "${randomRoomName}"
            }"""))
        .asJson
        .check(
          status.is(201)
        )
        .check(
          jsonPath("$..id").exists.saveAs("commentId")
        )
    )
    .exec(
      http("upVoteComment")
        .post("/vote/")
        .headers(sessionHeaders)
        .body(StringBody("""{
        "commentId": "${commentId}",
        "vote": "1"
      }"""))
        .check(status.is(200))
    )

   */

  //inject(atOnceUsers(20))
  //      .andThen(scn2.inject(constantUsersPerSec(5).during(1.minute).randomized))
  setUp(
    scenarioLoginPeter
      .inject(atOnceUsers(5))
  )
    .protocols(httpProtocol)
    .assertions(
      global.responseTime.mean.lt(1000),
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(95),
      global.failedRequests.percent.lt(5)
    )

}
