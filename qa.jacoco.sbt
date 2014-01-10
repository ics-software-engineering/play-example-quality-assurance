import de.johoop.jacoco4sbt._
import JacocoPlugin._

//----------------------------------------
// Setup Jacoco test coverage.
// Invocation: jacoco:cover
// Results: target/jacoco/html/index.html
// See: https://github.com/sbt/jacoco4sbt
//      project/plugins.sbt
//----------------------------------------

jacoco.settings

parallelExecution      in jacoco.Config := false

jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8"))

jacoco.excludes        in jacoco.Config := Seq("views*", "*Routes*", "controllers*routes*", "controllers*Reverse*", "controllers*javascript*", "controller*ref*")
