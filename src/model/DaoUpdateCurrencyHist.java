package model;

import java.sql.SQLException;
import java.sql.Statement;

public class DaoUpdateCurrencyHist {

    DBConnect connection;
    Statement statement;
    String sql;
    //getting timestamp to be stored
    long currentUnixTime = System.currentTimeMillis() / 1000L;

    public DaoUpdateCurrencyHist(){connection = new DBConnect();}

    public void updateCurrency(String type, Double cryptoAmt, Double usdAmt) throws SQLException {
        //Creates a connection to the database
        statement = connection.connect().createStatement();

        if(type.equals("BTC")){
            //sql to select from database
            sql = "INSERT INTO BTCHistory(TimeStamp,CryptoAmt,USDAmt) VALUES ("+currentUnixTime+","+cryptoAmt+","+usdAmt+");";
        }
        if(type.equals("ETH")){
            //sql to select from database
            sql = "INSERT INTO ETHHistory(TimeStamp,CryptoAmt,USDAmt) VALUES ("+currentUnixTime+","+cryptoAmt+","+usdAmt+");";
        }

        statement.execute(sql);
        statement.close();


    }
}
