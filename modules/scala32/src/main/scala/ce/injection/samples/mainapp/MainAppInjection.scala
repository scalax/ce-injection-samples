package ce.injection.samples
package mainapp

import doobie.*
import cats.implicits.given
import cats.effect.*
import cats.*
import zio.{IO as _, *}
import cats.effect.implicits.given

import model.Conf
import routes.{AppRoutes, AppRoutesImpl}
import service.{ListPetsServices, ListPetsServicesImpl}

object MainAppInjection:

  type Module1Sum = Transactor[IO] with Conf with InitData

  val Module1Resources: Resource[IO, ZEnvironment[Module1Sum]] =
    val dbResourcesProvide: DBResourcesProvide = new DBResourcesProvideImpl

    for
      given Transactor[IO] <- dbResourcesProvide.transactor
      projectConf: ProjectConf = new ProjectConfImpl
      given Conf <- Resource.eval(projectConf.configIO)
      given InitData = new InitDataImpl
      initDB: InitDB = new InitDBImpl
      _: Seq[Int] <- Resource.eval(initDB.execInitDataAction)
    yield ZEnvironment(implicitly[Transactor[IO]], implicitly[Conf], implicitly[InitData])

  val AppModule: Resource[IO, AppRoutes] =
    for given ZEnvironment[Module1Sum] <- Module1Resources
    yield
      given ListPetsServices = new ListPetsServicesImpl
      new AppRoutesImpl

end MainAppInjection

given [ModelTag: Tag](using ZEnvironment[ModelTag]): ModelTag = summon[ZEnvironment[ModelTag]].get
