import sbt._
import Keys._
import play.Project._
import de.johoop.findbugs4sbt.FindBugs._

object ApplicationBuild extends Build {
  

  val appName         = "play-example-qa"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies,
                          settings = Defaults.defaultSettings ++ findbugsSettings).settings(
    // Add 'pmd' command to Play console.
    PmdSettings.pmdTask,
    // Add 'checkstyle' command to Play console.
    CheckstyleSettings.checkstyleTask,
    // Add 'api-doc' command (JavaDoc + ScalaDoc) to Play console.
    ApiDocSettings.apiDocTask,
    // Configure the 'findbugs' command within Play console.
    //findbugsExcludeFilters := Some(scala.xml.XML.loadFile("project/findbugs-excludefilter.xml")),
    findbugsReportType := de.johoop.findbugs4sbt.ReportType.PlainHtml,
    findbugsTargetPath := file("target/findbugs")
  )

}