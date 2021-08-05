package de.thm.mni.microservices.gruppe6

import scala.concurrent.duration._
import scala.util.Random

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RunScenarios extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8069")
    .inferHtmlResources(
      BlackList(
        """.*\.js""",
        """.*\.css""",
        """.*\.gif""",
        """.*\.jpeg""",
        """.*\.jpg""",
        """.*\.ico""",
        """.*\.woff""",
        """.*\.woff2""",
        """.*\.(t|o)tf""",
        """.*\.png""",
        """.*detectportal\.firefox\.com.*"""
      ),
      WhiteList()
    )
    .userAgentHeader(
      "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"
    )

  val randomQuestionBody =
    Iterator.continually(
      Map("randomRoomName" -> (Random.alphanumeric.take(200).mkString))
    )

  val sessionHeaders = Map(
    "Authorization" -> "${authToken}",
    "Content-Type" -> "application/json"
  )

  val headers_0 = Map(
    "Content-Type" -> "application/json"
  )

  var randomProjectNameFeeder = Iterator.continually(
    Map("randomProjectName" -> (Random.alphanumeric.take(10).mkString))
  )
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

  setUp(
    scn1
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
