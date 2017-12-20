package com.mucahit.airspace.services.airport

import com.mucahit.airspace.core.implicits.CoreImplicits._
import com.mucahit.airspace.core.namespaces.dao.AirportDAO
import com.mucahit.airspace.core.namespaces.service.AirportService
import com.mucahit.airspace.model.Airport

import scala.collection.immutable.ListMap
import scala.concurrent.Future

trait DefaultAirportService extends AirportService {
  self: AirportDAO =>

  type CountryCode = String

  /**
    * Sorted by number of airports
    */
  val airports: Future[Map[CountryCode, List[Airport]]] =
    dao.map { airportz =>
      ListMap() ++
        airportz
          .foldLeft(ListMap.empty[CountryCode, List[Airport]]) { case (acc, curr) => acc.updated(curr.iso_country, curr :: acc.getOrElse(curr.iso_country, Nil)) }
          .toList
          .sortBy(x => -x._2.size)
    }

  def getAirportByCountryCode(countryCode: CountryCode): Future[List[Airport]] = airports.map(r => r.getOrElse(countryCode, Nil))

  def getTop10CountriesWithMostAirpots: Future[Map[CountryCode, Int]] =
    airports.map { airportz =>
      airportz.take(10).mapValues(_.size)
    }

  def getBottom10CountriesWithMostAirpots: Future[Map[CountryCode, Int]] =
    airports.map { airportz =>
      airportz.takeRight(10).mapValues(_.size)
    }

}
