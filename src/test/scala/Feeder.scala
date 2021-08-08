package de.thm.mni.microservices.gruppe6

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.Random

object Feeder {

  var peterAuth: (String, String) = ("Peter_Zwegat", "password")
  var peterUserId: String = "a443ffd0-f7a8-44f6-8ad3-87acd1e91042"
  var kimOnAuth: (String, String) = ("Kim-Jong-On", "password")

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

  def randomUserFeeder(role:String): Iterator[Map[String, String]] = {
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

  val randomIssueFeeder: Iterator[Map[String, String]] = {
    Iterator.continually(Map(
      "message" -> (randomString.next() + randomString.next()),
      //"assignedUserId" -> assignedUserId,
      //"projectId" -> "${projectId}",
      "deadline" -> randomFutureDate.next(),
      "status" -> List("OPEN", "CLOSED", "IN_PROGRESS", "TO_BE_REVIEWED")(Random.nextInt(2))
    ))
  }

}
