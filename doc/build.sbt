name := "jacoco-test"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.puppycrawl.tools" % "checkstyle" % "5.5"
  ) 
    
// Setup Play for Java
play.Project.playJavaSettings

