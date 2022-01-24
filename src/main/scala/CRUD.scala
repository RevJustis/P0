import java.sql.{Connection, DriverManager, SQLException, SQLSyntaxErrorException}

object CRUD { // All functions currently placeholders
  val cStr = " (id int PRIMARY KEY AUTO_INCREMENT,lane1 int,lane2 int,lane3 int);"
  val dropStr = "DROP TABLE track"
  val delOddDebuff1 = "DELETE FROM track1 WHERE lane1 = 9 OR lane2 = 9 OR lane3 = 9;"
  val delOddDebuff2 = "DELETE FROM track2 WHERE lane1 = 9 OR lane2 = 9 OR lane3 = 9;"
  val delOddDebuff3 = "DELETE FROM track3 WHERE lane1 = 9 OR lane2 = 9 OR lane3 = 9;"
  val delEvenDebuff1 = "DELETE FROM track1 WHERE lane1 = 8 OR lane2 = 8 OR lane3 = 8;"
  val delEvenDebuff2 = "DELETE FROM track2 WHERE lane1 = 8 OR lane2 = 8 OR lane3 = 8;"
  val delEvenDebuff3 = "DELETE FROM track3 WHERE lane1 = 8 OR lane2 = 8 OR lane3 = 8;"

  def create(con: Connection, num: Byte): Unit = {
    // Create a statement to execute create command
    val statement = con.createStatement()
    var i: Byte = 1
    while (i < num + 1) {
      // But first drop the table to ensure it doesn't already exist
      drop(con, i)
      // Now create table
      statement.executeUpdate("CREATE TABLE track" + i.toChar + cStr)
      // Call a stored procedure that will insert random integers
      statement.executeUpdate("CALL Rand" + i.toChar + "(20, 0, 9)")
      i = (i + 1).toByte
    }
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
    1// Placeholder
  }
  def update(track: Int, kind: Int): Unit ={

  }
  def delete(con: Connection, kind: Byte): Unit = {
    val statement = con.createStatement()
    try {
      kind match {
        case 1 =>
          statement.executeUpdate(delOddDebuff1)
      }
    } catch {
      case e: SQLException => println("TABLE ALREADY DROPPED")
    }
  }

  def drop(con: Connection, track: Byte): Unit ={
    val statement = con.createStatement()
    // Hardcode delete for track1
    try {
      statement.executeUpdate(dropStr + "track1")
    } catch {
      case e: SQLException => println("TABLE ALREADY DROPPED")
    }
  }
}
