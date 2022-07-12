CustomSettings.scala2Settings

val modulesFile = file(".") / "modules"

val scala213 = project in modulesFile / "scala213"
val scala31  = project in modulesFile / "scala31"
val scala32  = project in modulesFile / "scala32"

val run2 = inputKey[Unit]("run2")
run2 := (scala213 / Compile / reStart).evaluated

val stop2 = taskKey[Unit]("stop2")
stop2 := (scala213 / Compile / reStop).value

val run31 = inputKey[Unit]("run31")
run31 := (scala31 / Compile / reStart).evaluated

val stop31 = taskKey[Unit]("stop31")
stop31 := (scala31 / Compile / reStop).value

val run32 = inputKey[Unit]("run32")
run32 := (scala32 / Compile / reStart).evaluated

val stop32 = taskKey[Unit]("stop32")
stop32 := (scala32 / Compile / reStop).value
