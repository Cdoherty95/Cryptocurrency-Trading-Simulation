package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

public class UserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar topBar;

    @FXML
    private Label welcomeUser;

    @FXML
    private Label usrName;

    @FXML
    private Label usrLastName;

    @FXML
    private Label usrEmail;

    @FXML
    private Label usrpaymnetMethod;

    @FXML
    private Label editClick;

    @FXML
    private Label welcomeUser1;

    @FXML
    private Label usdAmt;

    @FXML
    private Label btcAmt;

    @FXML
    private Label ethAmt;

    @FXML
    private Button goToExch;

    @FXML
    private Button CurrencyHistory;

    @FXML
    private Button viewTransHist;

    @FXML
    void goToExchange(ActionEvent event) {

    }

    @FXML
    void viewCurrencyHistory(ActionEvent event) {

    }

    @FXML
    void viewTransactionHistory(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert topBar != null : "fx:id=\"topBar\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert welcomeUser != null : "fx:id=\"welcomeUser\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert usrName != null : "fx:id=\"usrName\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert usrLastName != null : "fx:id=\"usrLastName\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert usrEmail != null : "fx:id=\"usrEmail\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert usrpaymnetMethod != null : "fx:id=\"usrpaymnetMethod\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert editClick != null : "fx:id=\"editClick\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert welcomeUser1 != null : "fx:id=\"welcomeUser1\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert usdAmt != null : "fx:id=\"usdAmt\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert btcAmt != null : "fx:id=\"btcAmt\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert ethAmt != null : "fx:id=\"ltcAmt\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert goToExch != null : "fx:id=\"goToExch\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert CurrencyHistory != null : "fx:id=\"CurrencyHistory\" was not injected: check your FXML file 'UserMain.fxml'.";
        assert viewTransHist != null : "fx:id=\"viewTransHist\" was not injected: check your FXML file 'UserMain.fxml'.";

    }

    public void setUserInfo(String uname, String fname, String lname, String em, String paymeth){
        welcomeUser.setText("Welcome" + fname);
        usrName.setText(uname);
        usrLastName.setText(lname);
        usrEmail.setText(em);
        usrpaymnetMethod.setText(paymeth);
    }

    public void setWalletInfo(Number usd, Number btc, Number eth){
        usdAmt.setText(usd.toString());
        btcAmt.setText(btc.toString());
        ethAmt.setText(eth.toString());
    }
}
