package controller;

import javafx.application.Platform;
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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.round;

public class ExchangeController implements DAOInterface {

    /*
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    */

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

    private Thread t;
    private int i = 0;
    private double usdBTCHist = 0, btcHist = 0, usdETHHist = 0, ethHist = 0, total = 0, balanceAfter = 0, userIn = 0;
    private String exchangingOne = null, exchangeTo = null, exchangeRate = null;
    private Double[] wallet;
    private Number menuOption;
    private boolean run = true;
    DaoUpdateCurrencyHist daoUpdateCurrencyHist = new DaoUpdateCurrencyHist();


    private ChangeListener<Number> changeListener = new ChangeListener<>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (i == 0) {
                try {
                    menuOption = newValue;
                    updateDataAfterChangingExchanges(menuOption);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    };

    public ExchangeController() throws SQLException {
        //ActiveUserID = daoUsers.activeUserInfo();
        //wallet = daoWallet.getWalletAmounts();
        updateWallet();
    }

    private void updateWallet() throws SQLException {
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
       /**
        * This listener brakes when running program from jar file.
        * Replaced with a refresh button to show total
        * updateTotal();
        */

    }

    @FXML
    void refreshTotal(ActionEvent event) {

        if (!inputAmount.getText().isEmpty()) {
            userIn = Double.parseDouble(inputAmount.getText().replaceAll("[^\\d.]", ""));
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
    }

    void updateTotal() {
        Platform.runLater(() ->
                inputAmount.textProperty().addListener((observable, oldValue, userInput) -> {
                    System.out.println("textfield changed from " + oldValue + " to " + userInput);
                    if (!userInput.isEmpty()) {
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
                }));

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
    void placeOrder(ActionEvent event) throws SQLException {
        System.out.println("Place Order Btn Clicked");
        errorLabl.setText("");
        userIn = Double.parseDouble(inputAmount.getText().replaceAll("[^\\d.]", ""));
        //Sell
        if (toggleBuySell.isSelected()) {
            //Recalculate the total just to make sure the user didnt change anything
            total = (userIn * Double.parseDouble(tradeOptionName.getText().replaceAll("[^\\d.]", "")));
            //sets the calculated amout label
            calculatedAmountLable.setText(String.valueOf(round(total, 2)));

            //if we are trading eth for BTC
            if (exchangingOne.equals("ETH") && exchangeTo.equals("BTC")) {
                System.out.println("Exchanging ETH FOR BTC");
                System.out.println(total);
                //if the user input is less than the balance in the bank
                if (userIn <= Double.parseDouble(currency1AmountLable.getText())) {
                    //subtracts the amount from the users bank balance
                    double balAfter = (Double.parseDouble(currency1AmountLable.getText()) - userIn);
                    //Adds to the usr's account balance
                    double balanceAfterGetValues = (Double.parseDouble(currency2AmountLable.getText().replaceAll("[^\\d.]", "")) + total);

                    //If funds are sufficient set the balances
                    if (exchangeTo.equals("BTC") && exchangingOne.equals("ETH")) {
                        System.out.println("BTC Balance after trade" + balanceAfterGetValues);
                        System.out.println("ETH Balance Change to" + balAfter);
                        daoWallet.setBtcAmount(round(balanceAfterGetValues, 2));
                        daoWallet.setEthAmount(round(balAfter, 2));
                        daoWallet.logTransaction("Sell ETH for BTC", "ETH", round(balanceAfterGetValues, 2), "ETH", round(balAfter, 2));
                        updateDataAfterChangingExchanges(menuOption);
                    }

                } else {
                    errorLabl.setText("Cannot trade more than you have");
                }
            }
            //We are trading ETH OR BTC for USD
            else {
                if (userIn <= Double.parseDouble(currency1AmountLable.getText())) {
                    double cryptoBalAfter = (Double.parseDouble(currency1AmountLable.getText()) - userIn);
                    //balance of crypto after trading
                    double usdBalanceAfter = (Double.parseDouble(currency2AmountLable.getText().replaceAll("[^\\d.]", "")) + total);

                    //If funds are sufficient set the balances
                    if (exchangeTo.equals("USD") && exchangingOne.equals("BTC")) {
                        System.out.println("BTC Balance after trade" + cryptoBalAfter);
                        System.out.println("USD Balance Change to" + usdBalanceAfter);
                        daoWallet.setUsdAmount(round(usdBalanceAfter, 2));
                        daoWallet.setBtcAmount(round(cryptoBalAfter, 2));
                        daoWallet.logTransaction("Sell BTC for USD", "BTC", round(cryptoBalAfter, 2), "USD", round(usdBalanceAfter, 2));
                        updateDataAfterChangingExchanges(menuOption);
                    }
                    //If funds are sufficient set the balances
                    if (exchangeTo.equals("USD") && exchangingOne.equals("ETH")) {
                        System.out.println("ETH Balance after trade" + cryptoBalAfter);
                        System.out.println("USD Balance Change to" + usdBalanceAfter);
                        daoWallet.setUsdAmount(round(usdBalanceAfter, 2));
                        daoWallet.setEthAmount(round(cryptoBalAfter, 2));
                        daoWallet.logTransaction("Sell ETH for USD", "ETH", round(cryptoBalAfter, 2), "USD", round(usdBalanceAfter, 2));
                        updateDataAfterChangingExchanges(menuOption);
                    }
                } else {
                    errorLabl.setText("Cannot trade more than you have");
                }
            }

        } else {//Buy
            total = (userIn / Double.parseDouble(tradeOptionName.getText().replaceAll("[^\\d.]", "")));
            calculatedAmountLable.setText(String.valueOf(round(total, 2)));

            //if we are trading eth for BTC
            if (exchangingOne.equals("ETH") && exchangeTo.equals("BTC")) {
                System.out.println("Exchanging ETH FOR BTC");
                System.out.println(total);
                if (userIn <= Double.parseDouble(currency2AmountLable.getText())) {
                    double btcBalAfter = (Double.parseDouble(currency2AmountLable.getText()) - userIn);
                    double ethBalanceAfterGetValues = (Double.parseDouble(currency2AmountLable.getText().replaceAll("[^\\d.]", "")) + total);

                    //If funds are sufficient set the balances
                    if (exchangeTo.equals("BTC") && exchangingOne.equals("ETH")) {
                        System.out.println("BTC Balance after trade" + btcBalAfter);
                        System.out.println("ETH Balance Change to" + ethBalanceAfterGetValues);
                        daoWallet.setEthAmount(round(ethBalanceAfterGetValues, 2));
                        daoWallet.setBtcAmount(round(btcBalAfter, 2));
                        daoWallet.logTransaction("Buy BTC with ETH", "BTC", round(btcBalAfter, 2), "ETH",
                                round(ethBalanceAfterGetValues, 2));
                        updateDataAfterChangingExchanges(menuOption);
                    }

                } else {
                    errorLabl.setText("Cannot trade more than you have");
                }
            }
            //If we are exchanging BTC OR ETH FOR USD
            else {
                System.out.println("we are exchanging BTC OR ETH FOR USD");
                if (userIn <= Double.parseDouble(currency2AmountLable.getText())) {
                    double cryptoBalAfter = (Double.parseDouble(currency1AmountLable.getText()) + total);
                    double usdBalanceAfterGetValues = (Double.parseDouble(currency2AmountLable.getText().replaceAll("[^\\d.]", "")) - userIn);

                    //If funds are sufficient set the balances
                    if (exchangeTo.equals("USD") && exchangingOne.equals("BTC")) {
                        System.out.println("BTC Balance after trade" + cryptoBalAfter);
                        System.out.println("USD Balance Change to" + usdBalanceAfterGetValues);
                        daoWallet.setBtcAmount(round(cryptoBalAfter, 2));
                        daoWallet.setUsdAmount(round(usdBalanceAfterGetValues, 2));
                        daoWallet.logTransaction("Buy BTC with USD", "USD", round(usdBalanceAfterGetValues, 2),
                                "BTC", round(cryptoBalAfter, 2));
                        updateDataAfterChangingExchanges(menuOption);
                    }
                    if (exchangeTo.equals("USD") && exchangingOne.equals("ETH")) {
                        System.out.println("ETH Balance after trade" + cryptoBalAfter);
                        System.out.println("USD Balance Change to" + usdBalanceAfterGetValues);
                        daoWallet.setEthAmount(round(cryptoBalAfter, 2));
                        daoWallet.setUsdAmount(round(usdBalanceAfterGetValues, 2));
                        daoWallet.logTransaction("Buy ETH with USD", "USD", round(usdBalanceAfterGetValues, 2),
                                "ETH", round(cryptoBalAfter, 2));
                        updateDataAfterChangingExchanges(menuOption);
                    }
                } else {
                    errorLabl.setText("Cannot trade more than you have");
                }
            }

        }

    }

    private void defaultStart() throws SQLException {

        chiceDropDown.getItems().addAll("BTC/USD", "ETH/USD", "ETH/BTC");
        chiceDropDown.getSelectionModel().selectFirst();
        //sets the toggle Button background color to blue
        toggleBuySell.getStyleClass().add("blue-button");
        menuOption = 0;
        updateDataAfterChangingExchanges(menuOption);
    }

    private void setAmountLables() {
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

    private void setBalancesBoxes(String currency1, String currency2) {
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

    private void setExchangeRates(String oneCrypto, String twoValue) throws SQLException {
        setOneCryptoName(oneCrypto);
        setTradeOptionName(oneCrypto, twoValue);
    }

    private void setOneCryptoName(String name) {
        if (name.equals("BTC")) {
            oneCryptoName.setText("1 BTC");
        }
        if (name.equals("ETH")) {
            oneCryptoName.setText("1 ETH");
        }
    }

    private void setTradeOptionName(String crypto, String trade) throws SQLException {
        System.out.println("Called setTradeOptionName");
        tradeOptionName.setText("");
        ResultSet resultSet;
        resultSet = daoUpdateCurrencyHist.get1EthHist();

        while (resultSet.next()) {
            //gets the USD amount for 1 ETH
            usdETHHist = resultSet.getDouble("USDAmt");
            //gets the BTC amount for 1 eth
            btcHist = resultSet.getDouble("CryptoAmt");
        }
        resultSet.close();

        resultSet = daoUpdateCurrencyHist.get1BtcHist();

        while (resultSet.next()) {
            //gets the USD amount for 1BTC
            usdBTCHist = resultSet.getDouble("USDAmt");
            //gets the ETH amount for 1BTC
            ethHist = resultSet.getDouble("CryptoAmt");
        }
        resultSet.close();

        if (trade.equals("USD") && crypto.equals("BTC")) {
            exchangeRate = (String.valueOf(usdBTCHist) + " USD");
            tradeOptionName.setText(exchangeRate);
            if (trade.equals("BTC")) {
                exchangeRate = (String.valueOf(btcHist) + " BTC");
                tradeOptionName.setText(exchangeRate);
            }
            if (trade.equals("ETH")) {
                exchangeRate = (String.valueOf(ethHist) + " ETH");
                tradeOptionName.setText(exchangeRate);
            }
        }
        if (trade.equals("USD") && crypto.equals("ETH")) {
            exchangeRate = (String.valueOf(usdETHHist) + " USD");
            tradeOptionName.setText(exchangeRate);
            if (trade.equals("BTC")) {
                exchangeRate = (String.valueOf(btcHist) + " BTC");
                tradeOptionName.setText(exchangeRate);
            }
            if (trade.equals("ETH")) {
                exchangeRate = (String.valueOf(btcHist) + " ETH");
                tradeOptionName.setText(exchangeRate);
            }
        }
        if (!trade.equals("USD")) {
            if (trade.equals("BTC")) {
                exchangeRate = (String.valueOf(btcHist) + " BTC");
                tradeOptionName.setText(exchangeRate);
            }
            if (trade.equals("ETH")) {
                exchangeRate = (String.valueOf(btcHist) + " ETH");
                tradeOptionName.setText(exchangeRate);
            }
        }

    }

    /*
     *Calls all the necessary functions to set the correct values based
     * on users choice from Choice Box
     */

    private void updateDataAfterChangingExchanges(Number ch) throws SQLException {

        System.out.println(ch);
        /*
        WORKING HERE
        BTC/USD = 0
        NO LONGER BTC/ETH = 1
        ETH/USD=1
        ETH/BTC=2
         */
        updateWallet();
        int choice = Integer.parseInt(String.valueOf(ch));
        if (choice == 0) {
            exchangingOne = "BTC";
            exchangeTo = "USD";
        }/*
        if (choice == 1) {
            exchangingOne = "BTC";
            exchangeTo = "ETH";
        }*/
        if (choice == 1) {
            exchangingOne = "ETH";
            exchangeTo = "USD";
        }
        if (choice == 2) {
            exchangingOne = "ETH";
            exchangeTo = "BTC";
        }
        //start();
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
        Parent root = loader.load();
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