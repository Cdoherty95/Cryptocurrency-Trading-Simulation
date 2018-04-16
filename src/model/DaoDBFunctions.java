package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoDBFunctions {

	//Declare DB objects 
	final String DBURL = "jdbc:sqlite:CryptoTrader.db";
	DBConnect connection = null;
	Statement statement = null;
	String TableName; 

	// constructor
	public DaoDBFunctions() { //create db object instance
		connection = new DBConnect();
	}

	//create database method
	public void createNewDatabase(String fileName) {

		try (Connection conn = DriverManager.getConnection(DBURL)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// CREATE TABLE METHOD
	public void createTable(String whatTable) {
		String sql = null;

		try {
			// Open a connection
			System.out.println("Connecting to a selected database to create Table...");

			System.out.println("Connected database successfully...");


			//Creates a connection to the database
			statement = connection.connect().createStatement();

			if(whatTable=="Users") {
				//Creates Users SQL Statement
				sql = "CREATE TABLE IF NOT EXISTS Users"
						+ " (UserID integer PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
						" Username text NOT NULL UNIQUE, " +
						" Password BLOB NOT NULL, " + 
						" Salt BLOB, " + 
						" Role text, " + 
						" Active integer, " + 
						" LastLoggedIn integer"+
						" );";
			}else if(whatTable=="UAD") {
				//Creates Users SQL Statement
				sql = "CREATE TABLE IF NOT EXISTS UserAccountDetails" + 
						"(UserID INTEGER NOT NULL PRIMARY KEY, " + 
						"FirstName TEXT, " +
						"LastName TEXT, " + 
						"PaymentNumber INTEGER, " +
						"PaymentName TEXT, " + 
						"Account INTEGER, " +
						"Routing INTEGER, " +
						"Active INTEGER, " +
						"DateAdded INTEGER" +
						" );";				
			}else if(whatTable=="TransHist") {
				//Creates Users SQL Statement
				sql = "CREATE TABLE IF NOT EXISTS TransactionHistory" + 
						"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						"UserID INTEGER, " +
						"Type TEXT, " + 
						"CryptoCode TEXT, " +
						"CryptoAmt DECIMAL(10,10), " + 
						"USDAmt DECIMAL(10,10), " +
						"DateAdded INTEGER" +
						" );";
			}else if(whatTable=="BTCHist") {
				//Creates Users SQL Statement
				sql = "CREATE TABLE IF NOT EXISTS BTCHistory" + 
						"(TimeStamp INTEGER PRIMARY KEY NOT NULL, " + 
						"CryptoAmt DECIMAL(10,10), " + 
						"USDAmt DECIMAL(10,10)"+
						" );";
			}else if(whatTable=="ETHHist") {
				//Creates Users SQL Statement
				sql = "CREATE TABLE IF NOT EXISTS ETHHistory" + 
						"(TimeStamp INTEGER PRIMARY KEY NOT NULL, " + 
						"CryptoAmt DECIMAL(10,10), " + 
						"USDAmt DECIMAL(10,10)"+
						" );";
			}else if(whatTable=="Wallet") {
				//Creates Users SQL Statement
				sql = "CREATE TABLE IF NOT EXISTS Wallet" + 
						"(UserID INTEGER PRIMARY KEY NOT NULL, " + 
						"BTCAmt DECIMAL(10,10), " + 
						"ETHAmt DECIMAL(10,10), " +
						"USDAmt DECIMAL(10,10), " +
						"DateAdded INTEGER"+
						" );";
			}else {
				sql=null;
				System.out.println("No Match to create table");
			}
			statement.executeUpdate(sql);
			System.out.println("Created " + whatTable + " in database.");
			statement.close();
			connection.connect().close(); //close db connection 
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}

	// CREATE TABLE METHOD
	public void DropTable(String TableName) {
		try {
			//Creates a connection to the database
			statement = connection.connect().createStatement();

			//Creates a SQL Statement
			String sql = "DROP TABLE " +TableName+ "";

			statement.executeUpdate(sql);
			System.out.println("Table " +TableName +" deleted in database.");
			statement.close();
			connection.connect().close(); //close db connection 
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}

	public void createAllTables() {
		//Table Names Which To Create
		String UAD="UAD";
		String Users="Users";
		String TransHist="TransHist";
		String BTCHist="BTCHist";
		String ETHHist="ETHHist";
		String Wallet="Wallet";

		//Calls with different table names
		createTable(UAD);
		createTable(Users);
		createTable(TransHist);
		createTable(BTCHist);
		createTable(ETHHist);
		createTable(Wallet);
	}


	public void test() throws SQLException {
		//Creates a connection to the database
		statement = connection.connect().createStatement();

		String sql = "Select * FROM Users";

		ResultSet results = null;

		results = statement.executeQuery(sql);

		//Closes the connection
		statement.close();
		connection.connect().close();

		while(results.next()) {
			System.out.println("username: " + results.getString("Username"));
		}
	}


	/**
	 * @param args the command line arguments
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DaoDBFunctions dao = new DaoDBFunctions();
		//dao.test();


	}
}
