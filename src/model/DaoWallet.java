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

    public DaoWallet() throws SQLException {
        connection = new DBConnect();
        activeUserID = Integer.parseInt(daoUsers.activeUserInfo()[0]);
    }


    public void logTransaction(String type, String cc, Double ca, Double usa) throws SQLException {
        //sql prepared stmt
        sql = "INSERT INTO TransactionHistory (UserID, Type, CryptoCode, CryptoAmt, USDAmt, DateAdded) VALUES (?,?,?,?,?,?)";
        //getting timestamp to be stored
        long currentUnixTime = System.currentTimeMillis() / 1000L;

        try (
                PreparedStatement pstmt = connection.connect().prepareStatement(sql)) {
            pstmt.setInt(1, activeUserID);
            pstmt.setString(2, type);
            pstmt.setString(3, cc);
            pstmt.setDouble(4, ca);
            pstmt.setDouble(5, usa);
            pstmt.setLong(6, currentUnixTime);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection.connect().close();
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
}
