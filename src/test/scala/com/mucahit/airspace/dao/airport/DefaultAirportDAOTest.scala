package com.mucahit.airspace.dao.airport

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.concurrent.duration._

class DefaultAirportDAOTest extends  TestKit(ActorSystem("Test")) with Matchers with WordSpecLike with BeforeAndAfterAll {

  override def afterAll(): Unit = TestKit.shutdownActorSystem(system)

  val defaultAirportDAO = new DefaultAirportDAO {
    override val tableName: String = "src/test/resources/airports.csv"
  }

  "DefaultAirportDAO" should{
    import defaultAirportDAO._

    "read csv files with dao" in{
      Await.result(dao,5 seconds).size shouldBe 6
      Await.result(dao,5 seconds).exists(_.iso_country == "NL") shouldBe true
      Await.result(dao,5 seconds).exists(_.iso_country == "TR") shouldBe true
      Await.result(dao,5 seconds).exists(_.iso_country == "XS") shouldBe false
    }


  }





}
