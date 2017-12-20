package com.mucahit.airspace.services.country

import com.mucahit.airspace.core.implicits.CoreImplicits._
import com.mucahit.airspace.core.namespaces.dao.CountryDAO
import com.mucahit.airspace.core.namespaces.service.CountryService
import com.mucahit.airspace.model.Country

import scala.concurrent.Future

trait DefaultCountryService extends CountryService {
  self: CountryDAO =>

  type CountryName = String

  private val countries: Future[Map[CountryName, Country]] =
    dao.map { countriez =>
      countriez.map(c => c.name -> c)(collection.breakOut)
    }

  def getCountryByName(name: String): Future[Option[Country]] = countries.map(r => r.get(name))

  def getCountryByCode(code: String): Future[Option[Country]] = countries.map(countriez => countriez.find { case (_, country) => country.code == code }.map(_._2))
}
