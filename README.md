# ce-injection-samples
This project try to show a new injection way in Scala2 and Scala3.

It's a http4s web application. Test page is
```
http://127.0.0.1:8080/list
```

In Scala 2 we use [ThoughtWorksInc/dsl-domains-cats](https://github.com/ThoughtWorksInc/dsl-domains-cats) to handle many Resources in cats-effect.
And we use scala implicit syntax to implement some injection like macwire's wire[T].
At least we use zio.ZEnvironment to create a module and use cats.Id to fix a type issue only in Scala 2.

[Link to the main file](./modules/scala213/src/main/scala/ce/injection/samples/mainapp/MainAppInjection.scala)


Start and stop command is
```scala
sbt>run2
sbt>stop2
```

In Scala 3.1 we use [typelevel/cats-effect-cps](https://github.com/typelevel/cats-effect-cps) to replace `dsl-domains-cats` since `dsl-domains-cats` only support Scala2.
We alse zio.ZEnvironment to create a module. And the usage of given is more simple.

[Link to the main file](./modules/scala31/src/main/scala/ce/injection/samples/mainapp/MainAppInjection.scala)

Start and stop command is
```scala
sbt>run31
sbt>stop31
```

In Scala 3.2 we just use for comprehensions embbed by given. This shows the powerful syntax of scala.
zio.ZEnvironment is still used for moduling.

[Link to the main file](./modules/scala32/src/main/scala/ce/injection/samples/mainapp/MainAppInjection.scala)

Start and stop command is
```scala
sbt>run32
sbt>stop32
```
