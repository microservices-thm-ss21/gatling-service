package de.thm.mni.microservices.gruppe6

import Feeder._
import Utils._

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object User {

  def setAuth(auth: (String, String)): ChainBuilder = User.setAuth(auth._1, auth._2)
  def setAuth(username: String, password: String): ChainBuilder = {
    exec(session => {
      session.set("username", username)
        .set("password", password)
    }).exitHereIfFailed
  }

  val login: ChainBuilder = {
    exec(
      http("loginUserHTTP")
        .get("/login")
        .basicAuth("${username}","${password}")
        .check(status.is(200))
        .check(header("authorization").exists)
        .check(
          header("authorization").saveAs("authToken")
        )
    ).exitHereIfFailed
  }

  /**
   * saves user ID into field "userId.
   * Saves username and password in respective fields.
   */
  def create(role: String): ChainBuilder = {
    feed(randomUserFeeder(role))
      .exec(
        http("createUserHTTP")
          .post(s"/api/users/")
          .headers(jwtHeader)
          .body(ElFileBody("bodies/userDTO.json")).asJson
          .check(status.is(201))
          .check(jsonPath("$.id").saveAs("userId"))
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
