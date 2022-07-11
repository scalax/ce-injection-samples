package ce.injection.samples
package mainapp

import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.dsl.io._
import com.comcast.ip4s._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._

object MainApp extends IOApp {

  val serverBuilding = EmberServerBuilder.default[IO].withHost(ipv4"0.0.0.0").withPort(port"8080")

  val serverResource = for {
    routesApp <- MainAppInjection.AppModule
    server    <- serverBuilding.withHttpApp(routesApp.routes.orNotFound).build
  } yield server

  override def run(args: List[String]): IO[ExitCode] = serverResource.useForever.as(ExitCode.Success)

}
