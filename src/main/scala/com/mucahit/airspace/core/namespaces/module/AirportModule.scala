package com.mucahit.airspace.core.namespaces.module

import com.mucahit.airspace.core.namespaces.dao.AirportDAO
import com.mucahit.airspace.core.namespaces.service.AirportService
import com.mucahit.airspace.model.Airport

trait AirportModule extends Module[Airport] with AirportDAO with AirportService
