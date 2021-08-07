package de.thm.mni.microservices.gruppe6

import scala.util.Random
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.concurrent.duration.DurationInt

class RunScenarios extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8069")
    .headers(Map(
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

  var peterAuth: (String, String) = ("Peter_Zwegat", "password")

  var randomRole: Iterator[String] = Iterator.continually(List("USER", "SUPPORT", "ADMIN")(Random.nextInt(3)))

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

  def newUserFeeder(role:String, username: String = randomString.next(), password: String = randomString.next()): Iterator[Map[String, String]] = {
    Iterator.continually(Map(
      "username" -> username,
      "password" -> password,
      "name" -> randomString.next(),
      "lastName" -> randomString.next(),
      "email" -> (randomString.next() + "@mni.thm.de"),
      "dateOfBirth" -> randomPastDate.next(),
      "globalRole" -> role
    ))
  }


  object User {

    def login(logins: (String, String)): ChainBuilder  = login(logins._1, logins._2)
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
      ).exitHereIfFailed
    }

    /**
     * saves user ID into field "userId.
     */
    def create(role: String, username: String = randomString.next(), password: String = randomString.next()): ChainBuilder = {
      feed(newUserFeeder(role, username, password))
      .exec(
        http("createProjectHTTP")
          .post(s"/api/users/")
          .headers(jwtHeader)
          .body(ElFileBody("bodies/userDTO.json")).asJson
          .check(status.is(201))
          .check(jsonPath("$.id").saveAs("userId"))
      ).exitHereIfFailed
    }

    /**
     * saves user ID into field "userId.
     */
    def createAndLogin(role: String, delay: Int = 0): ChainBuilder = {
      val username = randomString.next()
      val password = randomString.next()
      User.create(role, username, password)
        .pause(delay)
        .exec(
          http("loginUser")
          .get("/login")
          .basicAuth(username,password)
          .check(status.is(200))
          .check(header("authorization").exists)
          .check(
            header("authorization").saveAs("authToken")
          )
        ).exitHereIfFailed
    }

    /**
     * Saves all Project-ID's into "projectList"
     */
    val getAll: ChainBuilder =
      exec(
        http("getAllUsersHTTP")
          .get(s"/api/users/")
          .headers(jwtHeader)
          .headers(sessionHeaders)
          .check(status.is(200))
          .check(jsonPath("$[*].id").findAll.saveAs("userList")))
        .exitHereIfFailed
  }

  object Project {
    /**
     * saves projectId into "projectId".
     * saves projectName into "projectName".
     */
    def create(projectName: String = randomString.next()): ChainBuilder = {
      exec(
        http("createProjectHTTP")
          .post(s"/api/projects/${projectName}")
          .headers(jwtHeader)
          .headers(sessionHeaders)
          .check(status.is(201))
          .check(jsonPath("$.id").saveAs("projectId"))
          .check(jsonPath("$.name").saveAs("projectName"))
      ).exitHereIfFailed
    }

    /**
     * Saves all Project-ID's into "projectList"
     */
    val getAll: ChainBuilder =
      exec(
        http("getAllProjectsHTTP")
          .get(s"/api/projects/")
          .headers(jwtHeader)
          .headers(sessionHeaders)
          .check(status.is(200))
          .check(jsonPath("$[*].id").findAll.saveAs("projectList")))
                            //.transform(productIds => util.Random.shuffle(productIds)).saveAs("productIds"))
        .exitHereIfFailed

    def addMember(projectId: String = "${projectId}", memberId: String, role: String = randomRole.next()): ChainBuilder = {
      exec(
        http("addMemberHTTP")
          .post(s"/api/projects/${projectId}/members/user/${memberId}/role/${role}")
          .headers(jwtHeader)
          .headers(sessionHeaders)
          .check(status.is(201)))
        .exitHereIfFailed
    }

  }

  val scenarioLoginAsPeter: ScenarioBuilder =
    scenario("LoginAsPeter")
      .exec(User.login(peterAuth))


  val scenarioPeterCreateProject: ScenarioBuilder = {
    scenario("CreateProjectAsPeter")
      .feed(randomStringFeeder)
      .exec(User.login(peterAuth))
      .exec(Project.create("${randomString}"))
  }

  val scenarioFailPeterCreateProject: ScenarioBuilder =
    scenario("CreateProjectAsPeter")
      .exec(User.login(peterAuth._1,"NotThePassword"))
      .exec(Project.create("ShouldNotCreate"))

  val scenarioPeterCreateUser: ScenarioBuilder = {
    scenario("CreateUserAsPeter")
      .exec(User.login(peterAuth))
      .exec(User.create("USER"))
  }

  val scenarioPeterCreateUserLogin: ScenarioBuilder = {
    scenario("CreateUserLoginAsPeter")
      .exec(User.login(peterAuth))
      .exec(User.createAndLogin("USER"))
  }

  val scenarioPeterGetProjects: ScenarioBuilder = {
    scenario("GetAllProjectsAsPeter")
      .exec(User.login(peterAuth))
      .exec(Project.getAll)
      .exec(session => {
        print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n")
        val vector: Vector[String] = session.attributes("projectList").asInstanceOf[Vector[String]]
        print(vector)
        for (id <- vector) print(s"\n iterate: $id")
        session
      })
  }

  val scenarioUserCreatesProjectAddsMembers: ScenarioBuilder = {
    scenario("scenarioUserCreatesProjectAddsMembers")
      //Random.between(3,15)
      .exec(
        User.login(peterAuth),
        pause(Random.between(3,15)),
        User.createAndLogin("USER",1),
        pause(Random.between(3,15)),
        Project.create(),
        pause(Random.between(3,15)),
        User.getAll
      )
      .exec(session => {
        val loggedInUser = session.attributes("userId").asInstanceOf[String]
        val userList: List[String] = session.attributes("userList")
          .asInstanceOf[Vector[String]]
          .filter(id => id != loggedInUser)
          .toList
        val addingList = Random.shuffle(userList).take(Random.between(1, userList.length))
        val new_session = session.set("addingList", addingList)
        new_session
      })
      .repeat(session => session("addingList").as[List[Any]].size, "index"){
        exec(session => {
          val addingList = session("addingList").as[List[Any]]
          val index = session("index").as[Int]
          session.set("addingId", addingList(index))
        })
          .pause(Random.between(3,15))
          .exec(Project.addMember(memberId="${addingId}"))
    }
        /*
        print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n")
        print(addingList)
        for (userId <- addingList){
          print(s"$userId \n")
          exec(
            pause(1),
            Project.addMember(memberId = userId)
          )

        }
        session

        })
         */



  }


  /*
print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n")
//val vector: Vector[String] = session.attributes("userList").asInstanceOf[Vector[String]]
print(vector)
for (id <- vector) print(s"\n iterate: $id")
session

 */

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
    scenarioPeterCreateUserLogin
      .inject(
        atOnceUsers(1),
        //constantUsersPerSec(5).during(1.minute).randomized
      )
  )
    .protocols(httpProtocol)
    .assertions(
      global.responseTime.mean.lt(1000),
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(95),
      global.failedRequests.percent.lt(5)
    )

}
