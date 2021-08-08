package de.thm.mni.microservices.gruppe6

import Utils.jwtHeader

import Feeder._

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object Issue {

  /**
   * saves user ID into field "userId.
   * Saves username and password in respective fields.
   */
  val create: ChainBuilder = {
    feed(randomIssueFeeder)
      .exec(
        http("createIssueHTTP")
          .post("/api/issues/")
          .headers(jwtHeader)
          .body(ElFileBody("bodies/issueDTO.json")).asJson
          .check(status.is(201))
          .check(jsonPath("$.id").saveAs("issueId"))
      ).exitHereIfFailed
  }

}
