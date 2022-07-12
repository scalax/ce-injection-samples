CustomSettings.scala2Settings

libraryDependencies ++= Dependencies.http4s
libraryDependencies ++= Dependencies.cats
libraryDependencies ++= Dependencies.doobie
libraryDependencies ++= Dependencies.h2
libraryDependencies ++= Dependencies.zio2
libraryDependencies ++= Dependencies.simpleLogger
libraryDependencies ++= Dependencies.domainsCats

addCompilerPlugin(Dependencies.kindProjector)

addCompilerPlugin(Dependencies.compilerpluginsBangnotation)

enablePlugins(SbtTwirl)
