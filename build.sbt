name         := "play-example-quality-assurance"

version      := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

play.Project.playJavaSettings
  
