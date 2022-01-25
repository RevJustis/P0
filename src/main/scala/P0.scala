import java.sql.{Connection, DriverManager}
import scala.io.StdIn.{readChar, readLine}
import Utilities._
import CRUD._

object P0 {
  private val trans: Array[String] = Array("reverseAll", "noOddBuff", "noEvenBuff")
  private val con = dbConn() // The connection to the database

  def main(args: Array[String]): Unit ={
    // Start off by creating any necessary large scope variables
    var result: Int = 0
    var played: Boolean = false

    println("Hello! Ready to go for a run?")
    // Ask the user how many rounds they would like to play
    println("How many rounds would you like to play?\n~1~\n~2~\n~3~")
    val rounds = choose123()
    line()

    rounds match {
      case 1 => create(con, 1, false)// If 1 rounds, create 1 table
      case 2 => create(con, 2, false)// If 2 rounds, create 2 tables
      case 3 => create(con, 3, false)// etc...
    }

    while (!played) {// Loops so user can continue to shuffle or transform many times
      // Ask if use would like to start or do something else first
      println("So, think the lanes will favor you today?\nOr will you shuffle them?\n" +
        "Or maybe you want to mess with them?")
      println("~1~Leave the lanes alone\n~2~Regenerate lanes\n~3~Transform the lanes")
      val path: Byte = choose123()
      line()

      path match { // result should be an int, currently functions return type Byte
        case 1 => result = play(con, rounds); played = true// After playing the loop will terminate
        case 2 => shuffle(rounds)// Regenerate lanes
        case 3 => trans(rounds)
      }
    }
    println("Your Run Result is: " + result)
  }

  def play(con: Connection, track: Byte): Int = {
    var i: Byte = 1// Iterator counter
    var result: Int = 0
    while (i < track + 1) {
      println("_ROUND " + i + " START_")
      println("There are three lanes in front of you, each with 200 collectibles of differing " +
        "value and buffs and debuffs.")
      println("Please choose a lane, and we will see how you fare...\n~1~\n~2~\n~3~")
      var lane: Byte = choose123()// Gets users lane choice as a Byte
      line()
      // Runs the read function, which returns the score of the full run on user's chosen track and lane
      result += read(con, i, lane)
      i += 1// Increment so loop will eventually terminate
    }
    result
  }

  def shuffle(rounds: Byte): Unit = {
    if (rounds > 1) {
      println("Which track do you want to shuffle?\n~1~\n~2~\n~3~")
      val choice = choose123()
      choice match {
        case 1 => create(con, 1, true)
        case 2 => create(con, 2, true)
        case 3 => create(con, 3, true)
      }
    } else {
      create(con, 1, true)
    }
  }

  def trans(rounds: Byte): Unit = {
    var i = 1
    var track: Byte = 1

    print("Ah, the \"hands on\" type... ")
    rounds match {
      case 2 => println("Which track do you want to transform?\n~1~\n~2~"); track = choose123()
      case 3 => println("Which track do you want to transform?\n~1~\n~2~\n~3~"); track = choose123()
      case _ =>
    }

    println("Here are your options:")
    for (x <- trans) {
      println(s"~$i~$x ")
      i += 1
    }
    val kind = choose123()

    kind match {
      case 1 => update(track, kind)// reverseTrack
      case 2 => delete(con, track, 1)// delOddDebuff
      case 3 => delete(con, track, 2)// delEvenDebuff
    }
    line()
  }

  def dbConn(): Connection = {
    val url = "jdbc:mysql://localhost:3306/sys"
    val username = "root"
    val password = "rootpass"
    val driver = "com.mysql.jdbc.Driver"
    val connection = DriverManager.getConnection(
      url,
      username,
      password
    )
    println("Successfully connected to ", connection)
    connection
  }
}