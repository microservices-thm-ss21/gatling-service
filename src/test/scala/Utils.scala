package de.thm.mni.microservices.gruppe6

object Utils {

  val sessionHeaders = Map(
    "Content-Type" -> "application/json"
  )

  val jwtHeader = Map(
    "Authorization" -> "${authToken}"
  )

}
