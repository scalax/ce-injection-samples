package ce.injection.samples
package mainapp

import model.Cat

import doobie.*
import doobie.implicits.given
import doobie.util.ExecutionContexts
import cats.*
import cats.data.*
import cats.effect.*
import cats.implicits.given
import doobie.hikari.HikariTransactor
import scala.concurrent.ExecutionContext

class DBResourcesProvide:

  val transactor: Resource[IO, HikariTransactor[IO]] =
    def toTransactor(ce: ExecutionContext) = HikariTransactor.newHikariTransactor[IO](
      driverClassName = "org.h2.Driver", // driver classname
      url = "jdbc:h2:./h2database",      // connect URL
      user = "sa",                       // username
      pass = "",                         // password
      ce                                 // await connection here
    )

    for
      ce <- ExecutionContexts.fixedThreadPool[IO](32)
      xa <- toTransactor(ce)
    yield xa

end DBResourcesProvide

class DBResourcesProvideImpl extends DBResourcesProvide

class InitDB(xa: Transactor[IO], initData: InitData):

  val y = xa.yolo
  import y.*

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

end InitDB

class InitDBImpl(using Transactor[IO], InitData) extends InitDB(summon, summon)
