package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

/**
 * @author B.Nandini This class is used to work with database
 */
public class DatabaseUtility {
	Connection con;

	/**
	 * This is method is used to connect with DB by passing the url,username and
	 * password
	 * 
	 * @param url
	 * @param un
	 * @param pswd
	 * @throws SQLException
	 */
	public void getconnectWithDB(String url, String un, String pswd) throws SQLException {
		Driver driverobj = new Driver();
		DriverManager.registerDriver(driverobj);
		con = DriverManager.getConnection(url, un, pswd);
	}

	/**
	 * This method is used to connect to database with credentials
	 * 
	 * @throws SQLException
	 */
	public void getconnectWithDB() throws SQLException {
		Driver driverobj = new Driver();
		DriverManager.registerDriver(driverobj);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advproject", "root", "root");
	}

	/**
	 * This method is used to fetch data from database
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet fetchDataFromDatabase(String query) throws SQLException {
		Statement stat = con.createStatement();
		ResultSet data = stat.executeQuery(query);
		return data;
	}

	/**
	 * THis method is used to disconnect with the database
	 * 
	 * @throws SQLException
	 */
	public void disconnectWithDB() throws SQLException {
		con.close();
	}

	/**
	 * This method used is used to update the data to Database
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateDataToDB(String query) throws SQLException {
		Statement stat = con.createStatement();
		int res = stat.executeUpdate(query);
		return res;
	}

}
