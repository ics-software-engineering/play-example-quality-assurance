import de.johoop.jacoco4sbt._
import JacocoPlugin._

name         := "play-example-quality-assurance"

version      := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

jacoco.settings

parallelExecution      in jacoco.Config := false

jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8"))

jacoco.excludes        in jacoco.Config := Seq("views*", "*Routes*", "controllers*routes*", "controllers*Reverse*", "controllers*javascript*", "controller*ref*")


play.Project.playJavaSettings
  
