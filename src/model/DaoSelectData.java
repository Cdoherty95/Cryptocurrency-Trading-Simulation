package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoSelectData {
	
	DBConnect connection = null;
	Statement statement = null;
	String sql = null;
	ResultSet rs = null;
	
	public DaoSelectData() {
		connection = new DBConnect();
	}

	public ResultSet viewAllUsers() throws SQLException {
		//connect to db
		statement = connection.connect().createStatement();
		//sql to select users
		sql = "SELECT * FROM USERS";	
		rs = statement.executeQuery(sql);
		System.out.println("Selected from DB");
		statement.close();
		connection.connect().close(); //close db connection 
		return rs;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
