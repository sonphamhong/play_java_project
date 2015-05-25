name := "zombie2"

version := "1.0-SNAPSHOT"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.27"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings
