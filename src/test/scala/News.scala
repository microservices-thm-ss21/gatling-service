package de.thm.mni.microservices.gruppe6


import Feeder._
import Utils._

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object News {

  val getNewsOwn: ChainBuilder = exec(
      http("getNewsOwn")
        .get("/api/news/")
        .headers(jwtHeader)
        .headers(sessionHeaders)
        .check(status.is(200))
    ).exitHereIfFailed

}
