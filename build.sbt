// name := """scala-play"""
// organization := "com.example"

// version := "1.0-SNAPSHOT"

// lazy val root = (project in file(".")).enablePlugins(PlayScala)

// scalaVersion := "2.13.5"

// libraryDependencies += guice
// libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// // libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
// libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
// // Adds additional packages into Twirl
// //TwirlKeys.templateImports += "com.example.controllers._"

// // Adds additional packages into conf/routes
// // play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

// // herokuAppName in Compile := "scala-play-app"

// libraryDependencies ++= Seq(
//   "com.typesafe.play" %% "play-slick" % "5.0.0",
//   "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
// )

// Compile / herokuProcessTypes := Map(
//   "web" -> "target/universal/stage/bin/scala-play-app -Dhttp.port=$PORT",
// )

name := """scala-play-app"""
organization := "com.arifwider"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
)

Compile / herokuProcessTypes := Map(
  "web" -> "target/universal/stage/bin/scala-play-app -Dhttp.port=$PORT",
)