import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseAdapter {
	  public void dbConnect(String db_connect_string,
	            String db_userid,
	            String db_password)
	   {
	      try {
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         Connection conn = DriverManager.getConnection(db_connect_string,
	                  db_userid, db_password);
	         System.out.println("connected");
	         Statement statement = conn.createStatement();
	         String queryString = "SELECT * FROM Movie";
	         ResultSet rs = statement.executeQuery(queryString);
	         while (rs.next()) {
	            System.out.println(rs.getString("rented"));
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

	  public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

          /*Connection conn=null;
          try {
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
              conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master", "sa", "root");

              if(conn!=null)
                  System.out.println("Database Successfully connected");

          } catch (SQLException e) {
              e.printStackTrace();
          }
          
          */
		  DatabaseAdapter database = new DatabaseAdapter();
		  database.dbConnect("jdbc:sqlserver://localhost:1433;databaseName=master", "sa", "root");
      }
}
