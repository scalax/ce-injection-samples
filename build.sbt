CustomSettings.scala2Settings

val modulesFile = file(".") / "modules"

val scala213 = project in modulesFile / "scala213"
val scala31  = project in modulesFile / "scala31"

val run21 = inputKey[Unit]("run21")
run21:= (scala213 / Compile / reStart).evaluated

val stop21 = taskKey[Unit]("stop21")
stop21 := (scala213 / Compile / reStop).value

val run31 = inputKey[Unit]("run31")
run31:= (scala31 / Compile / reStart).evaluated

val stop31 = taskKey[Unit]("stop31")
stop31 := (scala31 / Compile / reStop).value