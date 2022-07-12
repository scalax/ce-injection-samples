package ce.injection.samples
package mainapp

import doobie.*
import cats.implicits.given
import cats.effect.*
import cats.*
import zio.{IO as _, *}
import cats.effect.implicits.given
import cats.effect.cps.*

import model.Conf
import routes.{AppRoutes, AppRoutesImpl}
import service.{ListPetsServices, ListPetsServicesImpl}

object MainAppInjection:

  type Module1Sum = Transactor[IO] with Conf with InitData

  val Module1Resources: Resource[IO, ZEnvironment[Module1Sum]] = async[Resource[IO, *]] {
    val dbResourcesProvide: DBResourcesProvide = new DBResourcesProvideImpl
    given Transactor[IO]                       = dbResourcesProvide.transactor.await
    val projectConf: ProjectConf               = new ProjectConfImpl
    given Conf                                 = Resource.eval(projectConf.configIO).await
    given InitData                             = new InitDataImpl
    val initDB: InitDB                         = new InitDBImpl
    val initResult: Seq[Int]                   = Resource.eval(initDB.execInitDataAction).await
    ZEnvironment(implicitly[Transactor[IO]], implicitly[Conf], implicitly[InitData])
  }

  val AppModule: Resource[IO, AppRoutes] = async[Resource[IO, *]] {
    given ZEnvironment[Module1Sum] = Module1Resources.await
    given ListPetsServices         = new ListPetsServicesImpl
    new AppRoutesImpl
  }

end MainAppInjection

given [ModelTag: Tag](using ZEnvironment[ModelTag]): ModelTag = summon[ZEnvironment[ModelTag]].get
