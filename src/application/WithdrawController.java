package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DaoUsers;
import model.DaoWallet;

public class WithdrawController implements DAOInterface {

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
    private Button withdrawBtn;

    @FXML
    private Label payMethLabel;

    @FXML
    private Label accountNumberLabel;

    @FXML
    private Label routingNumLabel;

    @FXML
    private Label currentBalLabel;

    @FXML
    private Label errorLabel;

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
    void withdraw(ActionEvent event) throws SQLException, IOException {
        if (!amountTxtfield.getText().isEmpty()) {
            if(checkInput()){
                System.out.println("Input is valid");
                Double[] walletAmts = daoWallet.getWalletAmounts();
                Double total = (walletAmts[0] - Double.parseDouble(amountTxtfield.getText()));
                System.out.println("Total after withdraw would be " + total);
                if(total>0){
                    daoWallet.setUsdAmount(total);
                    System.out.println("Set USD to amount minus the withdraw");
                    daoWallet.logTransaction("Withdraw","USD", Double.parseDouble(amountTxtfield.getText()), null, 0.0);
                    mainMenu(event);
                }else {
                    amountTxtfield.clear();
                    amountTxtfield.setPromptText("Balance Cannot Be Negative");
                }

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
    void initialize() throws SQLException {
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert mainMenuBtn != null : "fx:id=\"mainMenuBtn\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert amountTxtfield != null : "fx:id=\"amountTxtfield\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert withdrawBtn != null : "fx:id=\"withdrawBtn\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert payMethLabel != null : "fx:id=\"payMethLabel\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert accountNumberLabel != null : "fx:id=\"accountNumberLabel\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert routingNumLabel != null : "fx:id=\"routingNumLabel\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert currentBalLabel != null : "fx:id=\"currentBalLabel\" was not injected: check your FXML file 'Withdraw.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'Withdraw.fxml'.";

        setUserInfo();
        setWalletInfo();
    }

    public void setUserInfo() throws SQLException {

        String[] userInfo = daoUsers.activeUserInfo();

        payMethLabel.setText(userInfo[6]);
        accountNumberLabel.setText(userInfo[7]);
        routingNumLabel.setText(userInfo[8]);
    }

    public void setWalletInfo() throws SQLException {
        Double[] wallet = daoWallet.getWalletAmounts();
        currentBalLabel.setText(String.valueOf(wallet[0]));
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
