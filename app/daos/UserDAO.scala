package daos

import model.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Users = TableQuery[ProduktTable]

  def all(): Future[Seq[User]] = db.run(Users.result)

  def insert(user: User): Future[Unit] = db.run(Users += user).map { _ => () }

  private class ProduktTable(tag: Tag) extends Table[User](tag, "PRODUKT") {

    def name = column[String]("NAME", O.PrimaryKey)
    def color = column[Int]("PRICE")

    def * = (name, color) <> (User.tupled, User.unapply)
  }
}