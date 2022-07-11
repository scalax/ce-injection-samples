package ce.injection.samples
package mainapp

import doobie._
import cats._
import cats.implicits._
import cats.effect._
import zio.ZEnvironment
import service.{ListPetsServices, ListPetsServicesImpl}
import routes.{AppRoutes, AppRoutesImpl}
import model.Conf

import com.thoughtworks.dsl.keywords.Monadic._
import com.thoughtworks.dsl.domains.cats._
import com.thoughtworks.dsl.Dsl.reset

object MainAppInjection extends ZEnvHelper {

  type Module1Sum = Transactor[IO] with Conf with InitData

  val Module1Resources: Resource[IO, ZEnvironment[Module1Sum]] = Resource.pure {
    val _dbResourcesProvide: DBResourcesProvide = new DBResourcesProvideImpl
    implicit val _dbResources: Transactor[IO]   = !_dbResourcesProvide.transactor
    val _projectConf: ProjectConf               = new ProjectConfImpl
    implicit val _conf: Conf                    = !Resource.eval(_projectConf.configIO)
    implicit val _initData: InitData            = new InitDataImpl
    val _initDBImpl: InitDB                     = new InitDBImpl
    val _execResult: Seq[Int]                   = !Resource.eval(_initDBImpl.execInitDataAction)
    val env                                     = ZEnvironment(implicitly[Transactor[IO]], implicitly[Conf], implicitly[InitData])
    env
  }: @reset

  val AppModule: Resource[IO, AppRoutes] = Resource.pure {
    implicit val module1: ZEnvironment[Module1Sum]   = !Module1Resources
    implicit val _listPetsServices: ListPetsServices = new ListPetsServicesImpl
    new AppRoutesImpl
  }: @reset

}

trait ZEnvHelper {
  implicit def fetchModelFromZEnv[T](implicit env: zio.ZEnvironment[T], tag: zio.Tag[T]): Id[T] = env.get(tag)
}
