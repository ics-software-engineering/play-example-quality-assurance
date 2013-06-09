import sbt._
import Keys._
import play.Project._

// Adapted from https://github.com/ymasory/sbt-code-quality.g8
object CheckstyleSettings {

  val checkstyle = TaskKey[Unit]("checkstyle", "run checkstyle, placing results in target/checkstyle")
  val checkstyleTask = checkstyle <<=
    (streams, baseDirectory, sourceDirectory in Compile, target) map {
      (streams, base, src, target) =>
      import com.puppycrawl.tools.checkstyle.Main.{ main => CsMain }
      import streams.log
      val outputDir = (target / "checkstyle").mkdirs
      val outputFile = (target / "checkstyle" / "checkstyle-report.txt").getAbsolutePath

      val args = List(
        "-c", (base / "project" / "checkstyle-config.xml").getAbsolutePath,
        "-f", "plain",
        "-r", src.getAbsolutePath,
        "-o", outputFile
      )
      log info ("Running checkstyle...")
      trappingExits {
        CsMain(args.toArray)
      }
      // Print out results.
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
      case e => throw e
    } finally {
      System setSecurityManager originalSecManager
    }
  }
}
  