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
    String sql;
    String TableName;

    public DaoUsers() {
        connection = new DBConnect();
    }

    public void createUser(String Username, String Password, String Role) throws SQLException {

        //getting timestamp to be stored
        long currentUnixTime = System.currentTimeMillis() / 1000L;
        //generating salt storing in byte array
        byte[] salt = controller.Password.getNextSalt();
        //Getting the byte array back after hashing password
        byte[] pwHash = controller.Password.hash(Password.toCharArray(), salt);


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
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.connect().close(); //close db connection

    }

    public int getUserID(String uname) throws SQLException {

        int userID = 0;
        statement = connection.connect().createStatement();
        //sql to select from database
        sql = "SELECT UserID FROM Users WHERE Username='" + uname + "'";

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            userID = rs.getInt("UserID");
            System.out.println(userID);
        }
        statement.close();
        connection.connect().close(); //close db connection
        return userID;
    }

    public void registerUser(int uID, String fname, String lName, String payName, int accNum, int routeNum, String em) throws SQLException {

        //getting timestamp to be stored
        long currentUnixTime = System.currentTimeMillis() / 1000L;

        //sql prepared stmt
        sql = "INSERT INTO UserAccountDetails(UserID, FirstName, LastName, PaymentNumber, PaymentName, Account, Routing, Active, DateAdded, Email) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (
                PreparedStatement pstmt = connection.connect().prepareStatement(sql)) {
            pstmt.setInt(1, uID);
            pstmt.setString(2, fname);
            pstmt.setString(3, lName);
            pstmt.setInt(4, 1);
            pstmt.setString(5, payName);
            pstmt.setInt(6, accNum);
            pstmt.setInt(7, routeNum);
            pstmt.setInt(8, 0);
            pstmt.setLong(9, currentUnixTime);
            pstmt.setString(10, em);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        //Create User Wallet
        statement = connection.connect().createStatement();
        sql = "INSERT INTO Wallet (UserID, BTCAmt, ETHAmt, USDAmt, DateAdded) VALUES (" + uID + ",0,0,0," + currentUnixTime + ");";
        //System.out.println(sql);
        statement.execute(sql);
        statement.close();
        //connection.connect().close(); //close db connection
    }

    public boolean loginPasswordMatching(String Username, String Password) throws SQLException, InterruptedException {

        byte[] hashPw = null;
        byte[] saltFromSql = null;

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT Password, Salt FROM Users WHERE Username='" + Username + "'";

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            hashPw = rs.getBytes("Password");
            saltFromSql = rs.getBytes("Salt");


            //getting if the password matches
            boolean y = controller.Password.isExpectedPassword(Password.toCharArray(), saltFromSql, hashPw);

            if (y) {
                System.out.println("Passwords Match");
                rs.close();
                statement.close();
                setActiveUsers(Username);
                return true;
            } else {
                rs.close();
                statement.close();
                return false;
            }
        }
        rs.close();
        statement.close();
        connection.connect().close(); //close db connection
        return false;
    }

    public void setActiveUsers(String uname) throws SQLException, InterruptedException {
        //Creates a connection to the database
        statement = connection.connect().createStatement();
        int uid = getUserID(uname);

        //Sql to update active user
        sql = "UPDATE Users SET Active=1 WHERE UserID='" + uid + "'";
        statement.executeUpdate(sql);
        statement.close();

        statement = connection.connect().createStatement();
        //Sql to update UserAccountDetails active user
        sql = "UPDATE UserAccountDetails SET Active=1 WHERE UserID='" + uid + "'";
        statement.executeUpdate(sql);
        statement.close();
    }

    public String[] activeUserInfo() throws SQLException {

        String[] userInfo;
        String userName = null;
        String role = null;
        int userID = 0;
        long lastLoggedInUnixTime = 0;
        String fname = null;
        String lname = null;
        String paymentName = null;
        int bankAccNumber = 0;
        int bankRoutingNum = 0;
        String email = null;


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
        rs.close();
        statement.close();
        //connection.connect().close(); //close db connection

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT FirstName, LastName, PaymentName, Account, Routing, Email FROM UserAccountDetails WHERE Active=1";

        ResultSet rs2 = statement.executeQuery(sql);
        // loop through the result set
        while (rs2.next()) {
            fname = rs2.getString("FirstName");
            lname = rs2.getString("LastName");
            paymentName = rs2.getString("PaymentName");
            bankAccNumber = rs2.getInt("Account");
            bankRoutingNum = rs2.getInt("Routing");
            email = rs2.getString("Email");
        }
        rs2.close();
        statement.close();

        //setting return
        userInfo = new String[]{String.valueOf(userID), userName, role, String.valueOf(lastLoggedInUnixTime), fname, lname,
                paymentName, String.valueOf(bankAccNumber), String.valueOf(bankRoutingNum), email};
        //Return user information
        return userInfo;

    }

    public void makeAllInactive() throws SQLException {
        //Creates a connection to the database
        statement = connection.connect().createStatement();
        //sql to set active to 0
        sql = "UPDATE Users SET Active=0";
        statement.executeUpdate(sql);
        statement.close();
        statement = connection.connect().createStatement();
        //sql to set active to 0
        sql = "UPDATE UserAccountDetails SET Active=0";
        statement.executeUpdate(sql);
        statement.close();
    }

    public ResultSet viewAllUsers() throws SQLException {

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT UserID, Username, Role, LastLoggedIn FROM Users";

        ResultSet results = statement.executeQuery(sql);

        //results.close();

        return results;
    }

    public void updateUserInfo(int uID, String fnam, String lname, String em, String payName, int acc, int route) throws SQLException {
        //getting timestamp to be stored
        long currentUnixTime = System.currentTimeMillis() / 1000L;
        //Creates a connection to the database
        statement = connection.connect().createStatement();
        //sql to set active to 0
        sql = "UPDATE UserAccountDetails SET FirstName='" + fnam + "', LastName='" + lname + "', PaymentName='" + payName + "', Account='" + acc + "', " +
                "Routing='" + route + "', Email='" + em + "', DateAdded='" + currentUnixTime + "' WHERE UserID=" + uID + "";
        System.out.println(sql);
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statement.close();
    }

    public void deleteUser(int uID) throws SQLException {
        statement = connection.connect().createStatement();
        //sql to set active to 0
        sql = "DELETE FROM Users WHERE UserID=" + uID + "";
        //System.out.println(sql);
        int i = statement.executeUpdate(sql);
        //System.out.println(i);
        statement.close();
    }

	/*
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