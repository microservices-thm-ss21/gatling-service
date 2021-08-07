enablePlugins(GatlingPlugin)

name := "stresstest-gatling"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("de.thm.mni.microservices.gruppe6")

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

val gatlingVersion = "3.6.1"
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test,it"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % gatlingVersion % "test,it"
