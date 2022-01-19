import java.sql.{Connection, DriverManager}
import scala.io.StdIn.{readChar, readLine}
import Utilities._

object P0 {
  val trans: Array[String] = Array("reverseAll", "invertAll", "oddifyAll", "evenifyAll")

  def main(args: Array[String]): Unit ={
    val connection = JDBCCon()
    var inByte: Byte = 0
    var goodIn: Boolean = false
    var lane: Byte = 0
    var shuff: Byte = 0

    println("So, think the lanes will favor you today?\nOr will you shuffle them?\n" +
      "Or maybe you want to mess with them?\n")
    println("1)Leave the lanes alone\n2)Create new lanes\n3)Transform the lanes")
    var in: Char = readChar()

    while (!goodIn){
      in match {
        case '1'  => goodIn = true; inByte = 1
        case '2'  => goodIn = true; inByte = 2
        case '3'  => goodIn = true; inByte = 3
        case _  => print("\rSorry, but you have to choose '1', '2', or '3': "); in = readChar()
      }
    }

    line()

    val result = inByte match { // result should be an int, currently functions return type Byte
      case 1 => PlayLane()
      case 2 => Shuffle()
      case 3 => ChooseTrans()
    }

    println("Your Run Result is: " + result)
  }

  def PlayLane(): Byte = {
    println("There are three lanes in front of you, each with 200 collectibles of differing value and buffs and debuffs.")
    println("Please choose a lane, and we will see how you fare... 1, 2, or 3?")
    var in = readChar()
    var goodIn = false

    while (!goodIn){
      if (in == '1' || in == '2' || in == '3') {
        goodIn = true
      } else {
        println("Sorry, but you have to choose '1', '2', or '3': ")
        in = readChar()
      }
    }
    in.toByte
  }

  def ChooseTrans(): Byte = {
    var i = 1
    var inByte: Byte = 0
    var goodIn = false

    println("Ah, the \"hands on\" type... Here are your options:")
    for (x <- trans) {
      print(i + ")" + x + " ")
      i += 1
    }
    println()

    var in = readChar()
    while (!goodIn){
      in match {
        case '1'  => goodIn = true; inByte = 1
        case '2'  => goodIn = true; inByte = 2
        case '3'  => goodIn = true; inByte = 3
        case '4'  => goodIn = true; inByte = 4
        case _  => println("Sorry, but you have to choose '1', '2', '3', or '4': "); in = readChar()
      }
    }
    (inByte - 1).toByte
  }

  def Shuffle(): Byte = {
    0.toByte // Placeholder
  }

  def JDBCCon(): Connection = {
    val url = "jdbc:mysql://localhost:3306/sys"
    val username = "root"
    val password = "rootpass"
    val driver = "com.mysql.jdbc.Driver"
    val connection = DriverManager.getConnection(url, username, password)
    println("Successfully connected to ", connection)
    connection
  }
}
