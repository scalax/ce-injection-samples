package ce.injection.samples
package service

import doobie._
import doobie.implicits._
import cats._
import cats.data._
import cats.effect._
import cats.implicits._

import model.Cat

class ListPetsServices(xa: Transactor[IO]) {

  val y = xa.yolo
  import y._

  def listCats: IO[Seq[Cat]] = sql"""
    select id, name, age from cat
  """.query[Cat].to[List].transact(xa)

}

class ListPetsServicesImpl(implicit xa: Id[Transactor[IO]]) extends ListPetsServices(implicitly)
