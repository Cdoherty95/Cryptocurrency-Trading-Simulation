package model;

import org.jdesktop.swingx.plaf.PainterUIResource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoWallet {

    DBConnect connection = null;
    Statement statement = null;
    String sql = null;
   // ResultSet rs = null;
    DaoUsers daoUsers = new DaoUsers();
    int activeUserID;

    {
        try {
            activeUserID = Integer.parseInt(daoUsers.activeUserInfo()[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DaoWallet(){connection = new DBConnect();}


    public void logTransaction(String type, String fcc, Double fca, String scc, Double sca) throws SQLException {
        //sql prepared stmt
        sql = "INSERT INTO TransactionHistory (UserID, Type, FirstCurrencyCode, FirstCurrencyAmount, SecondCurrencyCode, SecondCurrencyAmount, DateAdded) VALUES (?,?,?,?,?,?,?)";
        //getting timestamp to be stored
        long currentUnixTime = System.currentTimeMillis() / 1000L;

        try (
                PreparedStatement pstmt = connection.connect().prepareStatement(sql)) {
            pstmt.setInt(1, activeUserID);
            pstmt.setString(2, type);
            pstmt.setString(3, fcc);
            pstmt.setDouble(4, fca);
            pstmt.setString(5, scc);
            pstmt.setDouble(6, sca);
            pstmt.setLong(7, currentUnixTime);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection.connect().close();
    }

    public ResultSet getTransactionHistoryActiveUser() throws SQLException {

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT ID, UserID, Type, FirstCurrencyCode, FirstCurrencyAmount, SecondCurrencyCode, SecondCurrencyAmount, DateAdded " +
                "FROM TransactionHistory WHERE UserID='"+activeUserID+"'";

        ResultSet results = statement.executeQuery(sql);

        //results.close();

        return results;
    }

    public ResultSet getTransactionHistoryAll() throws SQLException {

        //Creates a connection to the database
        statement = connection.connect().createStatement();

        //sql to select from database
        sql = "SELECT ID, UserID, Type, FirstCurrencyCode, FirstCurrencyAmount, SecondCurrencyCode, SecondCurrencyAmount, DateAdded " +
                "FROM TransactionHistory";

        ResultSet results = statement.executeQuery(sql);

        //results.close();
        return results;
    }

    public void setUsdAmount(Double usdAmount) throws SQLException {

        statement = connection.connect().createStatement();
        sql = "UPDATE Wallet SET USDAmt="+usdAmount+" WHERE UserID='"+activeUserID+"'";
        statement.execute(sql);
        statement.close();
    }
    
    public void setBtcAmount(Double btcAmount) throws SQLException {

        statement = connection.connect().createStatement();
        sql = "UPDATE Wallet SET BTCAmt="+btcAmount+" WHERE UserID='"+activeUserID+"'";
        statement.execute(sql);
        statement.close();

    }
    
    public void setEthAmount(Double ethAmount) throws SQLException {
        statement = connection.connect().createStatement();
        sql = "UPDATE Wallet SET ETHAmt="+ethAmount+" WHERE UserID='"+activeUserID+"'";
        statement.execute(sql);
        statement.close();
    }
    
    public Double[] getWalletAmounts() throws SQLException {
        Double[] wallet = new Double[3];
        
        statement = connection.connect().createStatement();
        sql = "Select USDAmt, BTCAmt, ETHAmt FROM Wallet WHERE UserID='"+activeUserID+"'";
        
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            wallet[0]=rs.getDouble("USDAmt");
            wallet[1]=rs.getDouble("BTCAmt");
            wallet[2]=rs.getDouble("ETHAmt");
        }
        rs.close();
        return wallet;
    }

    public Double[] getCurrentTradePrices() throws SQLException {
        Double[] wallet = new Double[4];
        statement = connection.connect().createStatement();
        sql = "Select USDAmt, BTCAmt, ETHAmt FROM Wallet WHERE UserID='"+activeUserID+"'";

        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            wallet[0]=rs.getDouble("USDAmt");
            wallet[1]=rs.getDouble("BTCAmt");
        }
        rs.close();

        statement = connection.connect().createStatement();
        sql = "Select USDAmt, BTCAmt, ETHAmt FROM Wallet WHERE UserID='"+activeUserID+"'";

        ResultSet rs1 = statement.executeQuery(sql);
        while(rs1.next()){
            wallet[2]=rs.getDouble("USDAmt");
            wallet[3]=rs.getDouble("ETHAmt");
        }
        rs1.close();

        return wallet;

    }
}
