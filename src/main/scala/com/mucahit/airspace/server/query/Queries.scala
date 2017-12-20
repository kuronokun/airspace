package com.mucahit.airspace.server.query

import com.mucahit.airspace.modules.airport.DefaultAirportModule
import com.mucahit.airspace.modules.country.DefaultCountryModule
import com.mucahit.airspace.modules.runway.DefaultRunwayModule
import com.mucahit.airspace.server.protocol.{AirportAndRunways, AirportAndRunwaysList, JsonProtocol}
import com.mucahit.airspace.core.implicits.CoreImplicits._
import scala.concurrent.Future

trait Queries extends JsonProtocol {

  def getByCountryCode(cc: String): Future[AirportAndRunwaysList] = {
    DefaultAirportModule
      .getAirportByCountryCode(cc)
      .flatMap { airports =>
        Future.sequence(airports.map { airport =>
          DefaultRunwayModule.getRunways(airport.id).map { runways =>
            AirportAndRunways(airport, runways)
          }
        })
      }.map(res => AirportAndRunwaysList(res))
  }

  def getByCountryName(countryName: String): Future[AirportAndRunwaysList] = {
    DefaultCountryModule
      .getCountryByName(countryName)
      .flatMap {
        case Some(country) => getByCountryCode(country.code)
        case None          => Future.successful(AirportAndRunwaysList(Nil))
      }

  }

}
