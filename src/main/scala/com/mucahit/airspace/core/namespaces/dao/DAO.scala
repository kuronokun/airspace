package com.mucahit.airspace.core.namespaces.dao

import java.nio.file.Paths

import akka.stream.alpakka.csv.scaladsl.{CsvParsing, CsvToMap}
import akka.stream.scaladsl.{FileIO, Flow, Sink}
import akka.util.ByteString
import com.mucahit.airspace.core.implicits.CoreImplicits._

import scala.concurrent.Future

trait DAO[Entity] {

  val tableName: String

  def parseRow(row: Map[String, String]): Entity

  lazy val dao: Future[Seq[Entity]] =
    FileIO.fromPath(Paths.get(tableName))
      .via(CsvParsing.lineScanner())
      .via(CsvToMap.toMap())
      .via(Flow[Map[String, ByteString]].map(_.map(r => (r._1, r._2.utf8String))))
      .via(Flow[Map[String, String]].map(parseRow))
      .runWith(Sink.seq)

}
