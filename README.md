# ce-injection-samples
This project try to show a new injection way in Scala2 and Scala3.

In Scala2 we use [ThoughtWorksInc/dsl-domains-cats](https://github.com/ThoughtWorksInc/dsl-domains-cats) to handle many Resources in cats-effect.
And we use scala implicit to implement some injection like macwire's wire[T].
At least we use zio.ZEnvironment to create a module.

(Wip) In Scala3 we use for comprehensions embbed by given. And also we will use cats-effect-cps in Scala3.2 with [fewerBraces](https://dotty.epfl.ch/docs/reference/other-new-features/indentation.html#variant-indentation-marker--for-arguments).

Now try the [first version](./modules/scala213) (Scala2) with
```scala
sbt>run21
```
and visit
```
http://127.0.0.1:8080/list
```
The stop command is `sbt>sotp21`

[Link to the main file](./modules/scala213/src/main/scala/ce/injection/samples/mainapp/MainAppInjection.scala)
