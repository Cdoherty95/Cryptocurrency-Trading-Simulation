package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DaoUsers;
import model.DaoWallet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DepositController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private TextField amountTxtfield;

    @FXML
    private Button depositBtn;

    @FXML
    private Label payMethLabel;

    @FXML
    private Label accountNumberLabel;

    @FXML
    private Label routingNumLabel;

    @FXML
    void deposit(ActionEvent event) throws SQLException, IOException {

        if (!amountTxtfield.getText().isEmpty()) {
            if(checkInput()){
                DaoWallet daoWallet = new DaoWallet();
                Double[] walletAmts = daoWallet.getWalletAmounts();
                Double total = (walletAmts[0] + Double.parseDouble(amountTxtfield.getText()));
                daoWallet.setUsdAmount(total);
                daoWallet.logTransaction("Deposit",null,0.0,Double.parseDouble(amountTxtfield.getText()));
                exit(event);
                new WhichUserMainMenu("user");
            }else {
                amountTxtfield.clear();
                amountTxtfield.setPromptText("Please Input A Number");
            }
        } else {
            amountTxtfield.clear();
            amountTxtfield.setPromptText("Please Input A Number");
        }
    }

    @FXML
    void exit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException, SQLException {
        exit(event);
        new WhichUserMainMenu("user");
    }

    @FXML
    void initialize() throws SQLException {
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'Deposit.fxml'.";
        assert mainMenuBtn != null : "fx:id=\"mainMenuBtn\" was not injected: check your FXML file 'Deposit.fxml'.";
        assert amountTxtfield != null : "fx:id=\"amountTxtfield\" was not injected: check your FXML file 'Deposit.fxml'.";
        assert depositBtn != null : "fx:id=\"depositBtn\" was not injected: check your FXML file 'Deposit.fxml'.";
        assert payMethLabel != null : "fx:id=\"payMethLabel\" was not injected: check your FXML file 'Deposit.fxml'.";
        assert accountNumberLabel != null : "fx:id=\"accountNumberLabel\" was not injected: check your FXML file 'Deposit.fxml'.";
        assert routingNumLabel != null : "fx:id=\"routingNumLabel\" was not injected: check your FXML file 'Deposit.fxml'.";

        setUserInfo();
    }

    public void setUserInfo() throws SQLException {
        DaoUsers dao = new DaoUsers();
        String[] userInfo = dao.activeUserInfo();

        payMethLabel.setText(userInfo[6]);
        accountNumberLabel.setText(userInfo[7]);
        routingNumLabel.setText(userInfo[8]);
    }

    public boolean checkInput(){
        try {
            Double.parseDouble(amountTxtfield.getText());
        } catch (NumberFormatException e) {
            System.out.println("Input Must be an Integer Or double");
            return false;
        }
        return true;
    }
}
