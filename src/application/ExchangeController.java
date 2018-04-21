package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExchangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> chiceDropDown;

    @FXML
    private Label usdLable;

    @FXML
    private Label usdAmountLable;

    @FXML
    private AnchorPane cryptoNameLable;

    @FXML
    private Label CryptoBalLable;

    @FXML
    private ToggleButton toggleBuySell;

    @FXML
    private TextField inputAmount;

    @FXML
    private Label inputAmountCurrencyLable;

    @FXML
    private Button placOrderBtn;

    @FXML
    private Label totalCurrencyLable;

    @FXML
    private Label calculatedAmountLable;

    @FXML
    private Label oneCryptoName;

    @FXML
    private Label tradeOptionName;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize() {
        assert chiceDropDown != null : "fx:id=\"chiceDropDown\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert usdLable != null : "fx:id=\"usdLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert usdAmountLable != null : "fx:id=\"usdAmountLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert cryptoNameLable != null : "fx:id=\"cryptoNameLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert CryptoBalLable != null : "fx:id=\"CryptoBalLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert toggleBuySell != null : "fx:id=\"toggleBuySell\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert inputAmount != null : "fx:id=\"inputAmount\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert inputAmountCurrencyLable != null : "fx:id=\"inputAmountCurrencyLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert placOrderBtn != null : "fx:id=\"placOrderBtn\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert totalCurrencyLable != null : "fx:id=\"totalCurrencyLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert calculatedAmountLable != null : "fx:id=\"calculatedAmountLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert oneCryptoName != null : "fx:id=\"oneCryptoName\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert tradeOptionName != null : "fx:id=\"tradeOptionName\" was not injected: check your FXML file 'Exchange.fxml'.";
        defaultStart();
    }

    public void defaultStart() {
        chiceDropDown.getItems().addAll("BTC/USD", "BTC/ETH", "ETH/USD", "ETH/BTC");
        chiceDropDown.getSelectionModel().selectFirst();


    }

    @FXML
    public void exit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void logout(ActionEvent event) {
        exit(event);
        new Main();
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException, SQLException {
        exit(event);
        new WhichUserMainMenu();
    }
}