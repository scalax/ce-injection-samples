CustomSettings.scala2Settings

libraryDependencies ++= Dependencies.http4s
libraryDependencies ++= Dependencies.cats
libraryDependencies ++= Dependencies.doobie
libraryDependencies ++= Dependencies.h2
libraryDependencies ++= Dependencies.catsCPS
libraryDependencies ++= Dependencies.zio2
libraryDependencies ++= Dependencies.simpleLogger
libraryDependencies ++= Dependencies.macwire

addCompilerPlugin(Dependencies.kindProjector)

enablePlugins(SbtTwirl)
