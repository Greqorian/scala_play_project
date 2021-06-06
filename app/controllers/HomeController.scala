package controllers

// import daos.ProduktDAO
// import model.Produkt
import daos.UserDAO
import model.User

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject() (
    userDao: UserDAO,
    controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext)
    extends AbstractController(controllerComponents) {

  def index() = Action.async {
    userDao.all().map { case (users) => Ok(views.html.index(users)) }
  }

  def table() = Action.async {
    userDao.all().map { case (users) => Ok(views.html.table(users)) }
  }

  def env() = Action { implicit request: Request[AnyContent] =>
    Ok("Nothing to see here")
  //Ok(System.getenv("JDBC_DATABASE_URL"))
  }  


  val userForm = Form(
    mapping(
      "id" -> number(),
      "name" -> text(),
      "surname" -> text(),
      "email" -> text(),
      "password" -> text(),
      "street" -> text(),
      "city" -> text()

    )(User.apply)(User.unapply)
  )

  def insertUser = Action.async { implicit request =>
    val user: User = userForm.bindFromRequest.get
    userDao.insert(user).map(_ => Redirect(routes.HomeController.index))
  }
}
