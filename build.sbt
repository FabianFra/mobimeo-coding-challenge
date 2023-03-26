ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

val AkkaVersion = "2.7.0"
val AkkaHttpVersion = "10.5.0"
val JUnitVersion = "0.13.3"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.12.2",

  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,

  "com.github.sbt" % "junit-interface" % JUnitVersion % "test"
)

lazy val root = (project in file("."))
  .settings(
    name := "mobimeo-coding-challenge",
    idePackagePrefix := Some("com.ffrank.mobimeo")
  )

