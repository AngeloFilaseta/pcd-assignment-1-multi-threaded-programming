# README #

# !! Development !!

**THIS IS REALLY IMPORTANT**: Don't skip me!
Make sure you're using a 1.8 JVM.
If you're using Java 8 you're probably fine. Java 11? Not fine.
Check what JDK your system is using and switch to an older version if needed.

## JPF
Java Path Finder Library has been included in this Repository, You can find it inside the "JPF" folder.
#### Usage
To test the application with JPF the syntax is the following:
For the SEQUENTIAL App use the following syntax:
```shell script
java -jar JPF/jpf-core/build/RunJPF.jar +classpath=app/build/classes/java/main controller.apps.AppSequential
```
For the CONCURRENT App use the following syntax:
```shell script
java -jar JPF/jpf-core/build/RunJPF.jar +classpath=app/build/classes/java/main controller.apps.AppConcurrent
```
You should be fine by changing the class name in case of other class tests. The classpath should not change.

## GUI Auto-generated Code
Intellij automatically generates some code for the GUI styling when editing the .form file.
Some problem may occur when doing this on a Gradle Projects.
If the GUI is not changing at all when editing that file refers to the first answer this link:
[StackOverflow Thread](https://stackoverflow.com/questions/55844177/intellij-swing-gui-not-compiling-because-of-gradle)

## Report
The project report is available on Overleaf, note that this is a private repository and only authorized users can edit the report:

[Overleaf Link](https://it.overleaf.com/5688533281rxmmmvnqsxpm)
