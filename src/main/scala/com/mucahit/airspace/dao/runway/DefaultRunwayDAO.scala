package com.mucahit.airspace.dao.runway

import com.mucahit.airspace.core.conf.Configuration
import com.mucahit.airspace.core.implicits.CoreImplicits._
import com.mucahit.airspace.core.namespaces.dao.RunwayDAO
import com.mucahit.airspace.model.Runway

import scala.util.Try

trait DefaultRunwayDAO extends RunwayDAO {

  override val tableName: String = Configuration.RUNWAY_DAO_TABLE_NAME

  override def parseRow(row: Map[String, String]): Runway =
    Runway(
      row("id").toLong,
      row("airport_ref").toLong,
      row("airport_ident"),
      Try(row.get("length_ft").map(_.toInt)).getOrElse(None),
      Try(row.get("width_ft").map(_.toInt)).getOrElse(None),
      row("surface"),
      row("lighted").toInt,
      row("closed").toInt,
      row.get("le_ident"),
      Try(row.get("le_latitude_deg").map(_.toDouble)).getOrElse(None),
      Try(row.get("le_longitude_deg").map(_.toDouble)).getOrElse(None),
      Try(row.get("le_elevation_ft").map(_.toInt)).getOrElse(None),
      Try(row.get("le_heading_degT").map(_.toInt)).getOrElse(None),
      Try(row.get("le_displaced_threshold_ft").map(_.toInt)).getOrElse(None),
      row.get("he_ident"),
      Try(row.get("he_latitude_deg").map(_.toDouble)).getOrElse(None),
      Try(row.get("he_longitude_deg").map(_.toDouble)).getOrElse(None),
      Try(row.get("he_elevation_ft").map(_.toInt)).getOrElse(None),
      Try(row.get("he_heading_degT").map(_.toInt)).getOrElse(None),
      Try(row.get("he_displaced_threshold_ft").map(_.toInt)).getOrElse(None)
    )
}
