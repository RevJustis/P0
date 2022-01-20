import scala.io.StdIn.readChar

object Utilities {
  def slpClr(seconds: Int): Unit ={
    Thread.sleep(seconds * 1000)
    print("\r")
  }

  def line(): Unit = {
    print("\n~~~~~~~~~~\n\n")
  }

  def choose123(): Byte ={
    var input: Char = readChar()
    var inByte: Byte = 0
    var goodIn: Boolean = false

    while (!goodIn){
      input match {
        case '1'  => goodIn = true; inByte = 1.toByte
        case '2'  => goodIn = true; inByte = 2.toByte
        case '3'  => goodIn = true; inByte = 3.toByte
        case _  => print("Sorry, but you have to choose '1', '2', or '3': "); input = readChar()
      }
    }
    inByte
  }
}
