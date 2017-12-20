package com.mucahit.airspace.services.airport

import akka.actor.ActorSystem
import akka.testkit.TestKit
import com.mucahit.airspace.dao.airport.DefaultAirportDAO
import com.mucahit.airspace.dao.country.DefaultCountryDAO
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration._
import scala.concurrent.Await


class DefaultAirportServiceTest extends TestKit(ActorSystem("Test")) with Matchers with WordSpecLike with BeforeAndAfterAll {

  override def afterAll(): Unit = TestKit.shutdownActorSystem(system)

  trait TestAirportDao extends DefaultAirportDAO {
    override val tableName: String = "src/test/resources/airports.csv"
  }

  class TestAirportService extends TestAirportDao with DefaultAirportService

  val testAirportService = new TestAirportService

  " DefaultAirportService" should {


    "getAirportsByCountryCode " in {
      Await.result(testAirportService.getAirportByCountryCode("TR"), 5 seconds).size shouldBe 3
      Await.result(testAirportService.getAirportByCountryCode("TC"), 5 seconds).size shouldBe 0

    }


  }


}
