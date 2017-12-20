name := "airspace"

version := "0.1"

scalaVersion := "2.12.4"

val akkaVersion = "2.5.6"

// Akka
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.0.11",
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "0.15",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.11" % Test,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)

//logback
libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3"
)

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
