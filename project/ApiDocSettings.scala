import sbt._
import sbt.Keys._
import play.Project._
import java.nio.charset.StandardCharsets.UTF_8

// Adapted from: https://github.com/yesnault/Play20StartApp
// With enhancements by Mike Slinn: https://github.com/mslinn
object ApiDocSettings {
  def childPathFinder(dir: String, filetype: String): PathFinder = file(dir) ** ("*." + filetype)

  def childFiles(dir: String, filetype: String): Seq[File] = childPathFinder(dir, filetype).get

  val apiDocTask = TaskKey[Unit]("api-doc", "Create ScalaDoc and Javadoc, placing results in target/doc/api") <<=
      (fullClasspath in Test, compilers, streams) map { (classpath, cs, s) =>

    val apiDir = "target/doc/api"
    IO.delete(file(apiDir))

    // Scaladoc, including links to source on bitbucket, using the git: or https: protocols
    val scalaVersionForSbt = Option(System.getProperty("scala.version")).getOrElse("2.10")
    val scalaHtmlDir = "target/scala-" + scalaVersionForSbt + "/src_managed/main/views/html"
    val managedSrcFiles = childPathFinder(scalaHtmlDir, "scala")
    val sourceFiles = childFiles("app", "scala") ++ childFiles("test", "scala") ++ managedSrcFiles.get
    val gitOriginUrl = "git config remote.origin.url".!!.trim
    val midUrl = gitOriginUrl.substring(gitOriginUrl.indexOf("@")+1, gitOriginUrl.lastIndexOf(".")).replace(":", "/")
    // Minor tweak needed to also support github repos, I don't need it so did not do it:
    val scalaOptions = List("-doc-source-url", "https://" + midUrl + "/src/master/app€{FILE_PATH}.scala")
    new Scaladoc(10, cs.scalac)("Scala API", sourceFiles, classpath.map(_.data), file(apiDir + "/scala"), scalaOptions, s.log)
    val cwd = file("").getAbsolutePath
    val regex1 = cwd + "/" + scalaHtmlDir + "/(.*?).template.scala"
    val regex2 = "app" + cwd + "/(.*?)"
//    println("cwd=" + cwd)
//    println("scalaHtmlDir=" + scalaHtmlDir)
//    println("regex1=" + regex1)
//    println("regex2=" + regex2)
    (file(apiDir + "/scala") ** "*.html").get.foreach { file =>
      //println("Processing " + file.getAbsolutePath)
      val oldContents = IO.read(file, UTF_8)
      val newContents = oldContents.replaceAll(regex1, "/views/$1.scala.html").replaceAll(regex2, "$1")
      IO.write(file, newContents, UTF_8)
    }

    // Javadoc
    val javaFiles = childFiles("app", "java") ++ childFiles("test", "java")
    if (javaFiles.size==0) {
      println("Did not find any Java source files for Javadoc")
    } else {
      val javaSources = Seq(file("test"), file("app")).mkString(":")
      val javaApiTarget = file(apiDir + "/java")
      val javaClasspath = classpath.map(_.data).mkString(":")
      val javaPackages = "controllers:models:tests"

      val cmd = <x>javadoc -linksource -sourcepath {javaSources} -d {javaApiTarget} -subpackages {javaPackages} -classpath {javaClasspath}</x>
      //println("Executing: "+cmd.text)
      cmd ! s.log
    }
    println("API documentation in " + apiDir)
  }
}


      
      
      

      
