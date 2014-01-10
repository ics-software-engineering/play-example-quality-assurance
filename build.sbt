name := "play-example-quality-assurance"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
  ) 
    
// Setup Play for Java
play.Project.playJavaSettings

