import sbt._
import sbt.Keys._

object Dependencies {

  object versions {
    val akka                  = "2.6.19"
    val akkaHttp              = "10.2.9"
    val akkaHttpCirce         = "1.40.0-RC2"
    val slf4j                 = "1.8.0-beta4"
    val typeSafeConfig        = "1.4.1"
    val http4s                = "0.23.12"
    val catsEffect            = "3.3.12"
    val catsEffect2           = "2.5.4"
    val circe                 = "0.14.2"
    val kindProjector         = "0.13.2"
    val scalatest             = "3.2.12"
    val macwire               = "2.5.7"
    val mysql                 = "8.0.29"
    val slick                 = "3.4.0-M1"
    val sttp                  = "3.6.2"
    val tapir                 = "1.0.1"
    val zioLogging            = "2.0.0-RC10"
    val jintellitype          = "1.4.0"
    val quill                 = "4.0.0-RC1"
    val distage               = "1.1.0-M4"
    val zio                   = "1.0.15"
    val zio2                  = "2.0.0"
    val zioMagic              = "0.3.11"
    val zioHttp               = "2.0.0-RC9"
    val finch                 = "0.33.0"
    val scalaCollectionCompat = "2.7.0"
    val scalaJava8Compat      = "1.0.2"
    val circeYaml             = "0.2.1"
    val hikariCP              = "5.0.1"
    val zioConfig             = "3.0.1"
    val zioJson               = "0.3.0-RC8"
    val jsoniter              = "2.13.31"
    val tethys                = "0.26.0"
    val monix                 = "3.4.1"
    val pureconfig            = "0.17.1"
    val doobie                = "1.0.0-RC2"
    val zioInteropCats        = "3.3.0"
    val catsCPS               = "0.3.0"
    val h2                    = "2.1.214"
    val domainsCats = "2.0.0"
    val compilerpluginsBangnotation = "1.5.5"
  }

  val simpleLogger = List("org.slf4j" % "slf4j-simple" % versions.slf4j)

  val akkaHttp = List(
    "com.typesafe.akka" %% "akka-actor-typed" % versions.akka,
    "com.typesafe.akka" %% "akka-stream"      % versions.akka,
    "com.typesafe.akka" %% "akka-http"        % versions.akkaHttp,
    // "de.heikoseeberger" %% "akka-http-circe"     % versions.akkaHttpCirce,
    "de.heikoseeberger" %% "akka-http-zio-json"  % versions.akkaHttpCirce,
    "com.typesafe.akka" %% "akka-stream-testkit" % versions.akka,
    "com.typesafe.akka" %% "akka-http-testkit"   % versions.akkaHttp
  )

  val http4s = List(
    "org.http4s" %% "http4s-dsl"          % versions.http4s,
    "org.http4s" %% "http4s-ember-server" % versions.http4s,
    "org.http4s" %% "http4s-ember-client" % versions.http4s,
    "org.http4s" %% "http4s-circe"        % versions.http4s/*,
    "org.http4s" %% "http4s-twirl"        % "0.23.11" exclude ("org.http4s", "http4s-core_2.13") exclude ("com.typesafe.play", "twirl-api_2.13") exclude ("org.typelevel", "*") cross CrossVersion.for3Use2_13*/
  )

  val cats = List("org.typelevel" %% "cats-effect" % versions.catsEffect)

  val catsCPS = List("org.typelevel" %% "cats-effect-cps" % versions.catsCPS)

  val scalatest = List(
    "org.scalactic" %% "scalactic" % versions.scalatest,
    "org.scalatest" %% "scalatest" % versions.scalatest % "test"
  )

  val kindProjector = "org.typelevel" % "kind-projector" % versions.kindProjector cross CrossVersion.full

  def scalaReflect(scalaVersion: String) = "org.scala-lang" % "scala-reflect" % scalaVersion

  val zio = "dev.zio" %% "zio" % versions.zio
  val zio2 = List(
    "dev.zio" %% "zio"                 % versions.zio2,
    "dev.zio" %% "zio-test"            % versions.zio2 % "test",
    "dev.zio" %% "zio-test-sbt"        % versions.zio2 % "test",
    "dev.zio" %% "zio-config"          % versions.zioConfig,
    "dev.zio" %% "zio-config-magnolia" % versions.zioConfig,
    "dev.zio" %% "zio-config-typesafe" % versions.zioConfig,
    "dev.zio" %% "zio-interop-cats"    % versions.zioInteropCats
  )

  val doobie = List("org.tpolecat" %% "doobie-core" % versions.doobie, "org.tpolecat" %% "doobie-hikari" % versions.doobie)

  val scalaCollectionCompat = List("org.scala-lang.modules" %% "scala-collection-compat" % versions.scalaCollectionCompat)

  val hikariCP = "com.zaxxer" % "HikariCP" % versions.hikariCP

  val h2 = List("com.h2database" % "h2" % versions.h2)

  val macwire = List(
    "com.softwaremill.macwire" %% "macros" % versions.macwire % "provided",
    "com.softwaremill.macwire" %% "util"   % versions.macwire,
    "com.softwaremill.macwire" %% "proxy"  % versions.macwire
  )

  val domainsCats = List("com.thoughtworks.dsl" %% "domains-cats" % versions.domainsCats)

  val compilerpluginsBangnotation = "com.thoughtworks.dsl" %% "compilerplugins-bangnotation" % versions.compilerpluginsBangnotation

}
