import java.sql.{Connection, DriverManager}

object CRUD { // All functions currently placeholders
  def Create(con: Connection, num: Int): Unit = {
    // create the statement, and run the select query
    val statement = con.createStatement()
    val resultSet = statement.executeQuery("SELECT host, user FROM user")
    while ( resultSet.next() ) {
      val host = resultSet.getString("host")
      val user = resultSet.getString("user")
      println("host, user = " + host + ", " + user)
    }
  }
  def Read(track: Byte, lane: Byte): Int ={
    1// Placeholder
  }
  def Update(track: Int, kind: Int): Unit ={

  }
  def Delete(): Unit ={

  }
}