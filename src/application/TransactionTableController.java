package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.BtcHistory;
import model.DaoWallet;
import model.EthHistory;
import model.TransactionModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class TransactionTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label heading;

    @FXML
    private TableView<TransactionModel> table;

    @FXML
    private TableColumn<TransactionModel, Integer> transIDCol;

    @FXML
    private TableColumn<TransactionModel, Integer> userIDCol;

    @FXML
    private TableColumn<TransactionModel, String> typeCol;

    @FXML
    private TableColumn<TransactionModel, String> ccodeCol;

    @FXML
    private TableColumn<TransactionModel, Double> caAmount;

    @FXML
    private TableColumn<TransactionModel, Double> usdAmtCol;

    @FXML
    private TableColumn<TransactionModel, Date> date;

    @FXML
    private Button exitBtn;

    @FXML
    private Button mainMenuBtn;

    private ObservableList<TransactionModel> transModel = FXCollections.observableArrayList();

    @FXML
    void exit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void menu(ActionEvent event) throws IOException, SQLException {
        exit(event);
        new WhichUserMainMenu();
    }

    @FXML
    void initialize() {
        assert heading != null : "fx:id=\"heading\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert transIDCol != null : "fx:id=\"transIDCol\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert userIDCol != null : "fx:id=\"userIDCol\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert typeCol != null : "fx:id=\"typeCol\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert ccodeCol != null : "fx:id=\"ccodeCol\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert caAmount != null : "fx:id=\"caAmount\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert usdAmtCol != null : "fx:id=\"usdAmtCol\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert mainMenuBtn != null : "fx:id=\"mainMenuBtn\" was not injected: check your FXML file 'TransactionHist.fxml'.";

        transIDCol.setCellValueFactory(new PropertyValueFactory<>("transID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ccodeCol.setCellValueFactory(new PropertyValueFactory<>("crycode"));
        caAmount.setCellValueFactory(new PropertyValueFactory<>("cAmt"));
        usdAmtCol.setCellValueFactory(new PropertyValueFactory<>("usAmt"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public TransactionTableController(){
        setData();
    }

    public void fillBothTables() {
        table.setItems(transModel);
    }

    public void setData(){
        DaoWallet daoWallet = new DaoWallet();
        /**
         * NEED TO FINISH THIS!
         */
    }
}
