import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement


object MakeTable {
  @throws[SQLException]
  def main(args: Array[String]): Unit = { //Registering the Driver
    //DriverManager.registerDriver(new Nothing)
    //Getting the connection
    val mysqlUrl = "jdbc:mysql://localhost/mydatabase"
    val con = DriverManager.getConnection(mysqlUrl, "root", "password")
    System.out.println("Connection established......")
    //Creating the Statement
    val stmt = con.createStatement
    //Creating the sales table with auto-incremented values
    val createQuery = "CREATE TABLE `table`(ID INT PRIMARY KEY AUTO_INCREMENT, lane1 INT, lane2 INT, lane3 INT)"
    //Executing the query
    stmt.execute(createQuery)
    System.out.println("Table created......")
  }
}