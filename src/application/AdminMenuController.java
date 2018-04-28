package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminMenuController implements MenuOperations {

    @FXML
    private VBox border;

    @FXML
    private Button viewUsers;

    @FXML
    private Button viewTransactionHistory;

    //@FXML
    //private Button viewWallets;

    //@FXML
    //private Button viewExchangeHistory;

    @FXML
    private Label welcomeLabel;

    //@FXML
    //private Button logoutBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize() {
        assert border != null : "fx:id=\"border\" was not injected: check your FXML file 'AdminMain.fxml'.";
        assert viewUsers != null : "fx:id=\"viewUsers\" was not injected: check your FXML file 'AdminMain.fxml'.";
        assert viewTransactionHistory != null : "fx:id=\"viewTransactionHistory\" was not injected: check your FXML file 'AdminMain.fxml'.";
        //assert viewWallets != null : "fx:id=\"viewWallets\" was not injected: check your FXML file 'AdminMain.fxml'.";
        //assert viewExchangeHistory != null : "fx:id=\"viewExchangeHistory\" was not injected: check your FXML file 'AdminMain.fxml'.";
        assert welcomeLabel != null : "fx:id=\"welcomeLabel\" was not injected: check your FXML file 'AdminMain.fxml'.";
        welcomeLabel.setText("Welcome Chris");
    }

    //default constructor needed
    public AdminMenuController(){

    }

    @FXML
    public void viewTransactionHistory(ActionEvent event) throws IOException, SQLException {
        exit(event);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TransactionHist.fxml"));
        Parent root = loader.load();
        TransactionTableController controller = loader.getController();
        controller.whichDataToSet("admin");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Trader");

        primaryStage.show();
    }

    @FXML
    public void viewUsers(ActionEvent event) throws IOException {
        exit(event);
        //ViewUsers vu = new ViewUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UsersTableView.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    void backToUserMenu(ActionEvent event) throws IOException, SQLException {
        exit(event);
        new WhichUserMainMenu("user");
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
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
    public void exit(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}