Overview
--------

This project illustrates how to integrate the following quality assurance tools with [Play](http://www.playframework.com/) applications:

  * [PMD](http://pmd.sourceforge.net/) source code analysis
  * [Checkstyle](http://checkstyle.sourceforge.net/) source code analysis
  * [FindBugs](http://findbugs.sourceforge.net/) byte code analysis
  * [JavaDoc](http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html) and [ScalaDoc](http://docs.scala-lang.org/style/scaladoc.html) documentation generation
  * [Jacoco](http://www.eclemma.org/jacoco/) test coverage analysis

Installation
------------

Adding these tools to your Play project involves changes only to the [project/](https://github.com/ics-software-engineering/play-example-quality-assurance/tree/master/project) directory:

  * Update [plugins.sbt](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/plugins.sbt) to add libraries.
  * Update [Build.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/Build.scala) to add commands to the play console.
  * Add command definition files: [ApiDocSettings.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/ApiDocSettings.scala), 
    [CheckstyleSettings.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/CheckstyleSettings.scala),
    and [PmdSettings.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/PmdSettings.scala).
    
  * Add (and maybe modify) configuration files: [checkstyle-config.xml](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/checkstyle-config.xml), 
    and [pmd-ruleset.xml](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/pmd-ruleset.xml).


Credits
-------

  * Checkstyle and PMD integration thanks to Yuvi Masory: https://github.com/ymasory/sbt-code-quality.g8
  * Findbugs integration thanks to Joachim Hofer: https://bitbucket.org/jmhofer/findbugs4sbt/wiki/Home
  * Jacoco integration thanks to Joachim Hofer: https://bitbucket.org/jmhofer/jacoco4sbt
  * JavaDoc/ScalaDoc integration thanks to Yvonnick Esnault: https://github.com/yesnault/Play20StartApp/


