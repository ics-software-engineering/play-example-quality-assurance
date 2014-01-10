import de.johoop.findbugs4sbt.FindBugs._

//-----------------------------------------
// Setup FindBugs static analysis
// Invocation: findbugs
// Results:  target/findbugs/findbugs.xml
// See: https://github.com/sbt/findbugs4sbt
//      project/findbugs-exclude-filters.xml
//      project/plugins.sbt
//-----------------------------------------

findbugsSettings

findbugsReportType := Some(de.johoop.findbugs4sbt.ReportType.Xml)

findbugsReportPath := Some(file("target/findbugs/findbugs.xml"))

findbugsExcludeFilters := Some(scala.xml.XML.loadFile(baseDirectory.value / "project" / "findbugs-exclude-filters.xml"))
