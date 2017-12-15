import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "thoughtbot",
        scalaVersion := "2.12.3",
        version := "0.1.0-SNAPSHOT"
      )),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xfatal-warnings",
      "-Ypartial-unification",
      "-Ywarn-unused-import"
    ),
    name := "monocats",
    libraryDependencies += scalaTest % Test
  )
  .settings(
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.2",
      "org.typelevel" %% "cats-core" % "1.0.0-RC1"
    )
  )
