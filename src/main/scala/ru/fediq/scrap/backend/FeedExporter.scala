package ru.fediq.scrap.backend

import java.io.{BufferedOutputStream, FileOutputStream, PrintWriter}

import ru.fediq.scrap.Utilities
import ru.fediq.scrap.scraper.ScrapedEntity

import scala.concurrent.Future

trait FeedExporter extends AutoCloseable {
  def store[T <: ScrapedEntity](entity: T): Future[_]

  override def close() = {
    // Do nothing
  }
}

class NoOpFeedExporter extends FeedExporter {
  override def store[T <: ScrapedEntity](entity: T) = {
    Future.successful()
  }
}

class JsonLinesFeedExporter(
  path: String
) extends FeedExporter {
  val writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))

  implicit val dispatcher = Utilities.singleDaemonThreadDispatcher("feed-exporter")

  override def store[T <: ScrapedEntity](entity: T) = Future {
    writer.println(entity.dump)
  }

  override def close() = {
    writer.close()
  }
}