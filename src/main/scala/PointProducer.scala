import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.http.scaladsl.server.Directives._
import scala.util.Random
import scala.concurrent.duration._
import scala.io.StdIn
import upickle.default.{ReadWriter => RW, macroRW}
import upickle.default._

object PointProducer extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  case class Point(x: Double, y: Double)
  object Point {
    implicit val rw: RW[Point] = macroRW
  }

  var point = Point(300.0, 400.0)
  def move(): String = {
    point = Point(
      point.x + Random.nextDouble() * 2 - 1,
      point.y + Random.nextDouble() * 2 - 1
    )

    write(point)
  }

  val sink = Sink.cancelled
  val source = Source.tick(20.milli, 20.milli, ()).map(
    _ => TextMessage.Strict(move())
  )
  val flow = Flow.fromSinkAndSource(sink, source)

  val route = path("ws")(handleWebSocketMessages(flow))
  val bindingFuture = Http().bindAndHandle(route, "localhost", 12346)

  println(s"Server online at http://localhost:9898/\nPress RETURN to stop...")
  StdIn.readLine()
  while (true) {

  }

  import system.dispatcher
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
