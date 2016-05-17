name := "IronMQDemo"

version := "1.0"

lazy val `ironmqdemo` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" % "dispatch-core_2.10" % "0.11.3",
  jdbc ,
  anorm ,
  cache ,
  ws
)


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  