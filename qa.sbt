import sbt._
import Keys._
import play.Project._
import de.johoop.findbugs4sbt.FindBugs._
import de.johoop.jacoco4sbt._
import de.johoop.jacoco4sbt.JacocoPlugin._

findbugsSettings

// Add 'pmd' command to Play console. 
// Configuration file: project/pmd-ruleset.xml
// Output file: target/pmd/pmd-report.txt
PmdSettings.pmdTask

// Add 'checkstyle' command to Play console. 
// Configuration file: project/checkstyle-config.xml
// Output file: target/checkstyle/checkstyle-report.txt
CheckstyleSettings.checkstyleTask

// Add 'api-doc' command (JavaDoc + ScalaDoc) to Play console. 
// Output directory: target/doc/api
ApiDocSettings.apiDocTask

// Add the 'findbugs' command to Play console.
// Configuration file: project/findbugs-excludefilter.xml
// Output file: target/findbugs/findbugs.xml
// You want the report name file extension to match the report type. 
findbugsReportType := Some(de.johoop.findbugs4sbt.ReportType.Xml)

findbugsReportName := Some("findbugs.xml")

// Not sure how to express this:
//findbugsTargetPath <<= target (_ / "findbugs")

// Not sure how to express this:
//findbugsExcludeFilters <<= baseDirectory { base => Some(xml.XML.loadFile(BuildPaths.projectStandard(base) / "findbugs-excludefilter.xml")) }

// Add the 'jacoco:cover' command to Play console. 
// Output file: target/jacoco/html/index.html
//parallelExecution      in jacoco.Config := false

//jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

//jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8"))

//jacoco.excludes        in jacoco.Config := Seq("views*", "*Routes*", "controllers*routes*", "controllers*Reverse*", "controllers*javascript*", "controller*ref*")

