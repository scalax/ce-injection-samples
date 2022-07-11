package ce.injection.samples
package mainapp

import cats.*
import cats.effect.*
import cats.implicits.given
import doobie.*
import doobie.hikari.HikariTransactor
import doobie.implicits.given
import doobie.util.ExecutionContexts

import scala.concurrent.ExecutionContext

import model.Cat
import model.Conf

class InitData(conf: Conf):

  val initCat: Seq[Cat] = for (s <- conf.ce.injection.samples.project.init.h2.cats) yield Cat(id = -1, name = s.name, age = s.age)

end InitData

class InitDataImpl(using Conf) extends InitData(summon)
