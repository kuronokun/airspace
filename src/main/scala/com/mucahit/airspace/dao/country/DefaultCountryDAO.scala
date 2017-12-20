package com.mucahit.airspace.dao.country

import com.mucahit.airspace.core.conf.Configuration
import com.mucahit.airspace.core.namespaces.dao.CountryDAO
import com.mucahit.airspace.model.Country

trait DefaultCountryDAO extends CountryDAO {

  override val tableName: String = Configuration.COUNTRY_DAO_TABLE_NAME

  override def parseRow(row: Map[String, String]): Country =
    Country(
      row("id").toLong,
      row("code"),
      row("name"),
      row("continent"),
      row("wikipedia_link"),
      row("keywords").split(",").toSet
    )

}
