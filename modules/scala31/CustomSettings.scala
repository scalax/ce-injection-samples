package org.scalax.sbt

import sbt._
import sbt.Keys._

object CustomSettings {

  val scalaVersion_213 = "2.13.8"
  val scalaVersion_31   = "3.1.2"
  val scalaVersion_32   = "3.2.1-RC1-bin-20220624-28faa0f-NIGHTLY"

  val scalaConfig  = Seq(scalaVersion := scalaVersion_213, scalacOptions ++= Seq("-feature", "-deprecation", "-Ymacro-annotations"))
  val scala3Config = Seq(scalaVersion := scalaVersion_3, scalacOptions ++= Seq("-feature", "-deprecation", "-Ykind-projector"))

  val fmtConfig = org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile := true

}
