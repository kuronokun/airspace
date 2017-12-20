package com.mucahit.airspace.core.namespaces.module

import com.mucahit.airspace.core.namespaces.dao.RunwayDAO
import com.mucahit.airspace.core.namespaces.service.RunwayService
import com.mucahit.airspace.model.Runway

trait RunwayModule extends Module[Runway] with RunwayDAO with RunwayService
