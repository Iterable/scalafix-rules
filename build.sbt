lazy val V = _root_.scalafix.sbt.BuildInfo
inThisBuild(
  List(
    scalaVersion := V.scala213,
    crossScalaVersions := List(V.scala213),
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions += "-Yrangepos",
    organization := "com.iterable",
    homepage := Some(url("https://github.com/iterable/scalafix-rules")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List()
  )
)

publish / skip := true

lazy val rules = project.settings(
  moduleName := "scalafix-rules",
  libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion
)

lazy val input = project
  .settings(publish / skip := true)

lazy val output = project
  .settings(publish / skip := true)

lazy val tests = project
  .settings(
    libraryDependencies += "ch.epfl.scala" % "scalafix-testkit" % V.scalafixVersion % Test cross CrossVersion.full,
    scalafixTestkitOutputSourceDirectories :=
      (output / Compile / sourceDirectories).value,
    scalafixTestkitInputSourceDirectories :=
      (input / Compile / sourceDirectories).value,
    scalafixTestkitInputClasspath :=
      (input / Compile / fullClasspath).value
  )
  .settings(publish / skip := true)
  .dependsOn(input, rules)
  .enablePlugins(ScalafixTestkitPlugin)
