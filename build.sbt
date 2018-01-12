import Dependencies._
import ReleaseTransformations._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(scalaVersion := "2.12.3")),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xfatal-warnings",
      "-Ypartial-unification",
      "-Ywarn-unused-import"
    ),

    autoAPIMappings := true,
    developers := List(
      Developer(
        id="jferris",
        name="Joe Ferris",
        email="jferris@thoughtbot.com",
        url=url("https://github.com/jferris")
      )
    ),
    licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
    homepage := Some(url("https://github.com/thoughtbot/monocats")),
    name := "monocats",
    organization := "com.thoughtbot",
    publishMavenStyle := true,
    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/thoughtbot/monocats"),
        "scm:git:git@github.com:thoughtbot/monocats.git"
      )
    ),
    useGpg := true,

    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      publishArtifacts,
      releaseStepCommand("sonatypeRelease"),
      setNextVersion,
      commitNextVersion,
      pushChanges
    ),

    libraryDependencies += scalaTest % Test
  )
  .settings(
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.2",
      "org.typelevel" %% "cats-core" % "1.0.0-RC1"
    )
  )
