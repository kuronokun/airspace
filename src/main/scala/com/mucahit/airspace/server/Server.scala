package com.mucahit.airspace.server

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.{complete, path, _}
import com.mucahit.airspace.core.conf.Configuration
import com.mucahit.airspace.core.implicits.CoreImplicits._
import com.mucahit.airspace.server.protocol.ServerError
import com.mucahit.airspace.server.query.Queries
import com.mucahit.airspace.server.report.Reports
import com.mucahit.airspace.server.statics.html.StaticHTMLPages._
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.json.Json

import scala.util.{Failure, Success}

object Server extends Reports with Queries with LazyLogging {

  def handleServerError(ex: Throwable) = {
    complete(HttpResponse(StatusCodes.InternalServerError,
      entity = HttpEntity(jsonContentType, Json.prettyPrint(Json.toJsObject(ServerError(ex.toString))))))
  }

  val jsonContentType = ContentType(MediaTypes.`application/json`)

  val staticRoute =
    pathEndOrSingleSlash {
      complete(HttpResponse(StatusCodes.OK, entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, mainPage)))
    } ~ path("query") {
      complete(HttpResponse(StatusCodes.OK, entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, queryPage)))
    } ~ path("report") {
      complete(HttpResponse(StatusCodes.OK, entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, reportPage)))
    }

  val apiRoute = pathPrefix("api" / "getByName") {
    path(Remaining) { x =>
      onComplete(getByCountryName(x)) {
        case Success(x)  => complete(HttpResponse(entity = HttpEntity(jsonContentType, Json.prettyPrint(Json.toJsObject(x)))))
        case Failure(ex) => handleServerError(ex)
      }
    }
  } ~ path("api" / "top10") {
    onComplete(getTop10Countries) {
      case Success(x)  => complete(HttpResponse(entity = HttpEntity(jsonContentType, Json.prettyPrint(Json.toJsObject(x)))))
      case Failure(ex) => handleServerError(ex)
    }
  } ~ path("api" / "bottom10") {
    onComplete(getBottom10Countries) {
      case Success(x)  => complete(HttpResponse(entity = HttpEntity(jsonContentType, Json.prettyPrint(Json.toJsObject(x)))))
      case Failure(ex) => handleServerError(ex)
    }
  } ~ pathPrefix("api" / "getByCode") {
    path(Remaining) { cc =>
      onComplete(getByCountryCode(cc)) {
        case Success(x)  => complete(HttpResponse(entity = HttpEntity(jsonContentType, Json.prettyPrint(Json.toJsObject(x)))))
        case Failure(ex) => handleServerError(ex)
      }
    }
  } ~ path("api" / "getRunwaysPerCountry") {
    onComplete(getTypeOfRunwaysPerCountry) {
      case Success(x)  => complete(HttpResponse(entity = HttpEntity(jsonContentType, Json.prettyPrint(Json.toJsObject(x)))))
      case Failure(ex) => handleServerError(ex)
    }
  }

  val route = staticRoute ~ apiRoute

  lazy val startServer: Unit =
    Http()
      .bindAndHandle(route, Configuration.SERVER_HOST_NAME, Configuration.SERVER_PORT)
      .onComplete {
        case Success(x)  => logger.info(s"Server is listening from: ${x.localAddress}")
        case Failure(ex) => logger.error(s"Binding failed with exception: $ex")
      }
}




