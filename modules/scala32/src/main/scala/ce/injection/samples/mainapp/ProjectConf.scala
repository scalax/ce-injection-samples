package ce.injection.samples
package mainapp

import zio.config.magnolia.descriptor
import zio.config.typesafe.TypesafeConfig
import zio.{IO as _, *}
import cats.effect.*
import cats.*
import zio.interop.*
import zio.interop.catz.*
import zio.interop.catz.implicits.given

import model.Conf

class ProjectConf:

  private val desuConfigAutomatic = descriptor[Conf]
  private val layer               = TypesafeConfig.fromResourcePath(desuConfigAutomatic)
  private val desuConfigZIO       = ZIO.service[Conf].provide(layer)

  val configIO: IO[Conf] = desuConfigZIO.toEffect[IO]

end ProjectConf

class ProjectConfImpl extends ProjectConf
