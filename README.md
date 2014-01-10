Overview
========

This system illustrates how to integrate the following quality assurance tools with [Play](http://www.playframework.com/) applications:

  * [PMD](http://pmd.sourceforge.net/) source code analysis
  * [Checkstyle](http://checkstyle.sourceforge.net/) source code analysis
  * [FindBugs](http://findbugs.sourceforge.net/) byte code analysis
  * [JavaDoc](http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html) and [ScalaDoc](http://docs.scala-lang.org/style/scaladoc.html) documentation generation
  * [Jacoco](http://www.eclemma.org/jacoco/) test coverage analysis
  
The code is designed to allow you to add only those tools that you need for your project. Installation and running
of each tool is discussed separately below.

PMD
===

Installation
------------

  * Update project/plugins.sbt with the PMD library dependency.
  * Add project/pmd-ruleset.xml
  * Add project/PmdSettings.scala
  * Add qa.pmd.sbt 

Invocation
---------

```
[~/projecthosting/github/play-example-quality-assurance]-> play pmd
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[info] Running PMD...
[info] <?xml version="1.0" encoding="UTF-8"?>
[info] <pmd version="5.0.0" timestamp="2014-01-10T13:05:53.566">
[info] </pmd>
[success] Total time: 1 s, completed Jan 10, 2014 1:05:53 PM
```

The output file is in target/pmd/pmd-report.xml and echoed to the console.  For Play's default application, no PMD warnings are generated, resulting in a blank info line output.

Checkstyle
==========

Installation
------------

  * Update project/plugins.sbt with the Checkstyle library dependency.
  * Add project/checkstyle-config.xml
  * Add project/CheckstyleSettings.scala
  * Add qa.checkstyle.sbt 

Invocation
----------

```
[~/projecthosting/github/play-example-quality-assurance]-> play checkstyle
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[info] Running checkstyle...
[info] <?xml version="1.0" encoding="UTF-8"?>
[info] <checkstyle version="5.5">
[info] <file name="/Users/johnson/projecthosting/github/play-example-quality-assurance/app/controllers/Application.java">
[info] <error line="0" severity="error" message="Missing package-info.java file." source="com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocPackageCheck"/>
[info] <error line="3" severity="error" message="Using the &apos;.*&apos; form of import should be avoided - play.*." source="com.puppycrawl.tools.checkstyle.checks.imports.AvoidStarImportCheck"/>
[info] <error line="4" severity="error" message="Using the &apos;.*&apos; form of import should be avoided - play.mvc.*." source="com.puppycrawl.tools.checkstyle.checks.imports.AvoidStarImportCheck"/>
[info] <error line="6" severity="error" message="Using the &apos;.*&apos; form of import should be avoided - views.html.*." source="com.puppycrawl.tools.checkstyle.checks.imports.AvoidStarImportCheck"/>
[info] <error line="8" severity="error" message="Missing a Javadoc comment." source="com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocTypeCheck"/>
[info] <error line="10" column="5" severity="error" message="Missing a Javadoc comment." source="com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocMethodCheck"/>
[info] </file>
[info] <file name="/Users/johnson/projecthosting/github/play-example-quality-assurance/app/views/index.scala.html">
[info] </file>
[info] <file name="/Users/johnson/projecthosting/github/play-example-quality-assurance/app/views/main.scala.html">
[info] </file>
[info] </checkstyle>
[success] Total time: 1 s, completed Jan 10, 2014 1:08:06 PM
```

The output file is in target/checkstyle/checkstyle-report.xml and also echoed to the console. For Play's default application, 6 Checkstyle warnings are generated.


FindBugs
========

Installation
------------

  * Update project/plugins.sbt with the FindBugs sbt plugin.
  * Add project/findbugs-exclude-filters.xml
  * Add qa.findbugs.sbt 

Invocation
----------

```
[~/projecthosting/github/play-example-quality-assurance]-> play findbugs
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[success] Total time: 7 s, completed Jun 10, 2013 1:45:10 PM
```

The output file is in target/findbugs/findbugs.xml.  For Play's default application, no FindBug errors are generated, but the plugin will output the number of warnings found if non-zero.


Jacoco
======

Installation
------------

  * Update project/plugins.sbt with the Jacoco sbt plugin.
  * Add qa.jacoco.sbt
  
** Note: Due to ASM library incompatibilities, you cannot install both Jacoco and PMD.  If you do, then Jacoco
will report the following error:**
    
    [error] (jacoco:fullClasspath) java.lang.IncompatibleClassChangeError: class org.jacoco.core.internal.flow.ClassProbesVisitor has interface org.objectweb.asm.ClassVisitor as super class

Invocation
----------

```
[~/projecthosting/github/play-example-quality-assurance]-> play jacoco:cover
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[info] ApplicationTest
[info] + ApplicationTest.simpleCheck
[info] + ApplicationTest.renderTemplate
[info] 
[info] 
[info] Total for test ApplicationTest
[info] Finished in 0.239 seconds
[info] 2 tests, 0 failures, 0 errors
[info] IntegrationTest
[info] + IntegrationTest.test
[info] 
[info] 
[info] Total for test IntegrationTest
[info] Finished in 2.875 seconds
[info] 1 tests, 0 failures, 0 errors
[info] Passed: : Total 3, Failed 0, Errors 0, Passed 3, Skipped 0
[success] Total time: 4 s, completed Jun 10, 2013 1:51:29 PM
```

The output report is available in target/jacoco/html/index.html.  For Play's default application, statement coverage is 57%.


JavaDoc and ScalaDoc
====================

Installation
------------

  * Add project/ApiDocSettings.scala
  * Add qa.apidoc.sbt 

Invocation
----------

```
[~/projecthosting/github/play-example-quality-assurance]-> play api-doc
[info] Loading project definition from /Users/johnson/projecthosting/github/play-example-quality-assurance/project
[info] Set current project to play-example-quality-assurance (in build file:/Users/johnson/projecthosting/github/play-example-quality-assurance/)
[info] No sources available, skipping Scala API documentation...
[info] Creating destination directory: "target/doc/api/java/"
[info] Loading source files for package controllers...
[info] Loading source files for package tests...
[info] Constructing Javadoc information...
[info] Standard Doclet version 1.7.0_10
[info] Building tree for all the packages and classes...
[info] Generating target/doc/api/java/controllers/Application.html...
[info] Generating target/doc/api/java/tests/ApplicationTest.html...
[info] Generating target/doc/api/java/tests/IntegrationTest.html...
[info] Generating target/doc/api/java/overview-frame.html...
[info] Generating target/doc/api/java/controllers/package-frame.html...
[info] Generating target/doc/api/java/controllers/package-summary.html...
[info] Generating target/doc/api/java/controllers/package-tree.html...
[info] Generating target/doc/api/java/tests/package-frame.html...
[info] Generating target/doc/api/java/tests/package-summary.html...
[info] Generating target/doc/api/java/tests/package-tree.html...
[info] Generating target/doc/api/java/constant-values.html...
[info] Generating target/doc/api/java/src-html/controllers/Application.html...
[info] Generating target/doc/api/java/src-html/tests/IntegrationTest.html...
[info] Generating target/doc/api/java/src-html/tests/ApplicationTest.html...
[info] Building index for all the packages and classes...
[info] Generating target/doc/api/java/overview-tree.html...
[info] Generating target/doc/api/java/index-all.html...
[info] Generating target/doc/api/java/deprecated-list.html...
[info] Building index for all classes...
[info] Generating target/doc/api/java/allclasses-frame.html...
[info] Generating target/doc/api/java/allclasses-noframe.html...
[info] Generating target/doc/api/java/index.html...
[info] Generating target/doc/api/java/overview-summary.html...
[info] Generating target/doc/api/java/help-doc.html...
API documentation in target/doc/api

[success] Total time: 2 s, completed Jun 10, 2013 1:55:36 PM
```

The API documentation can be found in target/doc/api.

NOTE: With this approach, in order for test code to be documented by JavaDoc, you must locate them a package called 
"tests" inside the top-level test/ directory. See this repo for an example.
This is a change from the "play new" command, which puts the sample tests in the default package.  Creating a
"tests" package creates consistency with the existing Play convention of "controllers", "models", and "views"
packages.  

Credits
=======

  * Checkstyle and PMD integration thanks to Yuvi Masory: https://github.com/ymasory/sbt-code-quality.g8
  * Findbugs integration thanks to Joachim Hofer: https://bitbucket.org/jmhofer/findbugs4sbt/wiki/Home
  * Jacoco integration thanks to Joachim Hofer: https://bitbucket.org/jmhofer/jacoco4sbt
  * JavaDoc/ScalaDoc integration thanks to Yvonnick Esnault: https://github.com/yesnault/Play20StartApp/
  
Play version
============

Last tested on Play 2.2.0




