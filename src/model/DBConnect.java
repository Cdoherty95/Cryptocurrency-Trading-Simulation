package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    final static String URL = "jdbc:sqlite:CryptoTrader.db";

    /**
     * Connect to a sample database
     */
    public Connection connect() throws SQLException {

        return DriverManager.getConnection(URL);
    }


}