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
    println("In read...")
    val statement = con.createStatement()
    val resultSet = statement.executeQuery(s"SELECT * FROM track$track")
    while ( resultSet.next() ) {
      val l1 = resultSet.getString("lane1")
      val l2 = resultSet.getString("lane2")
      val l3 = resultSet.getString("lane3")
      println("lane 1 = " + l1 + ", lane 2 = " + l2 + ", lane 3 = " + l3)
    }
    1// TODO read() implementation doesn't return sum of points
  }

  def update(track: Int, lane: Int): Unit ={
    // TODO Put the U in CRUD
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
