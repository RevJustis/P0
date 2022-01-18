import scala.io.StdIn.{readChar, readLine}

class P0 {
  val trans: Array[String] = Array("reverseAll", "invertAll", "oddifyAll", "evenifyAll")

  def main(args: Array[String]): Unit ={
    //try {
    println("So, think the lanes will favor you today?\nOr will you shuffle them? Press y or n: ")
      val x: Char = readChar()
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
