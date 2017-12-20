package com.mucahit.airspace.modules.airport

import com.mucahit.airspace.core.namespaces.module.AirportModule
import com.mucahit.airspace.dao.airport.DefaultAirportDAO
import com.mucahit.airspace.services.airport.DefaultAirportService

object DefaultAirportModule extends AirportModule with DefaultAirportDAO with DefaultAirportService
