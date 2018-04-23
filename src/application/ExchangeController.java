package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DaoUsers;
import model.DaoWallet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    String[] ActiveUserID;

    DaoWallet daoWallet = new DaoWallet();
    DaoUsers daoUsers = new DaoUsers();
    int i=0;

    ChangeListener<Number> changeListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if(i==0){
                updateDataAfterChangingExchanges(newValue);
                i++;
            }
        }
    };
    public ExchangeController() throws SQLException {

        ActiveUserID = daoUsers.activeUserInfo();
    }


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

    public void setUSDBalance(String crypto) throws SQLException {
        Double[] wallet = daoWallet.getWalletAmounts();
        usdAmountLable.setText(String.valueOf(wallet[0]));
        if (crypto.equals("btc")) {
            CryptoBalLable.setText(String.valueOf(wallet[1]));
        }
        if (crypto.equals("eth")) {
            CryptoBalLable.setText(String.valueOf(wallet[2]));
        }

    }

    public void updateDataAfterChangingExchanges(Number choice){
        System.out.println(choice);
    }

    @FXML
    void changeTrade(MouseEvent event) {
        usdAmountLable.setText("mouseEvent");
        chiceDropDown.getSelectionModel()
                .selectedIndexProperty()
                .addListener(changeListener);
        i=0;
    }

    @FXML
    void onContexMenuRequested(ContextMenuEvent event) {
        System.out.println("context Menu Requested");

    }


    @FXML
    public void exit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        exit(event);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Trader");

        primaryStage.show();
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException, SQLException {
        exit(event);
        new WhichUserMainMenu("user");
    }
}