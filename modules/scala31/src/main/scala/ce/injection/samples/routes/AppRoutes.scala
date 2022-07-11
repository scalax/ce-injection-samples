package ce.injection.samples
package routes

import cats.*
import cats.data.*
import cats.effect.*
import cats.implicits.given
import org.http4s.*
import org.http4s.dsl.io.*
// import org.http4s.twirl.*

import service.ListPetsServices
// import views.html.ListPets
import model.Conf

class AppRoutes(listPetsServices: ListPetsServices, conf: Conf):

  def listCatsRoutes = HttpRoutes.of[IO] { case GET -> Root / "list" =>
    for
      cats   <- listPetsServices.listCats
      result <- Ok( /*ListPets(cats)(conf)*/ "22")
    yield result
  }

  val routes: HttpRoutes[IO] = listCatsRoutes

end AppRoutes

class AppRoutesImpl(using ListPetsServices, Conf) extends AppRoutes(summon, summon)
