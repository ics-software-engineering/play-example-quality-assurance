import sbt._
import Keys._
import play.Project._

// Adapted from: https://github.com/yesnault/Play20StartApp
object ApiDocSettings {

  val apiDocTask = TaskKey[Unit]("api-doc", "run scaladoc and javadoc, placing results in target/api") <<= (fullClasspath in Test, compilers, streams) map { (classpath, cs, s) => 
        
  val apiDir = "target/doc/api"
  
  IO.delete(file(apiDir))
  // Scaladoc
  var scalaVersionForSbt = Option(System.getProperty("scala.version")).getOrElse("2.10.0")

  val sourceFiles = 
    (file("app") ** "*.scala").get ++ 
    (file("test") ** "*.scala").get ++ 
    (file("target/scala-" + scalaVersionForSbt + "/src_managed/main/views/html") ** "*.scala").get
  new Scaladoc(10, cs.scalac)("Scala API", sourceFiles, classpath.map(_.data), file(apiDir + "/scala"), Nil, s.log)
          
  // Javadoc
  val javaSources = Seq(file("test"), file("app")).mkString(":")
  val javaApiTarget = file(apiDir + "/java")
  val javaClasspath = classpath.map(_.data).mkString(":")
  val javaPackages = "controllers:models:tests"

  val cmd = <x>javadoc -linksource -sourcepath {javaSources} -d {javaApiTarget} -subpackages {javaPackages} -classpath {javaClasspath}</x>
  //println("Executing: "+cmd.text)
  cmd ! s.log

  println("API documentation in " + apiDir)
  }
}



      
      
      

      