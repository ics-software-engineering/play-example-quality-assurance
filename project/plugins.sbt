// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.1")

// QA tools (PMD, Checkstyle)
libraryDependencies ++= Seq(
  "com.puppycrawl.tools" % "checkstyle" % "5.5",
  "net.sourceforge.pmd" % "pmd" % "5.0.0"
)
