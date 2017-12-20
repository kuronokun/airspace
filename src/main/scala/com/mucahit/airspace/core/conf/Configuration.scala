package com.mucahit.airspace.core.conf

import com.typesafe.config.ConfigFactory

object Configuration {

  private val config = ConfigFactory.load

  final val AIRPORT_DAO_TABLE_NAME: String = config.getString("com.mucahit.airspace.dao.airport-table-name")
  final val RUNWAY_DAO_TABLE_NAME: String = config.getString("com.mucahit.airspace.dao.runway-table-name")
  final val COUNTRY_DAO_TABLE_NAME: String = config.getString("com.mucahit.airspace.dao.country-table-name")
  final val SERVER_HOST_NAME: String = config.getString("com.mucahit.airspace.server.host-name")
  final val SERVER_PORT: Int = config.getInt("com.mucahit.airspace.server.port")
  final val SERVER_BASE_URL: String = s"http://$SERVER_HOST_NAME:$SERVER_PORT"


}
