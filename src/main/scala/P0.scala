import scala.io.StdIn.readLine

class P0 {
  val trans: Array[String] = new Array[String]("reverseAll", "invertAll", "oddifyAll", "evenifyAll")

  def main(args: Array[String]): Unit ={
    //try {
      val x: Char = readLine("So, think the lanes will favor you today?\nOr will you shuffle them? Press y or n: ")
//    } catch {
//    }
    if (x == 'y') {
      print("\n~~~~~~~~~~\n")
      ShuffleTerm()
    }
  }

  def ShuffleTerm(): Unit ={
    println("Ah, the \"hands on\" type... Here are your options:")
    //trans.foreach
    val in = readLine("Ah, the \"hands on\" type... Here are your options:")
  }
}
