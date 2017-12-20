package com.mucahit.airspace.dao.country

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.concurrent.duration._

class DefaultCountryDAOTest extends  TestKit(ActorSystem("Test")) with Matchers with WordSpecLike with BeforeAndAfterAll {

  override def afterAll(): Unit = TestKit.shutdownActorSystem(system)

  val defaultCountryDAO = new DefaultCountryDAO {
    override val tableName: String = "src/test/resources/countries.csv"
  }

  "DefaultCountryDAO" should{
    import defaultCountryDAO._

    "read csv files with dao" in{
      Await.result(dao,5 seconds).size shouldBe 2
      Await.result(dao,5 seconds).exists(_.code == "NL") shouldBe true
      Await.result(dao,5 seconds).exists(_.name == "Turkey") shouldBe true
      Await.result(dao,5 seconds).exists(_.keywords.contains("Holland")) shouldBe true
      Await.result(dao,5 seconds).exists(_.keywords == "Türkiye") shouldBe false
    }


  }





}
