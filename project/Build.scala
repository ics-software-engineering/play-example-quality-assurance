import sbt._
import Keys._
import play.Project._
import de.johoop.findbugs4sbt.FindBugs._
import de.johoop.jacoco4sbt._
import JacocoPlugin._
import java.io.File

object ApplicationBuild extends Build {

  val appName         = "play-example-quality-assurance"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )
  
  // Update settings for findbugs and jacoco SBT plugins.
  lazy val s = Defaults.defaultSettings ++ findbugsSettings ++ Seq(jacoco.settings:_*)

  val main = play.Project(appName, appVersion, appDependencies, settings = s).settings(

    // Add 'pmd' command to Play console. 
    // Configuration file: project/pmd-ruleset.xml
    // Output file: target/pmd/pmd-report.txt
    PmdSettings.pmdTask,

    // Add 'checkstyle' command to Play console. 
    // Configuration file: project/checkstyle-config.xml
    // Output file: target/checkstyle/checkstyle-report.txt
    CheckstyleSettings.checkstyleTask,

    // Add 'api-doc' command (JavaDoc + ScalaDoc) to Play console. 
    // Output directory: target/doc/api
    ApiDocSettings.apiDocTask,

    // Add the 'findbugs' command to Play console.
    // Configuration file: project/findbugs-excludefilter.xml
    // Output file: target/findbugs/findbugs.xml
    findbugsExcludeFilters := Some(scala.xml.XML.loadFile("project/findbugs-excludefilter.xml")),
    findbugsReportType := de.johoop.findbugs4sbt.ReportType.Html,
    findbugsTargetPath := file("target/findbugs"),

    // Add the 'jacoco:cover' command to Play console. 
    // Output file: target/jacoco/html/index.html
    parallelExecution      in jacoco.Config := false,
    jacoco.outputDirectory in jacoco.Config := file("target/jacoco"),
    jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8")),
    jacoco.excludes        in jacoco.Config := Seq("views*", "*Routes*", "controllers*routes*", "controllers*Reverse*", "controllers*javascript*", "controller*ref*")
  )

}
