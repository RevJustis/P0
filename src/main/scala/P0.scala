import java.sql.{Connection, DriverManager}
import scala.io.StdIn.{readChar, readLine}

object P0 {
  val trans: Array[String] = Array("reverseAll", "invertAll", "oddifyAll", "evenifyAll")

  def main(args: Array[String]): Unit ={
    val connection = jdbcConnection()
    var goodIn: Boolean = false
    var lane: Byte = 0
    var shuff: Byte = 0

    println("So, think the lanes will favor you today?\nOr will you shuffle them? Press y or n: ")
    val x: Char = readChar()

    while (!goodIn){
      if (x == 'y' || x == 'n') {
        goodIn = true
      } else {
        println("Sorry, but you have to say 'yes' or 'no'")
        val x = readChar()
      }
    }

    print("\n~~~~~~~~~~\n")

    if (x == 'y') {
      shuff = ShuffleTerm()
    } else {
      lane = ChooseLane()
    }
  }

  def ChooseLane(): Byte = {
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

  def ShuffleTerm(): Byte = {
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
    inByte
  }

  def jdbcConnection(): Connection = {
    val url = "jdbc:mysql://localhost:3306/sys"
    val username = "root"
    val password = "rootpass"
    val driver = "com.mysql.jdbc.Driver"
    val con = DriverManager.getConnection(url, username, password)

    try {
      // make the connection
      //Class.forName(driver)
      val connection = DriverManager.getConnection(url, username, password)
      println("Successfully connected to ", connection)
      connection
      // create the statement, and run the select query
//      val statement = connection.createStatement()
//      val resultSet = statement.executeQuery("SELECT host, user FROM user")
//      while ( resultSet.next() ) {
//        val host = resultSet.getString("host")
//        val user = resultSet.getString("user")
//        println("host, user = " + host + ", " + user)
//      }
    }
  }
}
