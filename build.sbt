import AssemblyKeys._

import java.util.Date

name := "roguelike"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.scalafx" % "scalafx_2.10" % "8.0.0-R4",
  "org.scalaz" % "scalaz-core_2.10" % "7.0.6"
)

jarName in assembly := "roguelike-" + version.value + ".jar"

mainClass in assembly := Some("controller.World")

assemblySettings

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))