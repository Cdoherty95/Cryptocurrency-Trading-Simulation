package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUsers {

	//Declare DB objects 
	final String DBURL = "jdbc:sqlite:CryptoTrader.db";
	DBConnect connection;
	Statement statement;

	String TableName; 
	
	public DaoUsers() {
		connection = new DBConnect();
	}

	public void selectSQL() throws SQLException {
		String sql = null;

		//Creates a connection to the database
		statement = connection.connect().createStatement();


		sql = "CREATE TABLE IF NOT EXISTS Users"
				+ " (UserID integer PRIMARY KEY AUTOINCREMENT, " + 
				" Username text NOT NULL, " +
				" Password text NOT NULL, " + 
				" Role text, " + 
				" LastLoggedIn integer"+
				" );";

		statement.executeUpdate(sql);
		System.out.println("Selected from DB");
		connection.connect().close(); //close db connection 
	}

	public void createUser(String Username, String Password, String Role) throws SQLException{
		String sql;

		//getting timestamp to be stored
		long currentUnixTime = System.currentTimeMillis() / 1000L;
		//generating salt storing in byte array
		byte[] salt = application.Password.getNextSalt();
		//Getting the byte array back after hashing password
		byte[] pwHash = application.Password.hash(Password.toCharArray(), salt);


		//sql prepared stmt 
		sql = "INSERT INTO Users(Username,Password,Role,LastLoggedIn,Salt,Active) VALUES(?,?,?,?,?,?)";

		try (
				PreparedStatement pstmt = connection.connect().prepareStatement(sql)) {
			pstmt.setString(1, Username);
			pstmt.setBytes(2, pwHash);
			pstmt.setString(3, Role);
			pstmt.setLong(4, currentUnixTime);
			pstmt.setBytes(5, salt);
			pstmt.setInt(6, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		connection.connect().close(); //close db connection 

	}

	public boolean loginPasswordMatching(String Username, String Password) throws SQLException{
		String sql;
		byte[] hashPw = null;
		byte[] saltFromSql =null;

		//Creates a connection to the database
		statement = connection.connect().createStatement();

		//sql to select from database
		sql = "SELECT Password, Salt FROM Users WHERE Username='"+Username+"'";

		ResultSet rs = statement.executeQuery(sql);
		
		if(rs.next()) {
		
				hashPw = rs.getBytes("Password");
				saltFromSql = rs.getBytes("Salt");
		

			//getting if the password matches
			boolean y = application.Password.isExpectedPassword(Password.toCharArray(), saltFromSql, hashPw);

			if(y){
				System.out.println("Passwords Match");
				//Sql to update active user
				sql = "UPDATE Users SET Active=1 WHERE Username='"+Username+"'";
				statement.execute(sql);
				return true;
			}else
				return false;
		}
		connection.connect().close(); //close db connection 
		return false;
	}

	public String[] activeUserInfo() throws SQLException{
		String sql;
		String[] userInfo;
		String userName = null;
		String role = null;
		int userID = 0;
		long lastLoggedInUnixTime = 0;

	
		//Creates a connection to the database
		statement = connection.connect().createStatement();

		//sql to select from database
		sql = "SELECT UserID, Username, Role, LastLoggedIn FROM Users WHERE Active=1";

		ResultSet rs = statement.executeQuery(sql);

		// loop through the result set
		while (rs.next()) {
			userID = rs.getInt("UserID");
			role = rs.getString("Role");  
			lastLoggedInUnixTime = rs.getLong("LastLoggedIn"); 
			userName = rs.getString("Username");
		}

		connection.connect().close(); //close db connection 
		
		//setting return
		userInfo = new String[] {String.valueOf(userID), userName, role, String.valueOf(lastLoggedInUnixTime)};
		//Return user information
		return userInfo;

	}

	/**
	 * @param args the command line arguments
	 * @throws SQLException 
	 *
	public static void main(String[] args) throws SQLException {
		DaoUsers dao = new DaoUsers();
		String Username = "chris";
		String Password = "chris";
		String Role = "admin";
		dao.createUser(Username, Password, Role);
		/*
		if(dao.loginPasswordMatching(Username, Password)) {
			System.out.print("Good");
		}else System.out.print("no match");
		String[] userInfo = dao.activeUserInfo();
		for (String element : userInfo) {
			System.out.println("Element: " + element);
		}
	}*/
}