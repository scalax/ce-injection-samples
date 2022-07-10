# ce-injection-samples
This project try to show a new injection way in Scala2 and Scala3.

In Scala2 we use cats-effect-cps. There is a plan use cats and macwire.

In Scala3 we use for comprehensions embbed by given. And also we will use cats-effect-cps in Scala3.2 with [fewerBraces](https://dotty.epfl.ch/docs/reference/other-new-features/indentation.html#variant-indentation-marker--for-arguments).

Now try the [first version](./modules/scala213) (Scala2) with
```scala
sbt>scala213/reStart
```
and open
```
http://127.0.0.1:8080/list
```
