// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.0")

// Add PMD, Checkstyle, and Jacoco libraries
libraryDependencies ++= Seq(
  "com.puppycrawl.tools" % "checkstyle" % "5.5",
  "net.sourceforge.pmd" % "pmd" % "5.0.0",
  "org.jacoco" % "org.jacoco.core" % "0.5.9.201207300726" artifacts(Artifact("org.jacoco.core", "jar", "jar")),
  "org.jacoco" % "org.jacoco.report" % "0.5.9.201207300726" artifacts(Artifact("org.jacoco.report", "jar", "jar"))
)

// Add Findbugs plugin library
addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.2.1")

// Add Jacoco plugin library
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.1")


