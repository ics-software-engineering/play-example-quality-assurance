import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-example-qa"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here  
  )

}

// QA (CheckStyle, PMD) definitions follow

object ProjectSettings {

  def all: Seq[sbt.Project.Setting[_]] = List(
    CheckStyleSettings.all,
    PmdSettings.all
  ).flatten

  object CheckStyleSettings {

    val checkStyle = TaskKey[Unit]("checkstyle", "run CheckStyle")
    val checkStyleTask = checkStyle <<=
      (streams, baseDirectory, sourceDirectory in Compile, target) map {
        (streams, base, src, target) =>
        import com.puppycrawl.tools.checkstyle.Main.{ main => CsMain }
        import streams.log

        val args = List(
          "-c", (base / "project" / "checkstyle-config.xml").getAbsolutePath,
          "-f", "xml",
          "-r", src.getAbsolutePath,
          "-o", (target / "checkstyle-report.xml").getAbsolutePath
        )
        log info ("using checkstyle args " + args)
        trappingExits {
          CsMain(args.toArray)
        }
      }

    val all = Seq(checkStyleTask)
  }

  object PmdSettings {

    val pmd = TaskKey[Unit]("pmd", "run PMD")
    val pmdTask = pmd <<=
      (streams, baseDirectory, sourceDirectory in Compile, target) map {
        (streams, base, src, target) =>
        import net.sourceforge.pmd.PMD.{ main => PmdMain }
        import streams.log

        val args = List(
          src.getAbsolutePath,
          "html",
          (base / "project" / "pmd-ruleset.xml").getAbsolutePath,
          "-reportfile", (target / "pmd-report.html").getAbsolutePath
        )
        log info ("using pmd args " + args)
        trappingExits {
          PmdMain(args.toArray)
        }
      }

    val all = Seq(pmdTask)
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

