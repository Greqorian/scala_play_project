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

     def key = column[Int]("KEY", O.PrimaryKey)
    def name = column[String]("NAME")
    def surname = column[String]("SURNAME")
    def email = column[String]("EMAIL")
    def password = column[String]("PASSWORD")
     def street = column[String]("STREET")
      def city = column[String]("CITY")
      def phone = column[Int]("PHONE")
       def birthdate = column[Int]("BIRTHDATE")

    def * = (key, name, surname, email, password, street, city, phone, birthdate) <> (User.tupled, User.unapply)
  }
}