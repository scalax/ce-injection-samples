package ce.injection.samples
package mainapp

import doobie._
import cats._
import cats.implicits._
import cats.effect._
import cats.effect.cps._
import zio.ZEnvironment
import service.{ListPetsServices, ListPetsServicesImpl}
import routes.{AppRoutes, AppRoutesImpl}
import model.Conf

object MainAppInjection extends ZEnvHelper {

  type Module1Sum = Transactor[IO] with Conf with InitData

  val Module1Resources: Resource[IO, ZEnvironment[Module1Sum]] = {
    val _dbResourcesProvide: DBResourcesProvide = new DBResourcesProvideImpl
    _dbResourcesProvide.transactor.flatMap { implicit _dbResources =>
      val _projectConf: ProjectConf = new ProjectConfImpl
      Resource.eval(_projectConf.configIO).flatMap { implicit _conf =>
        implicit val _initData: InitData = new InitDataImpl
        val _initDBImpl: InitDB          = new InitDBImpl
        Resource.eval(_initDBImpl.execInitDataAction).map { _ =>
          val env = ZEnvironment(implicitly[Transactor[IO]], implicitly[Conf], implicitly[InitData])
          env
        }
      }
    }
  }

  val AppModule: Resource[IO, AppRoutes] = Module1Resources.map { implicit env =>
    implicit val _listPetsServices: ListPetsServices = new ListPetsServicesImpl
    new AppRoutesImpl
  }

}

trait ZEnvHelper {
  implicit def fetchModelFromZEnv[T](implicit env: zio.ZEnvironment[T], tag: zio.Tag[T]): Id[T] = env.get(tag)
}
