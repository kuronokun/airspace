package com.mucahit.airspace.server

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import akka.testkit.TestDuration
import com.mucahit.airspace.server.statics.html.StaticHTMLPages
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration._

class ServerTest extends WordSpec with Matchers with ScalatestRouteTest {
  implicit val timeout = RouteTestTimeout(5.seconds dilated)

  "Server" should {

    "return main page as HTML" in {
      // tests:
      Get() ~> Server.staticRoute ~> check {
        response.status shouldBe StatusCodes.OK
        response.entity.contentType shouldBe ContentTypes.`text/html(UTF-8)`
        responseAs[String] shouldBe StaticHTMLPages.mainPage
      }
    }

    "return getByName request with json content type" in {
      // tests:
      Get("/api/getByName/Turkey") ~> Server.apiRoute ~> check {
        response.status shouldBe StatusCodes.OK
        response.entity.contentType shouldBe ContentTypes.`application/json`
      }
    }
  }
}
