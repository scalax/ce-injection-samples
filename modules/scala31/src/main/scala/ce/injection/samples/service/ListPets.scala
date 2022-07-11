package ce.injection.samples
package service

import doobie.*
import doobie.implicits.given
import cats.*
import cats.data.*
import cats.effect.*
import cats.implicits.given

import model.Cat

class ListPetsServices(xa: Transactor[IO]):

  val y = xa.yolo
  import y.*

  def listCats: IO[Seq[Cat]] = sql"""
    select id, name, age from cat
  """.query[Cat].to[List].transact(xa)

end ListPetsServices

class ListPetsServicesImpl(using Transactor[IO]) extends ListPetsServices(summon)
