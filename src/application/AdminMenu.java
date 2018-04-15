package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenu {

    @FXML
    private VBox border;

    @FXML
    private Button viewUsers;

    @FXML
    private Button viewTransactionHistory;

    @FXML
    private Button viewExchange;

    @FXML
    private Button viewWallets;

    @FXML
    private Button viewExchangeHistory;

    @FXML
    void viewExchange(ActionEvent event) {

    }

    @FXML
    void viewExchangeHistory(ActionEvent event) {

    }

    @FXML
    void viewTransactionHistory(ActionEvent event) {

    }

    @FXML
    void viewUsers(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AdminMain.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void viewWallets(ActionEvent event) {

    }

    public AdminMenu(){

    }

}