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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class TransactionTableController implements DAOInterface {

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
    private TableColumn<TransactionModel, String> sccColumn;

    @FXML
    private TableColumn<TransactionModel, Double> scaColumn;

    @FXML
    private TableColumn<TransactionModel, Date> date;

    @FXML
    private Button exitBtn;

    @FXML
    private Button mainMenuBtn;

    private ObservableList<TransactionModel> tHistList = FXCollections.observableArrayList();

    String userOrAdmin = null;
    //public String allOrOne = null;

    //DaoWallet daoWallet = new DaoWallet();

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
        if (userOrAdmin.equals("admin")){
            new WhichUserMainMenu("admin");
        }else{
            new WhichUserMainMenu("user");
        }
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
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'TransactionHist.fxml'.";
        assert mainMenuBtn != null : "fx:id=\"mainMenuBtn\" was not injected: check your FXML file 'TransactionHist.fxml'.";

        transIDCol.setCellValueFactory(new PropertyValueFactory<>("transID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ccodeCol.setCellValueFactory(new PropertyValueFactory<>("firstCurrencyCode"));
        caAmount.setCellValueFactory(new PropertyValueFactory<>("firstCurrencyAmount"));
        sccColumn.setCellValueFactory(new PropertyValueFactory<>("secondCurrencyCode"));
        scaColumn.setCellValueFactory(new PropertyValueFactory<>("secondCurrencyAmount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        //Fills the table with information in the Observ list
        fillTable();
    }

    public TransactionTableController() throws SQLException {

    }

    public void whichDataToSet(String allOrOne) throws SQLException {
        if(allOrOne.equals("user")) {
            userOrAdmin = "user";
            setDataAU();
        }
        if(allOrOne.equals("admin")){
            userOrAdmin = "admin";
            setDataAll();
        }
    }

    public void fillTable() {
        table.setItems(tHistList);
    }

    public void setDataAU() throws SQLException {
        ResultSet rs = daoWallet.getTransactionHistoryActiveUser();
        while (rs.next()) {
            //get Unix timestamp * 1000 to get it to current date and cast it to date object
            Date timestamp = new Date((rs.getLong("DateAdded")*1000));
            tHistList.add(new TransactionModel(
                    rs.getInt("ID"), rs.getInt("UserID"),
                    rs.getString("Type"), rs.getString("FirstCurrencyCode"),
                    rs.getDouble("FirstCurrencyAmount"), rs.getString("SecondCurrencyCode"),
                    rs.getDouble("SecondCurrencyAmount"),
                    timestamp));
        }
        rs.close();
    }

    public void setDataAll() throws SQLException {
        ResultSet rs = daoWallet.getTransactionHistoryAll();
        while (rs.next()) {
            //get Unix timestamp * 1000 to get it to current date and cast it to date object
            Date timestamp = new Date((rs.getLong("DateAdded")*1000));
            tHistList.add(new TransactionModel(
                    rs.getInt("ID"), rs.getInt("UserID"),
                    rs.getString("Type"), rs.getString("FirstCurrencyCode"),
                    rs.getDouble("FirstCurrencyAmount"), rs.getString("SecondCurrencyCode"),
                    rs.getDouble("SecondCurrencyAmount"),
                    timestamp));
        }
        rs.close();
    }
}
