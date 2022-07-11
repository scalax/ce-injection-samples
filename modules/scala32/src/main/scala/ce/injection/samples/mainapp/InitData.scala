package ce.injection.samples
package mainapp

import cats._
import cats.effect._
import cats.implicits._
import doobie._
import doobie.hikari.HikariTransactor
import doobie.implicits._
import doobie.util.ExecutionContexts

import scala.concurrent.ExecutionContext

import model.Cat
import model.Conf

class InitData(conf: Conf) {

  val initCat: Seq[Cat] = for (s <- conf.ce.injection.samples.project.init.h2.cats) yield Cat(id = -1, name = s.name, age = s.age)

}

class InitDataImpl(implicit conf: Id[Conf]) extends InitData(implicitly)
