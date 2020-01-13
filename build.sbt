import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.cell.server"
ThisBuild / organizationName := "home"

lazy val root = (project in file("."))
  .settings(
    name := "scala-cell-server",
    libraryDependencies ++= akkaDependencies.value
  )
