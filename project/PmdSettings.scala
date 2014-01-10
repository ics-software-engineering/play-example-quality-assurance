import sbt._
import Keys._
import play.Project._

//Adapted from https://github.com/ymasory/sbt-code-quality.g8
object PmdSettings {

  val pmd = TaskKey[Unit]("pmd", "run pmd, placing results in target/pmd")
  val pmdTask = pmd <<=
    (streams, baseDirectory, sourceDirectory in Compile, target) map {
      (streams, base, src, target) =>
      import net.sourceforge.pmd.PMD.{ main => PmdMain }
      import streams.log
      val outputDir = (target / "pmd").mkdirs
      val outputFile = (target / "pmd" / "pmd-report.xml").getAbsolutePath

      val args = List(
          src.getAbsolutePath,
          "xml",
          (base / "project" / "pmd-ruleset.xml").getAbsolutePath,
          "-reportfile", outputFile
      )

      log info ("Running PMD...")
      trappingExits {
        PmdMain(args.toArray)
      }
      
      // Print out results to console
      val source = scala.io.Source.fromFile(outputFile)
      log info (source.mkString)
      source.close()
    }

  def trappingExits(thunk: => Unit): Unit = {
    val originalSecManager = System.getSecurityManager
    case class NoExitsException() extends SecurityException
    System setSecurityManager new SecurityManager() {
      import java.security.Permission
      override def checkPermission(perm: Permission) {
        if (perm.getName startsWith "exitVM") throw NoExitsException()
      }
    }
    try {
      thunk
    } catch {
      case _: NoExitsException =>
      case e : Throwable =>
    } finally {
      System setSecurityManager originalSecManager
    }
  }
}
  
