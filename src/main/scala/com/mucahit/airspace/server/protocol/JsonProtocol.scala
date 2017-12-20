package com.mucahit.airspace.server.protocol

import com.mucahit.airspace.model.{Airport, Country, Runway}
import play.api.libs.json.Json

trait JsonProtocol {
  implicit val imp = Json.format[Runway]
  implicit val imp1 = Json.format[Airport]
  implicit val imp2 = Json.format[Country]
  implicit val imp3 = Json.format[AirportAndRunways]
  implicit val imp4 = Json.format[AirportAndRunwaysList]
  implicit val imp5 = Json.format[ServerError]
}
