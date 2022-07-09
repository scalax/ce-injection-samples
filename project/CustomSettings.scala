import sbt._
import sbt.Keys._

object CustomSettings {

  val scalaVersion_213 = "2.13.8"
  val scalaVersion_31   = "3.1.2"
  val scalaVersion_32   = "3.2.1-RC1-bin-20220624-28faa0f-NIGHTLY"

  val scala2Config  = Seq(scalaVersion := scalaVersion_213, scalacOptions ++= Seq("-feature", "-deprecation", "-Ymacro-annotations"))
  val scala31Config = Seq(scalaVersion := scalaVersion_31, scalacOptions ++= Seq("-feature", "-deprecation", "-Ykind-projector"))
  val scala32Config = Seq(scalaVersion := scalaVersion_32, scalacOptions ++= Seq("-feature", "-deprecation", "-Ykind-projector"))

  val fmtConfig = org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile := true

  val scala2Settings = fmtConfig +: scala2Config
  val scala31Settings = fmtConfig +: scala31Config
  val scala32Settings = fmtConfig +: scala32Config

}
