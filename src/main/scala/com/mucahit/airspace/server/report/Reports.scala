package com.mucahit.airspace.server.report

import com.mucahit.airspace.modules.airport.DefaultAirportModule
import com.mucahit.airspace.modules.runway.DefaultRunwayModule
import com.mucahit.airspace.server.protocol.JsonProtocol

import scala.concurrent.Future
import com.mucahit.airspace.core.implicits.CoreImplicits._

trait Reports extends JsonProtocol {

  val getTop10Countries = DefaultAirportModule.getTop10CountriesWithMostAirpots

  val getBottom10Countries = DefaultAirportModule.getBottom10CountriesWithMostAirpots

  val getTypeOfRunwaysPerCountry =
    DefaultAirportModule.airports.flatMap { airportz =>
      Future.sequence(airportz.map { case (countryId, airportList) =>
        Future.sequence(airportList.map { a =>
          DefaultRunwayModule.getRunways(a.id).map(_.map(x => countryId -> x))
        }).map(_.flatten)
      }).map(_.flatten)
    }.map { countryCodeRunwayTuples =>
      countryCodeRunwayTuples
        .groupBy(_._1)
        .map(v => (v._1, v._2.map(_._2)))
        .mapValues(x => x.map(_.surface)
          .toSet
          .size
        )
    }

}
