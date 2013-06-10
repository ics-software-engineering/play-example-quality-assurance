Overview
========

This project illustrates how to integrate the following quality assurance tools with [Play](http://www.playframework.com/) applications:

  * [PMD](http://pmd.sourceforge.net/) source code analysis
  * [Checkstyle](http://checkstyle.sourceforge.net/) source code analysis
  * [FindBugs](http://findbugs.sourceforge.net/) byte code analysis
  * [JavaDoc](http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html) and [ScalaDoc](http://docs.scala-lang.org/style/scaladoc.html) documentation generation
  * [Jacoco](http://www.eclemma.org/jacoco/) test coverage analysis

Installation
============

Adding these tools to your Play project requires changes to the [project/](https://github.com/ics-software-engineering/play-example-quality-assurance/tree/master/project) directory:

  * Update [plugins.sbt](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/plugins.sbt) to add libraries.
  * Update [Build.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/Build.scala) to add commands to the play console.
  * Add command definition files: [ApiDocSettings.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/ApiDocSettings.scala), [CheckstyleSettings.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/CheckstyleSettings.scala), and [PmdSettings.scala](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/PmdSettings.scala).
  * Add (and maybe modify) configuration files: [checkstyle-config.xml](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/checkstyle-config.xml) and [pmd-ruleset.xml](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/pmd-ruleset.xml).

Example invocations
===================

PMD
---

```
[~/projecthosting/github/play-example-quality-assurance]-> play pmd
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[info] Running PMD...
[info] 
[success] Total time: 1 s, completed Jun 10, 2013 1:33:54 PM
```

The output file is in target/pmd/pmd-report.txt and echoed to the console.  For Play's default application, no PMD warnings are generated, resulting in a blank info line output.

Modify [pmd-ruleset.xml](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/pmd-ruleset.xml) to change the way PMD analyzes your code. 

Checkstyle
----------

```
[~/projecthosting/github/play-example-quality-assurance]-> play checkstyle
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[info] Running checkstyle...
[info] Starting audit...
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:0: Missing package-info.java file.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:3: Using the '.*' form of import should be avoided - play.*.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:4: Using the '.*' form of import should be avoided - play.mvc.*.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:6: Using the '.*' form of import should be avoided - views.html.*.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:8: Missing a Javadoc comment.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:9: Line has trailing spaces.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:10:5: Missing a Javadoc comment.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java:13: Line has trailing spaces.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/views/index.scala.html:4: Line has trailing spaces.
[info] /Users/johnson/projecthosting/github/play-example-quality-assurance/app/views/index.scala.html:6: Line has trailing spaces.
[info] Audit done.
[success] Total time: 1 s, completed Jun 10, 2013 1:39:55 PM
```

The output file is in target/checkstyle/checkstyle-report.txt and also echoed to the console. For Play's default application, 10 Checkstyle warnings are generated.

Modify [checkstyle-config.xml](https://github.com/ics-software-engineering/play-example-quality-assurance/blob/master/project/checkstyle-config.xml) to change the way Checkstyle analyzes your code.



Credits
=======

  * Checkstyle and PMD integration thanks to Yuvi Masory: https://github.com/ymasory/sbt-code-quality.g8
  * Findbugs integration thanks to Joachim Hofer: https://bitbucket.org/jmhofer/findbugs4sbt/wiki/Home
  * Jacoco integration thanks to Joachim Hofer: https://bitbucket.org/jmhofer/jacoco4sbt
  * JavaDoc/ScalaDoc integration thanks to Yvonnick Esnault: https://github.com/yesnault/Play20StartApp/
  
Play version
------------

Last tested on Play 2.1.1


