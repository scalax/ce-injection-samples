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

  val Module1Resources: Resource[IO, ZEnvironment[Module1Sum]] = async[Resource[IO, *]] {
    val _dbResourcesProvide: DBResourcesProvide = new DBResourcesProvideImpl
    implicit val _dbResources                   = _dbResourcesProvide.transactor.await
    val _projectConf: ProjectConf               = new ProjectConfImpl
    implicit val _conf                          = Resource.eval(_projectConf.configIO).await
    implicit val _initData: InitData            = new InitDataImpl
    val _initDBImpl: InitDB                     = new InitDBImpl
    Resource.eval(_initDBImpl.execInitDataAction).await
    val env = ZEnvironment(implicitly[Transactor[IO]], implicitly[Conf], implicitly[InitData])
    env
  }

  val AppModule: Resource[IO, AppRoutes] = async[Resource[IO, *]] {
    implicit val env: ZEnvironment[Module1Sum]       = Module1Resources.await
    implicit val _listPetsServices: ListPetsServices = new ListPetsServicesImpl
    new AppRoutesImpl
  }

}

trait ZEnvHelper {
  implicit def fetchModelFromZEnv[T](implicit env: zio.ZEnvironment[T], tag: zio.Tag[T]): Id[T] = env.get(tag)
}
