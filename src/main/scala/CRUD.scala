import java.sql.{Connection, DriverManager, SQLException, SQLSyntaxErrorException, Statement}

object CRUD { // All functions currently placeholders
  val cStr = " (id int PRIMARY KEY AUTO_INCREMENT,lane1 int,lane2 int,lane3 int);"
  val dropStr = "DROP TABLE track"
  val delOddDebuff1 = "DELETE FROM track1 WHERE lane1 = 9 OR lane2 = 9 OR lane3 = 9;"
  val delOddDebuff2 = "DELETE FROM track2 WHERE lane1 = 9 OR lane2 = 9 OR lane3 = 9;"
  val delOddDebuff3 = "DELETE FROM track3 WHERE lane1 = 9 OR lane2 = 9 OR lane3 = 9;"
  val delEvenDebuff1 = "DELETE FROM track1 WHERE lane1 = 8 OR lane2 = 8 OR lane3 = 8;"
  val delEvenDebuff2 = "DELETE FROM track2 WHERE lane1 = 8 OR lane2 = 8 OR lane3 = 8;"
  val delEvenDebuff3 = "DELETE FROM track3 WHERE lane1 = 8 OR lane2 = 8 OR lane3 = 8;"
  var rev1 = false
  var rev2 = false
  var rev3 = false
  var oddType: Byte = 0
  var evenType: Byte = 0

  def create(con: Connection, num: Byte, single: Boolean): Unit = {// Create a statement to execute create command
    if (!single) {
      var i: Byte = 1
      while (i < num + 1) {
        regen(con.createStatement(), i)
        i = (i + 1).toByte
      }
    } else {
      regen(con.createStatement(), num)
    }
  }

  def regen(stmt: Statement, num: Byte): Unit ={
    // But first drop the table to ensure it doesn't already exist
    drop(stmt, num)
    // Now create table
    stmt.executeUpdate("CREATE TABLE track" + num + cStr)
    // Call a stored procedure that will insert random integers
    stmt.executeUpdate("CALL Rand" + num + "(20, 0, 9)")
  }

  def read(con: Connection, track: Byte, lane: Byte): Int ={
    var result = 0
    val statement = con.createStatement()
    var query: String = ""
    track match {
      case 1 =>
        if (rev1) {
          query = s"SELECT * FROM track$track ORDER BY id DESC"
        } else {
          query = s"SELECT * FROM track$track"
        }
      case 2 =>
        if (rev2) {
          query = s"SELECT * FROM track$track ORDER BY id DESC"
        } else {
          query = s"SELECT * FROM track$track"
        }
      case 3 =>
        if (rev3) {
          query = s"SELECT * FROM track$track ORDER BY id DESC"
        } else {
          query = s"SELECT * FROM track$track"
        }
    }
    val resultSet = statement.executeQuery(query)
    while ( resultSet.next ) {
      var lv: Int = 0
      lane match {
        case 1 => lv = resultSet.getInt("lane1")
        case 2 => lv = resultSet.getInt("lane2")
        case 3 => lv = resultSet.getInt("lane3")
      }
      lv match {
        case 1|2|3|4|5 => result += buff(lv)
        case 6 => evenType = 1// 1 means buff
        case 7 => oddType = 1
        case 8 => evenType = 2// 2 means debuff
        case 9 => oddType = 2
        case _ =>// nothing
      }
    }
    result
  }

  def buff(num: Int): Int ={
    num % 2 match {
      case 0 =>
        evenType match {
          case 1 => (num * 1.5).toInt
          case 2 => (num * 0.5).toInt
          case 0 => num
        }
      case 1 =>
        oddType match {
          case 1 => (num * 1.5).toInt
          case 2 => (num * 0.5).toInt
          case 0 => num
        }
    }
  }

  def makeRev(track: Int): Unit ={
    track match {
      case 1 => rev1 = true
      case 2 => rev2 = true
      case 3 => rev3 = true
    }
  }

  def delete(con: Connection, track: Byte, kind: Byte): Unit = {
    val statement = con.createStatement()
    try {
      kind match {
        case 1 =>
          track match {
            case 1 => statement.executeUpdate(delOddDebuff1)
            case 2 => statement.executeUpdate(delOddDebuff2)
            case 3 => statement.executeUpdate(delOddDebuff3)
          }
        case 2 =>
          track match {
            case 1 => statement.executeUpdate(delEvenDebuff1)
            case 2 => statement.executeUpdate(delEvenDebuff2)
            case 3 => statement.executeUpdate(delEvenDebuff3)
          }
      }
    } catch {
      case e: SQLException => println("UPDATE TABLE FAILED: " + e)
    }
  }

  def drop(stmt: Statement, track: Byte): Unit ={
    try {
      stmt.executeUpdate(dropStr + track)
    } catch {
      case e: SQLException => println("TABLE ALREADY DROPPED: " + e)
    }
  }
}
