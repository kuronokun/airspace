package com.mucahit.airspace.dao.airport

import com.mucahit.airspace.core.conf.Configuration
import com.mucahit.airspace.core.namespaces.dao.AirportDAO
import com.mucahit.airspace.model.Airport

import scala.util.Try

trait DefaultAirportDAO extends AirportDAO {

  override val tableName: String = Configuration.AIRPORT_DAO_TABLE_NAME

  override def parseRow(row: Map[String, String]): Airport =
    Airport(
      row("id").toLong,
      row("ident"),
      row("type"),
      row("name"),
      row("latitude_deg").toDouble,
      row("longitude_deg").toDouble,
      Try(row.get("elevation_ft").map(_.toInt)).getOrElse(None),
      row("continent"),
      row("iso_country"),
      row("iso_region"),
      row("municipality"),
      row("scheduled_service"),
      row("gps_code"),
      row("local_code"),
      row.get("iata_code"),
      row.get("home_link")
    )

}
