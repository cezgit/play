scalaVersion := "2.11.12"
organization := "com.cj"

name := "Log Analysis - Exercise"

resolvers ++= Seq(
  MavenRepository("nexus", "http://build105.wl.cj.com:8081/nexus/content/groups/public"),
  Resolver.mavenLocal,
  Resolver.mavenCentral
)

fork in Test := true

javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

val sparkVersion = "2.1.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" %  sparkVersion,
  "com.cj.data" % "spark-lib" % "2.1-SNAPSHOT",

  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.holdenkarau" %% "spark-testing-base" % s"${sparkVersion}_0.10.0" % "test",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "test"
)
