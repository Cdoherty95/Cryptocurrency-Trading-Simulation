package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DaoUpdateCurrencyHist;
import model.DaoUsers;
import model.DaoWallet;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.round;

public class ExchangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> chiceDropDown;

    @FXML
    private Label currency1Lable;

    @FXML
    private Label currency1AmountLable;

    @FXML
    private Label currency2Lable;

    @FXML
    private Label currency2AmountLable;

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
    private Label errorLabl;

    @FXML
    private Button exitBtn;


    String[] ActiveUserID;
    DaoWallet daoWallet = new DaoWallet();
    DaoUsers daoUsers = new DaoUsers();
    DaoUpdateCurrencyHist daoCurrencyHist = new DaoUpdateCurrencyHist();
    int i = 0;
    double usdBTCHist = 0, btcHist = 0, usdETHHist = 0, ethHist = 0, total = 0, balanceAfter = 0, userIn =0;
    String exchangingOne = null, exchangeTo = null;
    Double[] wallet;


    ChangeListener<Number> changeListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (i == 0) {
                try {
                    updateDataAfterChangingExchanges(newValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    };


    public ExchangeController() throws SQLException {
        ActiveUserID = daoUsers.activeUserInfo();
        wallet = daoWallet.getWalletAmounts();

    }


    @FXML
    void initialize() throws SQLException {
        assert chiceDropDown != null : "fx:id=\"chiceDropDown\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert currency1Lable != null : "fx:id=\"currency1Lable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert currency1AmountLable != null : "fx:id=\"currency1AmountLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert currency2Lable != null : "fx:id=\"currency2Lable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert currency2AmountLable != null : "fx:id=\"currency2AmountLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert toggleBuySell != null : "fx:id=\"toggleBuySell\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert inputAmount != null : "fx:id=\"inputAmount\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert inputAmountCurrencyLable != null : "fx:id=\"inputAmountCurrencyLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert placOrderBtn != null : "fx:id=\"placOrderBtn\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert totalCurrencyLable != null : "fx:id=\"totalCurrencyLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert calculatedAmountLable != null : "fx:id=\"calculatedAmountLable\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert oneCryptoName != null : "fx:id=\"oneCryptoName\" was not injected: check your FXML file 'Exchange.fxml'.";
        assert tradeOptionName != null : "fx:id=\"tradeOptionName\" was not injected: check your FXML file 'Exchange.fxml'.";
        defaultStart();
        inputAmount.textProperty().addListener((observable, oldValue, userInput) -> {
            System.out.println("textfield changed from " + oldValue + " to " + userInput);
            if(!userInput.isEmpty()) {
                userIn = Double.parseDouble(userInput.replaceAll("[^\\d.]", ""));
                if (toggleBuySell.isSelected()) {//Sell Mode
                    //need to see what null user input is
                        //if Stmts for Cases
                        total = (userIn * Double.parseDouble(tradeOptionName.getText().replaceAll("[^\\d.]", "")));
                        calculatedAmountLable.setText(String.valueOf(round(total, 2)));

                } else {//Buy Mode
                        total = (userIn / Double.parseDouble(tradeOptionName.getText().replaceAll("[^\\d.]", "")));
                        calculatedAmountLable.setText(String.valueOf(round(total, 2)));

                }
            }
        });


    }

    @FXML
    void changeBuySell(ActionEvent event) {
        toggleBuySell.setOnAction(e -> {
            toggleBuySell.getStyleClass().removeAll("red-button", "blue-button");
            if (toggleBuySell.isSelected()) {
                toggleBuySell.getStyleClass().add("red-button");
                toggleBuySell.setText("Sell");
                System.out.println("toggle Selected Sell");
            } else {
                toggleBuySell.getStyleClass().add("blue-button");
                toggleBuySell.setText("Buy");
                System.out.println("toggle Selected buy");
            }
            setAmountLables();
        });
    }

    @FXML
    void placeOrder(ActionEvent event) {
        errorLabl.setText("");
        userIn = Double.parseDouble(inputAmount.getText().replaceAll("[^\\d.]", ""));
        if(toggleBuySell.isSelected()){//Sell
            if(userIn<=Double.parseDouble(currency1AmountLable.getText())){
                double balAfter = (Double.parseDouble(currency1AmountLable.getText())-userIn);
                //balance of crypto after trading
                balanceAfter = (Double.parseDouble(currency2AmountLable.getText().replaceAll("[^\\d.]", ""))+total);
                System.out.println(exchangeTo+"Balance after trade" + balanceAfter);
                System.out.println(exchangingOne + "Balance Change to" +balAfter);

                //If funds are sufficient set the balances
                if(exchangeTo.equals("BTC") && exchangingOne.equals("USD")){
                    System.out.println("BTC Balance after trade" + balanceAfter);
                    System.out.println("USD Balance Change to" +balAfter);
                }
                //If funds are sufficient set the balances
                if(exchangeTo.equals("BTC") && exchangingOne.equals("ETH")){
                    System.out.println("BTC Balance after trade" + balanceAfter);
                    System.out.println("ETH Balance Change to" +balAfter);
                }
                if(exchangeTo.equals("ETH") && exchangingOne.equals("USD")){
                    System.out.println("ETH Balance after trade" + balanceAfter);
                    System.out.println("USD Balance Change to" + balAfter);
                }
                //If funds are sufficient set the balances
                if(exchangeTo.equals("ETH") && exchangingOne.equals("BTC")){
                    System.out.println("ETH Balance after trade" + balanceAfter);
                    System.out.println("BTC Balance Change to" +balAfter);
                }
            }else {
                errorLabl.setText("Cannot trade more than you have");
            }

        }else {//Buy
            //if user input is less than or equal to current balance of currency
            if(userIn<=Double.parseDouble(currency2AmountLable.getText())){
                double balAfter = (Double.parseDouble(currency2AmountLable.getText())-userIn);
                balanceAfter = (Double.parseDouble(currency1AmountLable.getText().replaceAll("[^\\d.]", ""))+total);
                System.out.println(exchangingOne+"Balance after trade" + balanceAfter);
                System.out.println( exchangeTo+ "Balance Change to" +balAfter);

                //If funds are sufficient set the balances
                if(exchangeTo.equals("BTC") && exchangingOne.equals("USD")){
                    System.out.println("BTC Balance after trade" + balanceAfter);
                    System.out.println("USD Balance Change to" +balAfter);
                }
                //If funds are sufficient set the balances
                if(exchangeTo.equals("BTC") && exchangingOne.equals("ETH")){
                    System.out.println("BTC Balance after trade" + balanceAfter);
                    System.out.println("ETH Balance Change to" +balAfter);
                }
                if(exchangeTo.equals("ETH") && exchangingOne.equals("USD")){
                    System.out.println("ETH Balance after trade" + balanceAfter);
                    System.out.println("USD Balance Change to" + balAfter);
                }
                //If funds are sufficient set the balances
                if(exchangeTo.equals("ETH") && exchangingOne.equals("BTC")){
                    System.out.println("ETH Balance after trade" + balanceAfter);
                    System.out.println("BTC Balance Change to" +balAfter);
                }

            }else {
                errorLabl.setText("Cannot trade more than you have");
            }

        }

    }

    public void defaultStart() throws SQLException {
        chiceDropDown.getItems().addAll("BTC/USD", "BTC/ETH", "ETH/USD", "ETH/BTC");
        chiceDropDown.getSelectionModel().selectFirst();
        //sets the toggle Button background color to blue
        toggleBuySell.getStyleClass().add("blue-button");
        updateDataAfterChangingExchanges(0);
    }

    public void setCurrency1LableandAmount(String currency) throws SQLException {
        currency1AmountLable.setText(String.valueOf(wallet[0]));
    }

    public void setAmountLables(){
        if (toggleBuySell.isSelected()) { // SELLL
            //Change the label in the textField
            inputAmountCurrencyLable.setText(exchangingOne);
            //Change Total Lable
            totalCurrencyLable.setText("Total(" + exchangeTo + ") = ");
        } else {//BUY
            //Change the label in the textField
            inputAmountCurrencyLable.setText(exchangeTo);
            //Change Total Lable
            totalCurrencyLable.setText("Total(" + exchangingOne + ") = ");
        }
    }

    public void setBalancesBoxes(String currency1, String currency2) throws SQLException {
        if (currency1.equals("BTC")) {
            currency1Lable.setText("BTC");
            currency1AmountLable.setText(String.valueOf(wallet[1]));
        }
        if (currency1.equals("ETH")) {
            currency1Lable.setText("ETH");
            currency1AmountLable.setText(String.valueOf(wallet[2]));

        }
        if (currency2.equals("BTC")) {
            currency2Lable.setText("BTC");
            currency2AmountLable.setText(String.valueOf(wallet[1]));
        }
        if (currency2.equals("ETH")) {
            currency2Lable.setText("ETH");
            currency2AmountLable.setText(String.valueOf(wallet[2]));

        }
        if (currency2.equals("USD")) {
            currency2Lable.setText("USD");
            currency2AmountLable.setText(String.valueOf(wallet[0]));
        }
    }

    public void setExchangeRates(String oneCrypto, String twoValue) throws SQLException {
        setOneCryptoName(oneCrypto);
        setTradeOptionName(oneCrypto, twoValue);
    }

    public void setOneCryptoName(String name) {
        if (name.equals("BTC")) {
            oneCryptoName.setText("1 BTC");
        }
        if (name.equals("ETH")) {
            oneCryptoName.setText("1 ETH");
        }
    }

    public void setTradeOptionName(String crypto, String trade) throws SQLException {

        ResultSet resultSet;
        resultSet = daoCurrencyHist.getEthHist();

        while (resultSet.next()) {
            //gets the USD amount for 1 ETH
            usdETHHist = resultSet.getDouble("USDAmt");
            //gets the BTC amount for 1 eth
            btcHist = resultSet.getDouble("CryptoAmt");
        }
        resultSet.close();

        resultSet = daoCurrencyHist.getBtcHist();

        while (resultSet.next()) {
            //gets the USD amount for 1BTC
            usdBTCHist = resultSet.getDouble("USDAmt");
            //gets the ETH amount for 1BTC
            ethHist = resultSet.getDouble("CryptoAmt");
        }
        resultSet.close();

        if (trade.equals("USD") && crypto.equals("BTC")) {
            tradeOptionName.setText(String.valueOf(usdBTCHist) + " USD");
            if (trade.equals("BTC")) {
                tradeOptionName.setText(String.valueOf(btcHist) + " BTC");
            }
            if (trade.equals("ETH")) {
                tradeOptionName.setText(String.valueOf(ethHist) + " ETH");
            }
        }
        if (trade.equals("USD") && crypto.equals("ETH")) {
            tradeOptionName.setText(String.valueOf(usdETHHist) + " USD");
            if (trade.equals("BTC")) {
                tradeOptionName.setText(String.valueOf(btcHist) + " BTC");
            }
            if (trade.equals("ETH")) {
                tradeOptionName.setText(String.valueOf(ethHist) + " ETH");
            }
        }
        if (!trade.equals("USD")) {
            if (trade.equals("BTC")) {
                tradeOptionName.setText(String.valueOf(btcHist) + " BTC");
            }
            if (trade.equals("ETH")) {
                tradeOptionName.setText(String.valueOf(ethHist) + " ETH");
            }
        }

    }

    /*
     *Calls all the necessary functions to set the correct values based
     * on users choice from Choice Box
     */

    public void updateDataAfterChangingExchanges(Number ch) throws SQLException {

        System.out.println(ch);
        /*
        WORKING HERE
        BTC/USD = 0
        BTC/ETH = 1
        ETH/USD=2
        ETH/BTC=3
         */

        int choice = Integer.parseInt(String.valueOf(ch));
        if (choice == 0) {
            exchangingOne = "BTC";
            exchangeTo = "USD";
        }
        if (choice == 1) {
            exchangingOne = "BTC";
            exchangeTo = "ETH";
        }
        if (choice == 2) {
            exchangingOne = "ETH";
            exchangeTo = "USD";
        }
        if (choice == 3) {
            exchangingOne = "ETH";
            exchangeTo = "BTC";
        }
        setAmountLables();
        setBalancesBoxes(exchangingOne, exchangeTo);
        setExchangeRates(exchangingOne, exchangeTo);

        System.out.println(choice + " choice value");
    }

    @FXML
    void changeTrade(MouseEvent event) {
        // currency1AmountLable.setText("mouseEvent");
        chiceDropDown.getSelectionModel()
                .selectedIndexProperty()
                .addListener(changeListener);
        i = 0;
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