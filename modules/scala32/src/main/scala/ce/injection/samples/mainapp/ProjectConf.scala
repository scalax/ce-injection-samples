package ce.injection.samples
package mainapp

import zio.config.magnolia.descriptor
import zio.config.typesafe.TypesafeConfig
import zio.{IO => _, _}
import cats.effect._
import cats._
import zio.interop._
import zio.interop.catz._
import zio.interop.catz.implicits._

import model.Conf

class ProjectConf {

  private val desuConfigAutomatic = descriptor[Conf]
  private val layer               = TypesafeConfig.fromResourcePath(desuConfigAutomatic)
  private val desuConfigZIO       = ZIO.service[Conf].provide(layer)

  val configIO: IO[Conf] = desuConfigZIO.toEffect[IO]

}

class ProjectConfImpl extends ProjectConf
