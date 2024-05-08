<!-- check-out single branch from repo:
        git clone -b b12 --single-branch https://github.com/sgra64/se1.play.git
 -->

# Assignment B1

Follow steps for completing this assignment.

 1. [Getting Started](#1-getting-started)                       

 2. [Sourcing the Project](#2-sourcing-the-project)             

 3. [Building the Application](#3-building-the-application)     (1 Pt)

 4. [Running the Application](#4-running-the-application)       (1 Pt)

 5. [Running JUnit Tests](#5-running-junit-tests)               (1 Pt)

 6. [Generating Javadoc](#6-generating-javadoc)                 (1 Pt)

 7. [Packaging the Application](#7-packaging-the-application)   

 8. [Running the Packaged Application](#8-running-the-packaged-application) (1 Pt)

 9. [How does *Build* work?](#9-how-does-build-work)            (1 Pt)


&nbsp;
## 1. Getting started

Make sure to have the [Java-JDK](https://www.oracle.com/java/technologies/downloads/)
version <b>21</b> (LTS, long-term support) installed and all tools show the same version.

Open a terminal and run:

```
java --version          ; the Java VM
javac --version         ; the Java Compiler
javadoc --version       ; the Javadoc processor
jar --version           ; the Java archiver to package .jar files
```

Same version for all Java tools, 21 or higher:

```
java 21 2023-09-19 LTS      ; Java VM
...
javac 21
javadoc 21
jar 21
```

Create a Java project with following structure.
Source code is in a dirctory called *src*.
Unit tests are in dirctory called *tests*.

```sh
--<se1.play>:
 |
 +-- README.md                          # project markup file (this file)
 |
 | # directory with files to source the project:
 +--<.env>
 |   +-- project.sh, init.classpath, init.project, init.gitignore
 |
 | # VSCode project configuration:
 +--<.vscode>
 |   +-- settings.json                  # project-specific VSCode settings
 |   +-- launch.json                    # Java/Debug launch configurtions
 |   +-- launch_terminal.sh             # terminal launch configurtions
 |
 +--<.git>                              # local git repository
 +-- .gitignore                         # files with patterns to ignore by git
 |
 | # libraries/modules needed by the project:
 +--<libs>
 |   +--<junit>                         # JUnit .jar files
 |   |   +-- apiguardian-api-1.1.2.jar, junit-platform-commons-1.9.3.jar,
 |   |   +-- junit-jupiter-api-5.9.3.jar, opentest4j-1.2.0.jar
 |   +--<jacoco>                        # Code coverage .jar files
 |   |   +-- jacocoagent.jar  jacococli.jar
 |   +--<jackson>                       # JSON library for Java
 |   |   +-- jackson-annotations-2.13.0.jar, jackson-databind-2.13.0.jar,
 |   |       jackson-core-2.13.0.jar
 |   +-- junit-platform-console-standalone-1.9.2.jar    # JUnit runtime
 |
 | # source code:
 +--<src>                       # Java source code
 |   +-- module-info.java           # module defintion file
 |   +--<application>               # Java package 'application'
 |       +-- package-info.java      # 'application'-package defintion
 |       +-- Application.java       # main application program with main()
 |
 +--<resources>                 # non-Java source code, mainly configuration
 |   +-- application.properties     # properties file for running the application
 |   +-- logging.properties         # logging properties
 |   +--<META-INF>                  # jar-packaging information
 |       +-- MANIFEST.MF            # jar-manifest file with main class
 |
 +--<tests>                     # Unit-test source code separated from src/main
 |   +--<application>               # mirrored package structure
 |       +-- Application_0_always_pass_Tests.java   # initial JUnit-test
 |
 | # generated artefacts, compiled classes:
 +--<bin>
 |   +-- application-1.0.0-SNAPSHOT.jar # executable .jar file (main artefact)
 |   +--<classes>                       # compiled Java classes (.class files)
 |   |   +-- module-info.class          # compiled module-info class
 |   |   +--<application>               # compiled 'application' package
 |   |       +-- package-info.class
 |   |       +-- Application.class
 |   |
 |   +--<test-classes>              # compiled test classes
 |       +--<application>
 |           +-- Application_0_always_pass_Tests.class
 |
```


&nbsp;
## 2. Sourcing the Project

Sourcing the project means to set up the project environment:

```sh
source .env/project.sh
```

Executing script
[project.sh](https://gitlab.bht-berlin.de/sgraupner/setup.se2/-/blob/main/.env/project.sh?ref_type=heads)
sets
[*environment variables*](https://opensource.com/article/19/8/what-are-environment-variables),
creates local project files for *VS Code* and *eclipse* IDE and sets some
[*aliases*](https://opensource.com/article/19/7/bash-aliases) and
[*functions*](https://linuxize.com/post/bash-functions):

```
setting the project environment
 - environment variables:
    - CLASSPATH
    - MODULEPATH
    - JDK_JAVAC_OPTIONS
    - JDK_JAVADOC_OPTIONS
    - JUNIT_OPTIONS
 - files created:
    - .env/.classpath
    - .classpath
    - .project
    - .gitignore
 - functions and aliases created:
    - aliases: mk, build, wipe, clean
    - functions: make, show, cmd, copy
//
project environment is set (use 'wipe' to reset)
```

All files and environment variables can be reset with the `wipe` command
(and rebuild with `source .env/project.sh`).

Make sure files are in the proper place. Open a terminal and navigate
to the project directory:

```sh
find src tests resources
```

Output:

```
src
src/application
src/application/Application.java
src/application/package-info.java
src/module-info.java
tests
tests/application
tests/application/Application_0_always_pass_Tests.java
resources
resources/application.properties
resources/logging.properties
resources/META-INF
resources/META-INF/MANIFEST.MF
```


&nbsp;
## 3. Building the Application

The [*Build-Process*](https://www.techbin.com/searchsoftwarequality/definition/build)
consists of operations such as:

 - compile source code

 - compile tests

 - build javadocs

 - package the application as '.jar' file

Command `show` shows operations that are available for the *Build-Process*:

```sh
show
show --all
```

```
source | project:
  source .env/project.sh

classpath:
  echo $CLASSPATH | tr "[;:]" "\n"

compile:
  javac $(find src/main -name '*.java') -d bin/classes; \
  copy src/resources bin/resources

compile-tests:
  javac $(find src/tests -name '*.java') -d bin/test-classes; \
  copy src/resources bin/resources

resources:
  copy src/resources bin/resources

run:
  java application.Application

run-tests:
  java -jar libs/junit-platform-console-standalone-1.9.2.jar \
    $(eval echo $JUNIT_OPTIONS) --scan-class-path

javadoc:
  javadoc -d docs $(eval echo $JDK_JAVADOC_OPTIONS) \
    $(cd src/main; find . -type f | xargs dirname | uniq | cut -c 3-)

clean:
  rm -rf bin logs docs

wipe:
```

Execute build steps with the `build` or `mk` (make) command:

```sh
mk compile                        # compile source code
mk compile-tests                  # compile test code

mk clean compile compile-tests    # execute all commands in order
```

The last command is called a *clean build*. It clears the `bin` directory,
removes all content and re-compiles all source code.

The result is in the `bin` directory:

```sh
find bin
```

Output

```
bin
bin/classes
bin/classes/application
bin/classes/application/Application.class
bin/classes/application/Factorizer.class
bin/classes/application/package_info.class
bin/classes/module-info.class
bin/resources
bin/resources/application.properties
bin/resources/logging.properties
bin/test-classes
bin/test-classes/application
bin/test-classes/application/Application_0_always_pass_Tests.class
bin/test-classes/application/Factorizer_Tests.class
```


&nbsp;
## 4. Running the Application

After building the application, it can be run using the `run` command
and passing a number `n` to factorize.

```sh
mk run a b c
```

Output:

```
java application.Application a b c
Hello, Application
arg: a
arg: b
arg: c
done.
```


&nbsp;
## 5. Running JUnit Tests

[JUnit](https://www.codeflow.site/de/article/junit-assertions#_4_junit_5_assertions)
is a widely used framework for Unit-testing.

JUnit is available in the project through:

- libraries in [libs/junit](), e.g.
  [junit-jupiter-api-5.9.3.jar](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)

- and the Test-Runner that executes JUnit tests
  [junit-platform-console-standalone-1.9.2.jar](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone)

Run JUnit-Tests in the IDE and in the terminal with:

```sh
mk compile-tests run-tests
```

Output:

```
├─ JUnit Jupiter ✔
│  └─ Application_0_always_pass_Tests ✔
│     ├─ test_001_always_pass() ✔
│     └─ test_002_always_pass() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 181 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         2 tests found           ]
[         0 tests skipped         ]
[         2 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]   <-- 5 tests successful
[         0 tests failed          ]   <-- 0 tests failed
done.
```

Run JUnit-Tests also in your IDE.


&nbsp;
## 6. Generating Javadoc

Build the javadoc for the project. Customize your name as author in
[package-info.java](https://gitlab.bht-berlin.de/sgraupner/setup.se2/-/blob/main/src/main/application/package-info.java?ref_type=heads).

```sh
mk javadoc
```

Output:

```
Loading source files for package application...
Constructing Javadoc information...
Creating destination directory: "docs\"
Building index for all the packages and classes...
Standard Doclet version 21+35-LTS-2513
Building tree for all the packages and classes...
Generating docs\se1_play\application\Application.html...
Generating docs\se1_play\application\package-summary.html...
Generating docs\se1_play\application\package-tree.html...
Generating docs\se1_play\module-summary.html...
Generating docs\overview-tree.html...
Building index for all classes...
Generating docs\allclasses-index.html...
Generating docs\allpackages-index.html...
Generating docs\index-all.html...
Generating docs\search.html...
Generating docs\index.html...
Generating docs\help-doc.html...
done.
```

Open `docs/index.html` in a browser.


&nbsp;
## 7. Packaging the Application

*Packaging* is part of the *Build-Process* in which a `.jar` file (jar: Java archive)
is created that contains all compiled classes and a
[MANIFEST.MF](https://gitlab.bht-berlin.de/sgraupner/setup.se2/-/blob/main/src/resources/META-INF/MANIFEST.MF?ref_type=heads) - file
that describes the class to execute (Main-Class: application.Application).

```sh
mk jar
```
or:
```sh
mk package
```

packages class files and creates the resulting `application-1.0.0-SNAPSHOT.jar`
in the `bin` directory.

```sh
ls -la bin
```

Output:

```
total 16
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:55 .
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:54 ..
-rw-r--r-- 1 svgr2 Kein 5397 Apr 14 21:55 application-1.0.0-SNAPSHOT.jar
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:50 classes
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:26 resources
drwxr-xr-x 1 svgr2 Kein    0 Apr 14 21:50 test-classes
```


&nbsp;
## 8. Running the Packaged Application

Test the jar-file with:

```sh
mk run-jar n=100 n=1000
```
or execute directly by java:
```sh
java -jar bin/application-1.0.0-SNAPSHOT.jar n=100 n=1000
```

Output:

```
java -jar bin/application-1.0.0-SNAPSHOT.jar n=100 n=1000
Hello, Application
arg: n=100
arg: n=1000
done.
```

The packaged .jar file can now be distributed.


&nbsp;
## 9. How does *Build* work?

In previous sections, commands `mk`, `make` or `build` were used to
perform the various steps of the build process.

For example:

```sh
make compile compile-tests    # or short: mk compile compile-tests
```

issues the commands to compile sources and tests using the Java compiler
`javac` plus copying resources over to bin:

```
  javac $(find src/main -name '*.java') -d bin/classes; \
  copy src/resources bin/resources
  javac $(find src/tests -name '*.java') -d bin/test-classes; \
  copy src/resources bin/resources
```

Similarly, the `javadoc` short command is used to build the Java
documentation:

```sh
build javadoc
```

issues the command for the Javadoc compiler to create the documentation
in the `docs` directory using values in the `JDK_JAVADOC_OPTIONS`
environment variable:

```
javadoc -d docs $(eval echo $JDK_JAVADOC_OPTIONS)
```

The full list of command short-cuts (`compile`, `compile-tests`) and
their executable, full commands can be seen with:

```sh
show --all                    # list all shorts and full commands
```

When the project is wiped:

```
build javadoc
build: command not found

wipe
wipe: command not found
```

Find answers to these quesitons:

1) Where do these magic commands: `make`, `mk`, `build`, `wipe`, etc. ?

1) Where are they defined?

1) What are they (installed programs, scripts, Java code, ... )?

1) In which programming language are they implemented?

1) What does the following fragment do?

    ```sh
    local module_jars=( $(find libs/*/ -name '*.jar' 2>/dev/null) )
    local entries=(
        "bin/classes"
        "bin/test-classes"
        "bin/resources"
        ${module_jars[@]}
    )

    [ "$(uname | grep 'CYGWIN\|MINGW')" ] && local sep=';' || local sep=':'

    if [ -z "$CLASSPATH" ]; then
        export CLASSPATH=""
        for entry in ${entries[@]}; do
            [ ! -z "$CLASSPATH" ] && CLASSPATH+=${sep}
            CLASSPATH+=$entry
        done
    fi
    ```
