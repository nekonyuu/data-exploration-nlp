
name := "data-exploration-nlp"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += "GitHub nekonyuu artifacts - snapshots" at "https://artifacts.nyuu.eu/snapshots"

val sparkVersion = "2.3.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "nekonyuu" %% "spark-corenlp" % "0.3.0-SNAPSHOT"
)
