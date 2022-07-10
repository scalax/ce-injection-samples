package ce.injection.samples
package mainapp

import model.Cat

import doobie._
import doobie.implicits._
import doobie.util.ExecutionContexts
import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import doobie.hikari.HikariTransactor
import cats.effect.cps._
import scala.concurrent.ExecutionContext

class DBResourcesProvide {

  val transactor: Resource[IO, HikariTransactor[IO]] = {
    def toTransactor(ce: ExecutionContext) = HikariTransactor.newHikariTransactor[IO](
      driverClassName = "org.h2.Driver", // driver classname
      url = "jdbc:h2:./h2database",      // connect URL
      user = "sa",                       // username
      pass = "",                         // password
      ce                                 // await connection here
    )

    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](32)
      xa <- toTransactor(ce)
    } yield xa
  }

}

class DBResourcesProvideImpl extends DBResourcesProvide

class InitDB(xa: Transactor[IO], initData: InitData) {

  val y = xa.yolo
  import y._

  private val dropTable = sql"""
      DROP TABLE IF EXISTS cat
    """.update.run

  private val createTable = sql"""
      CREATE TABLE cat(id int primary key auto_increment, name varchar(20), age int)
    """.update.run

  private def insertCat(cat: Cat) = sql"""
        insert into cat(name, age) values (${cat.name}, ${cat.age})
    """.update.run

  private val initDatabase = (dropTable, createTable).mapN(_ + _).transact(xa)

  private val initDataAction = initData.initCat.map(insertCat).sequence.transact(xa)

  val execInitDataAction: IO[Seq[Int]] = initDatabase *> initDataAction

}

class InitDBImpl(implicit xaProvide: Id[Transactor[IO]], initData: Id[InitData]) extends InitDB(implicitly, implicitly)
