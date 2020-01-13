import sbt._

object Dependencies {
  val akkaVersion = "2.5.23"
  val akkaHttpVersion = "10.1.9"
  val scalaTestVersion = "3.0.8"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  lazy val akkaStreamTestKit = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
  lazy val uPickle = "com.lihaoyi" %% "upickle" % "0.8.0"

  val akkaDependencies = Def.setting(Seq(akkaActor, akkaHttp, akkaStream, scalaTest, akkaHttpTestKit, akkaStreamTestKit, uPickle))
}
