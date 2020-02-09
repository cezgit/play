scalaVersion := "2.11.12"
organization := "com.cj"

name := "Spark Training - API Walkthrough"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.1",
  "org.apache.spark" %% "spark-sql" %  "2.1.1",
  "org.scalatest" %% "scalatest" % "3.0.5"
)