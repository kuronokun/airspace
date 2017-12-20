package com.mucahit.airspace.modules.runway

import com.mucahit.airspace.core.namespaces.module.RunwayModule
import com.mucahit.airspace.dao.runway.DefaultRunwayDAO
import com.mucahit.airspace.services.runway.DefaultRunwayService

object DefaultRunwayModule extends RunwayModule with DefaultRunwayDAO with DefaultRunwayService
