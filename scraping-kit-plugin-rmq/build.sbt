name := "scraping-kit-plugin-rmq"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "com.github.sstone" %% "amqp-client" % "1.5",

  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)