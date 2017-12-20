package com.mucahit.airspace.server.protocol

import com.mucahit.airspace.model.{Airport, Runway}

case class AirportAndRunways(airport: Airport, runways: List[Runway]) extends ServerResponse
