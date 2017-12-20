package com.mucahit.airspace.services.runway

import com.mucahit.airspace.core.implicits.CoreImplicits._
import com.mucahit.airspace.core.namespaces.dao.RunwayDAO
import com.mucahit.airspace.core.namespaces.service.RunwayService
import com.mucahit.airspace.model.Runway

import scala.concurrent.Future

trait DefaultRunwayService extends RunwayService {
  self: RunwayDAO =>

  type AirportRef = Long

  private val runways: Future[Map[AirportRef, List[Runway]]] =
    dao.map { runwayz =>
      runwayz.foldLeft(Map.empty[AirportRef, List[Runway]]) { case (acc, curr) => acc.updated(curr.airport_ref, curr :: acc.getOrElse(curr.airport_ref, Nil)) }
    }

  def getRunways(airport_ref: AirportRef): Future[List[Runway]] = runways.map(r => r.getOrElse(airport_ref, Nil))

}
