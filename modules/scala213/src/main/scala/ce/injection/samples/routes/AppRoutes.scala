package ce.injection.samples
package routes

import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import cats.effect.cps._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.twirl._

import service.ListPetsServices
import views.html.ListPets
import model.Conf

class AppRoutes(listPetsServices: ListPetsServices, conf: Conf) {

  def listCatsRoutes = HttpRoutes.of[IO] { case GET -> Root / "list" =>
    val action = for {
      cats   <- listPetsServices.listCats
      result <- Ok(ListPets(cats)(conf))
    } yield result
    action
  }

  val routes: HttpRoutes[IO] = listCatsRoutes

}

class AppRoutesImpl(implicit listPetsServices: Id[ListPetsServices], conf: Id[Conf]) extends AppRoutes(implicitly, implicitly)
