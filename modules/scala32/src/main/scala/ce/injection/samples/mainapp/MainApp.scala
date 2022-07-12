package ce.injection.samples
package mainapp

import cats.*
import cats.data.*
import cats.effect.*
import cats.implicits.given
import org.http4s.*
import org.http4s.dsl.io.*
import com.comcast.ip4s.{ipv4, port}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.given

object MainApp extends IOApp:

  val serverBuilding = EmberServerBuilder.default[IO].withHost(ipv4"0.0.0.0").withPort(port"8080")

  val serverResource = for
    routesApp <- MainAppInjection.AppModule
    server    <- serverBuilding.withHttpApp(routesApp.routes.orNotFound).build
  yield server

  override def run(args: List[String]): IO[ExitCode] = serverResource.useForever.as(ExitCode.Success)

end MainApp
