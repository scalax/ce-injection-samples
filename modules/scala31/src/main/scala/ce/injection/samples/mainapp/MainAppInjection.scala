package ce.injection.samples
package mainapp

import doobie.*
import cats.implicits.*
import cats.effect.*
import cats.*
import zio.{IO as _, *}
import cats.effect.implicits.*
import cats.effect.cps.*

import model.Conf
import routes.{AppRoutes, AppRoutesImpl}
import service.{ListPetsServices, ListPetsServicesImpl}

object MainAppInjection:

  type Module1Sum = Transactor[IO] with Conf with InitData

  val Module1Resources: Resource[IO, ZEnvironment[Module1Sum]] =
    val dbResourcesProvide: DBResourcesProvide = new DBResourcesProvideImpl
    val projectConf: ProjectConf               = new ProjectConfImpl
    val a: Resource[IO, Conf]                  = Resource.eval(projectConf.configIO)

    for
      given Transactor[IO] <- dbResourcesProvide.transactor
      c: Conf              <- a // Resource.eval(projectConf.configIO)
      given Conf         = c
      initData: InitData = new InitDataImpl
      initDBImpl: InitDB = new InitDBImpl
      a <- Resource.eval(initDBImpl.execInitDataAction)
    yield
      a: Seq[Int]
      val env: ZEnvironment[Module1Sum] = ZEnvironment(implicitly[Transactor[IO]], c, initData)
      env

  val AppModule: Resource[IO, AppRoutes] =
    for b <- Module1Resources
    yield
      given ZEnvironment[Module1Sum] = b
      given ListPetsServices         = new ListPetsServicesImpl
      new AppRoutesImpl

end MainAppInjection

given [ModelTag: Tag](using ZEnvironment[ModelTag]): ModelTag = summon[ZEnvironment[ModelTag]].get
