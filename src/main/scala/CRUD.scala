import java.sql.{Connection, DriverManager, SQLException, SQLSyntaxErrorException}

object CRUD { // All functions currently placeholders
  val cStr = "CREATE TABLE track1 (id int PRIMARY KEY AUTO_INCREMENT,lane1 int,lane2 int,lane3 int);"
  val dropStr = "DROP TABLE "

  def create(con: Connection, num: Int): Unit = {
    // Create a statement to execute create command
    val statement = con.createStatement()
    // But first delete the table to ensure it doesn't already exist
    delete(con)
    try {
      statement.executeUpdate(cStr)
      statement.executeUpdate("CALL Rand1(20, 0, 9)")
    } catch {
      case e: SQLSyntaxErrorException => println("TABLE ALREADY EXISTS")
      case _ => println("ERROR OCCURED AT TABLE CREATION")
    }
    println("Table created...")
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
  def delete(con: Connection): Unit ={
    val statement = con.createStatement()
    // Hardcode delete for track1
    try {
      statement.executeUpdate(dropStr + "track1")
    } catch {
      case e: SQLException => println("TABLE ALREADY DROPPED")
    }
  }
}
