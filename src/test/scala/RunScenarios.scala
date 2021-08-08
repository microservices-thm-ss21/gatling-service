package de.thm.mni.microservices.gruppe6

import Feeder._

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.util.Random

class RunScenarios extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8069")
    .headers(Map(
      "Content-Type" -> "application/json"
    ))
    .userAgentHeader(
      "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"
    )

  /**
   * Logs in as Peter Zwegat
   */
  val scenarioLoginAsPeter: ScenarioBuilder =
    scenario("scenarioLoginAsPeter")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)


  /**
   * 1. Logs in as Peter Zwegat. <br>
   * 2. Creates a random Project <br>
   */
  val scenarioPeterCreateProject: ScenarioBuilder = {
    scenario("scenarioPeterCreateProject")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .exec(Project.create)
  }

  /**
   * 1. Logs in as Peter Zwegat. <br>
   * 2. Creates a random user <br>
   */
  val scenarioPeterCreateUser: ScenarioBuilder = {
    scenario("scenarioPeterCreateUser")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .exec(User.create("USER"))
  }

  /**
   * 1. Logs in as Peter Zwegat. <br>
   * 2. Creates a random user of random role <br>
   * 3. Logs in as the new user
   */
  val scenarioPeterCreateUserLogin: ScenarioBuilder = {
    scenario("scenarioPeterCreateUserLogin")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .exec(User.create(randomRole.next()))
      .exec(User.login)
  }

  /**
   * 1. Logs in as Peter Zwegat. <br>
   * 2. Gets all Projects <br>
   */
  val scenarioPeterGetProjects: ScenarioBuilder = {
    scenario("scenarioPeterGetProjects")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .exec(Project.getAll)
      .exec(session => {
        print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n")
        val vector: Vector[String] = session.attributes("projectList").asInstanceOf[Vector[String]]
        print(vector)
        for (id <- vector) print(s"\n iterate: $id")
        session
      })
  }

  /**
   * 1. Logs in as Peter Zwegat. <br>
   * 2. Gets all Users <br>
   */
  val scenarioPeterGetUsers: ScenarioBuilder = {
    scenario("scenarioPeterGetUsers")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .exec(User.getAll)
      .exec(session => {
        print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n")
        val vector: Vector[String] = session.attributes("projectList").asInstanceOf[Vector[String]]
        print(vector)
        for (id <- vector) print(s"\n iterate: $id")
        session
      })
  }

  /**
   * 1. Log in as Peter <br>
   * 2. Create new User. <br>
   * 3. Create new Project. <br>
   * 4. Add member to new project <br>
   */
  val scenarioPeterCreateUserProjectMember: ScenarioBuilder = {
    scenario("scenarioPeterCreateUserProjectMember")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .exec(User.create("USER"))
      .exec(Project.create)
      .exec(Project.addMember(memberId = "${userId}"))
  }

  /**
   * 1. Logs in as Peter Zwegat. <br>
   * 2. Creates a random user of role USER <br>
   * 3. Logs in as the new user <br>
   * 4. Create a new Project <br>
   * 5. Get all Users <br>
   * 5.1 Select a random subset of the users <br>
   * 6. Add these users to the project <br>
   */
  val scenarioUserCreatesProjectAddsMembers: ScenarioBuilder = {
    scenario("scenarioUserCreatesProjectAddsMembers")
      .exec(User.setAuth(peterAuth))
      .exec(User.login)
      .pause(Random.between(3,15))
      .exec(User.create("USER"))
      .pause(Random.between(3,15))
      .exec(User.login)
      .pause(Random.between(3,15))
      .exec(Project.create)
      .pause(Random.between(3,15))
      .exec(User.getAll)
      // now filter the users
      .exec(session => {
        // check that the to adding user does not try to add himself
        val loggedInUser = session.attributes("userId").asInstanceOf[String]
        val userList: List[String] = session.attributes("userList")
          .asInstanceOf[Vector[String]]
          .filter(id => id != loggedInUser)
          .toList
        val addingList = Random.shuffle(userList).take(Random.between(1, userList.length))
        session.set("addingList", addingList)
      })
      // iterate over the adding list and set current adding Id
      .repeat(session => session("addingList").as[List[String]].size, "index"){
        exec(session => {
          val addingList = session("addingList").as[List[String]]
          val index = session("index").as[Int]
          session.set("addingId", addingList(index))
        })
          // actually add the selected member
          .pause(Random.between(3,15))
          .exec(Project.addMember(memberId="${addingId}"))
    }

    /**
     * 1. Logs in as Peter Zwegat. <br>
     * 2. Creates a random user of role USER <br>
     * 3. Logs in as the new user <br>
     * 4. Get all Projects <br>
     * 4.1 Select a random Project
     * 5. Create Issue for Project
     */
    val scenarioCreateUserRandomProjectIssue = {
      scenario("scenarioCreateUserRandomProjectIssue")
        .exec(User.setAuth(peterAuth))
        .exec(User.login)
        .pause(Random.between(3,15))
        .exec(User.create("USER"))
        .pause(Random.between(3,15))
        .exec(User.login)
        .exec(Project.getAll)
        // now filter the project
        .exec(session => {
          val projectList: List[String] = session.attributes("projectList")
            .asInstanceOf[Vector[String]]
            .toList
          val chosenProject = projectList(Random.nextInt(projectList.length))
          session.set("projectId", chosenProject)
        })

    }
        /*
        .exec(session => {
        print("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n")
        print(session)
        session
      })

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
    scenarioUserCreatesProjectAddsMembers
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
