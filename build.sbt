import sbt._
import Keys._
import play.Project._
import java.io.File

name         := "play-example-quality-assurance"

version      := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  // Java project dependencies here:
  //javaCore
  //javaJdbc,
  //javaEbean
  //
  // For Scala projects, at least the first dependency is required instead of the above:
  "com.typesafe.play" %% "play"       % "2.2.0" withSources,
  // "com.typesafe.play" %% "anorm"      % "2.2.0" withSources,
  // "com.typesafe.play" %% "play-jdbc"  % "2.2.0" withSources,
  // "com.typesafe.play" %% "play-json"  % "2.2.0" withSources,
  "junit"                %  "junit"      % "4.8.1" % "test"
)
  
