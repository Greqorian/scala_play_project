package daos

import model.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Users = TableQuery[UsersTable]

  def all(): Future[Seq[User]] = db.run(Users.result)

  def insert(users: User): Future[Unit] = db.run(Users += users).map { _ => () }

  private class UsersTable(tag: Tag) extends Table[User](tag, "USER") {

    def name = column[String]("NAME", O.PrimaryKey)
    def color = column[Int]("PRICE")

    def * = (name, color) <> (User.tupled, User.unapply)
  }
}