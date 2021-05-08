// package controllers

// import javax.inject._
// import play.api._
// import play.api.mvc._

// import daos.ProduktDAO
// import model.Produkt
// import play.api.data.Form
// import play.api.data.Forms.{mapping, number, text}
// /**
//  * This controller creates an `Action` to handle HTTP requests to the
//  * application's home page.
//  */
// import scala.concurrent.ExecutionContext

// @Singleton
// class HomeController @Inject() (produktDao: ProduktDAO, controllerComponents: ControllerComponents)
//                                (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

//   def index() = Action.async {
//     produktDao.all().map { case (produkte) => Ok(views.html.index(produkte)) }
//   }

//   def env() = Action { implicit request: Request[AnyContent] =>
    
//     Ok("Nothing to see here")
//     //Ok(System.getenv("JDBC_DATABASE_URL"))
//   }

//   val produktForm = Form(
//     mapping(
//       "name" -> text(),
//       "price" -> number())(Produkt.apply)(Produkt.unapply))

//   def insertProdukt = Action.async { implicit request =>
//     val produkt: Produkt = produktForm.bindFromRequest.get
//     produktDao.insert(produkt).map(_ => Redirect(routes.HomeController.index))
//   }
// }

package controllers

import daos.ProduktDAO
import model.Produkt

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject() (produktDao: ProduktDAO, controllerComponents: ControllerComponents)
                               (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  def index() = Action.async {
    produktDao.all().map { case (produkte) => Ok(views.html.index(produkte)) }
  }

  def env() = Action { implicit request: Request[AnyContent] =>
    Ok("Nothing to see here")
    //Ok(System.getenv("JDBC_DATABASE_URL"))
  }

  val produktForm = Form(
    mapping(
      "name" -> text(),
      "price" -> number())(Produkt.apply)(Produkt.unapply))

  def insertProdukt = Action.async { implicit request =>
    val produkt: Produkt = produktForm.bindFromRequest.get
    produktDao.insert(produkt).map(_ => Redirect(routes.HomeController.index))
  }
}