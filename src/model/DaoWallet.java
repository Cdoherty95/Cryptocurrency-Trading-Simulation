package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoWallet {

    DBConnect connection = null;
    Statement statement = null;
    String sql = null;
    ResultSet rs = null;

    public DaoWallet() {
        connection = new DBConnect();
    }

   
    public void setUsdAmount(Double usdAmount) throws SQLException {
        DaoUsers daoUsers = new DaoUsers();
        String[] userinfo = daoUsers.activeUserInfo();
        int userid = Integer.parseInt(userinfo[0]);
        
        statement = connection.connect().createStatement();
        sql = "UPDATE Wallet SET USDAmt="+usdAmount+" WHERE UserID='"+userid+"'";
        statement.execute(sql);
        statement.close();

    }
    
    public void setBtcAmount(Double btcAmount) throws SQLException {
        DaoUsers daoUsers = new DaoUsers();
        String[] userinfo = daoUsers.activeUserInfo();
        int userid = Integer.parseInt(userinfo[0]);

        statement = connection.connect().createStatement();
        sql = "UPDATE Wallet SET BTCAmt="+btcAmount+" WHERE UserID='"+userid+"'";
        statement.execute(sql);
        statement.close();

    }
    
    public void setEthAmount(Double ethAmount) throws SQLException {
        DaoUsers daoUsers = new DaoUsers();
        String[] userinfo = daoUsers.activeUserInfo();
        int userid = Integer.parseInt(userinfo[0]);

        statement = connection.connect().createStatement();
        sql = "UPDATE Wallet SET ETHAmt="+ethAmount+" WHERE UserID='"+userid+"'";
        statement.execute(sql);
        statement.close();
    }
    
    public Double[] getWalletAmounts() throws SQLException {
        DaoUsers daoUsers = new DaoUsers();
        String[] userinfo = daoUsers.activeUserInfo();
        int userid = Integer.parseInt(userinfo[0]);
        Double[] wallet = new Double[3];
        
        statement = connection.connect().createStatement();
        sql = "Select USDAmt, BTCAmt, ETHAmt FROM Wallet WHERE UserID='"+userid+"'";
        
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
