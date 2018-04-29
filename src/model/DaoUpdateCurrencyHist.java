package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUpdateCurrencyHist {

    DBConnect connection;
    Statement statement;
    String sql;

    public DaoUpdateCurrencyHist() {
        connection = new DBConnect();
    }

    public void updateCurrency(String type, Double cryptoAmt, Double usdAmt) throws SQLException {

        //getting timestamp to be stored
        long currentUnixTime = System.currentTimeMillis() / 1000L;
        //Creates a connection to the database
        statement = connection.connect().createStatement();

        if (type.equals("BTC")) {
            //sql to select from database
            sql = "INSERT INTO BTCHistory(TimeStamp,CryptoAmt,USDAmt) VALUES (" + currentUnixTime + "," + cryptoAmt + "," + usdAmt + ");";
        }
        if (type.equals("ETH")) {
            //sql to select from database
            sql = "INSERT INTO ETHHistory(TimeStamp,CryptoAmt,USDAmt) VALUES (" + currentUnixTime + "," + cryptoAmt + "," + usdAmt + ");";
        }

        statement.execute(sql);
        statement.close();
    }

    public ResultSet getEthHist() throws SQLException {

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT * FROM ETHHistory ORDER BY TimeStamp DESC LIMIT 20";

        ResultSet results = statement.executeQuery(sql);

        //results.close();

        return results;

    }

    public ResultSet getBtcHist() throws SQLException {


        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT * FROM BTCHistory ORDER BY TimeStamp DESC LIMIT 20";

        ResultSet results = statement.executeQuery(sql);

        //results.close();

        return results;

    }

    public ResultSet get1BtcHist() throws SQLException {

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT * FROM BTCHistory ORDER BY TimeStamp DESC LIMIT 1";

        ResultSet results = statement.executeQuery(sql);

        //results.close();

        return results;

    }

    public ResultSet get1EthHist() throws SQLException {

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT * FROM ETHHistory ORDER BY TimeStamp DESC LIMIT 1";

        ResultSet results = statement.executeQuery(sql);

        //results.close();

        return results;

    }
}
