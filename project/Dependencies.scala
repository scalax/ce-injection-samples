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
    val zio2                  = "2.0.0-RC6"
    val zioMagic              = "0.3.11"
    val zioHttp               = "2.0.0-RC9"
    val finch                 = "0.33.0"
    val scalaCollectionCompat = "2.7.0"
    val scalaJava8Compat      = "1.0.2"
    val circeYaml             = "0.2.1"
    val hikariCP              = "5.0.1"
    val zioConfig             = "3.0.0-RC9"
    val zioJson               = "0.3.0-RC8"
    val jsoniter              = "2.13.31"
    val tethys                = "0.26.0"
    val monix                 = "3.4.1"
    val pureconfig            = "0.17.1"
    val doobie                = "1.0.0-RC2"
    val zioInteropCats = "3.3.0-RC7"
    val catsCPS = "0.3.0"
  }

  val config = List("com.typesafe" % "config" % versions.typeSafeConfig)

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

  val zioJson = List("dev.zio" %% "zio-json" % versions.zioJson)
  val jsoniter = List(
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"   % versions.jsoniter,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % versions.jsoniter
  )

  val tethysJson = List(
    "com.tethys-json" %% "tethys-core"       % versions.tethys,
    "com.tethys-json" %% "tethys-jackson"    % versions.tethys,
    "com.tethys-json" %% "tethys-derivation" % versions.tethys
  )

  val http4s = List(
    "org.http4s" %% "http4s-dsl"          % versions.http4s,
    "org.http4s" %% "http4s-ember-server" % versions.http4s,
    "org.http4s" %% "http4s-ember-client" % versions.http4s,
    "org.http4s" %% "http4s-circe"        % versions.http4s
  )

  val cats  = List("org.typelevel" %% "cats-effect" % versions.catsEffect)
  val cats2 = List("org.typelevel" %% "cats-effect" % versions.catsEffect2)

  val circe = List(
    "io.circe" %% "circe-core"    % versions.circe,
    "io.circe" %% "circe-generic" % versions.circe,
    "io.circe" %% "circe-parser"  % versions.circe
  )

  val macwire = List(
    "com.softwaremill.macwire" %% "macros" % versions.macwire % "provided",
    "com.softwaremill.macwire" %% "util"   % versions.macwire,
    "com.softwaremill.macwire" %% "proxy"  % versions.macwire
  )

  val pureconfig = List("com.github.pureconfig" %% "pureconfig" % versions.pureconfig)

  val catsCPS = List("org.typelevel" %% "cats-effect-cps" % versions.catsCPS)

  val scalatest = List(
    "org.scalactic" %% "scalactic" % versions.scalatest,
    "org.scalatest" %% "scalatest" % versions.scalatest % "test"
  )

  val mysql = List("mysql" % "mysql-connector-java" % versions.mysql)

  val slick = List(
    "com.typesafe.slick" %% "slick"          % versions.slick,
    "com.typesafe.slick" %% "slick-codegen"  % versions.slick,
    "com.typesafe.slick" %% "slick-hikaricp" % versions.slick
  )

  val sttp = List(
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio"  % versions.sttp,
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % versions.sttp
  )

  val zioLogging = "dev.zio" %% "zio-logging" % versions.zioLogging

  val tapir = List(
    "com.softwaremill.sttp.tapir"   %% "tapir-zio"               % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server-zio" % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-openapi-docs"      % versions.tapir,
    "com.softwaremill.sttp.apispec" %% "openapi-circe-yaml"      % versions.circeYaml,
    "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"        % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-sttp-client"       % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui"        % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-redoc"             % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-zio-http-server"   % versions.tapir,
    "com.softwaremill.sttp.tapir"   %% "tapir-sttp-stub-server"  % versions.tapir
  )

  val monix = List("io.monix" %% "monix" % versions.monix)

  val jintellitype = "com.melloware" % "jintellitype" % versions.jintellitype

  val thumbnailator = "net.coobird"  % "thumbnailator"   % "0.4.14"
  val javacv        = "org.bytedeco" % "javacv-platform" % "1.5.6"

  val kindProjector = "org.typelevel" % "kind-projector" % versions.kindProjector cross CrossVersion.full

  val quill = List(
    "io.getquill"          %% "quill-codegen-jdbc" % versions.quill,
    "io.getquill"          %% "quill-jasync-mysql" % versions.quill,
    "io.github.kitlangton" %% "zio-magic"          % versions.zioMagic
  )
  def scalaReflect(scalaVersion: String) = "org.scala-lang" % "scala-reflect" % scalaVersion

  lazy val javaFXModules = List("base", "controls", "fxml", "graphics", "media", "swing", "web")

  val scalafx = {
    // Add dependency on ScalaFX library
    val fx = "org.scalafx" %% "scalafx" % "16.0.0-R25"

    // Determine OS version of JavaFX binaries
    lazy val osName = System.getProperty("os.name") match {
      case n if n.startsWith("Linux")   => "linux"
      case n if n.startsWith("Mac")     => "mac"
      case n if n.startsWith("Windows") => "win"
      case _                            => throw new Exception("Unknown platform!")
    }

    // Add dependency on JavaFX libraries, OS dependent
    val javafx = javaFXModules.map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
    fx :: javafx
  }

  val distage = "io.7mind.izumi" %% "distage-core" % versions.distage

  val zio = "dev.zio" %% "zio" % versions.zio
  val zio2 = List(
    "dev.zio" %% "zio"                 % versions.zio2,
    "dev.zio" %% "zio-test"            % versions.zio2 % "test",
    "dev.zio" %% "zio-test-sbt"        % versions.zio2 % "test",
    "dev.zio" %% "zio-config"          % versions.zioConfig,
    "dev.zio" %% "zio-config-magnolia" % versions.zioConfig,
    "dev.zio" %% "zio-config-typesafe" % versions.zioConfig,
    "dev.zio" %% "zio-interop-cats" % versions.zioInteropCats,
  )

  val doobie = List("org.tpolecat" %% "doobie-core" % versions.doobie,  "org.tpolecat" %% "doobie-hikari" % versions.doobie)

  val zioHttp = Seq("io.d11" %% "zhttp" % versions.zioHttp, "io.d11" %% "zhttp-test" % versions.zioHttp % Test)

  val quill_scala3 = Seq(
    "io.getquill" %% "quill-jdbc"     % versions.quill,
    "io.getquill" %% "quill-jdbc-zio" % versions.quill
  )

  val finch = Seq(
    "com.github.finagle" %% "finchx-core"    % versions.finch,
    "com.github.finagle" %% "finchx-generic" % versions.finch
  )

  val scalaCollectionCompat = List("org.scala-lang.modules" %% "scala-collection-compat" % versions.scalaCollectionCompat)

  val hikariCP = "com.zaxxer" % "HikariCP" % versions.hikariCP

}
