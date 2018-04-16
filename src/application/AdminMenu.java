package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import view.UserTableViewController;

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

        //ViewUsers vu = new ViewUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UsersTableView.fxml"));
        Parent root = (Parent) loader.load();

        //get the controllers functions to add data
        UserTableViewController controller = loader.getController();
        controller.setMainApp();

        //set up the new stage+scene
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    void viewWallets(ActionEvent event) {

    }

    //default constructor needed
    public AdminMenu(){

    }

}