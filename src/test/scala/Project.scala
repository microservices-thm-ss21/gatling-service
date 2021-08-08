package de.thm.mni.microservices.gruppe6



import Feeder._
import Utils._

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object Project {
  /**
   * saves projectId into "projectId".
   * saves projectName into "projectName".
   */
   var create: ChainBuilder = {
     feed(randomStringFeeder)
       .exec(
         http("createProjectHTTP")
          .post("/api/projects/${randomString}")
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
