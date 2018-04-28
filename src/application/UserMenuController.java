package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import model.DaoUsers;
import model.DaoWallet;

public class UserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar topBar;

    @FXML
    private Label welcomeUser;

    @FXML
    private Label usrName;

    @FXML
    private Label usrLastName;

    @FXML
    private Label usrEmail;

    @FXML
    private Label usrpaymnetMethod;

    @FXML
    private Label walletHeading;

    @FXML
    private Label usdAmt;

    @FXML
    private Label btcAmt;

    @FXML
    private Label ethAmt;

    @FXML
    private Button goToExch;

    @FXML
    private Button CurrencyHistory;

    @FXML
    private Button viewTransHist;

    @FXML
    private Button DepositFunds;

    @FXML
    private Button UpdateUserInfo;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button adminMenuBtn;

    @FXML
    void depositeFundsBtn(ActionEvent event) throws IOException {
        exit(event);
        //ViewUsers vu = new ViewUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Deposit.fxml"));
        Parent root = (Parent) loader.load();

        //set up the new stage+scene
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    void withdrawFundsBtn(ActionEvent event) throws IOException {
        exit(event);
        //ViewUsers vu = new ViewUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Withdraw.fxml"));
        Parent root = (Parent) loader.load();

        //set up the new stage+scene
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    void goToExchange(ActionEvent event) throws IOException {
        exit(event);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Exchange.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Trader");

        primaryStage.show();
    }

    @FXML
    void viewCurrencyHistory(ActionEvent event) throws IOException {
        exit(event);
        //ViewUsers vu = new ViewUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CurrencyHistroy.fxml"));
        Parent root = (Parent) loader.load();

        //set up the new stage+scene
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    void viewTransactionHistory(ActionEvent event) throws IOException, SQLException {
        exit(event);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TransactionHist.fxml"));
        Parent root = (Parent) loader.load();
        TransactionTableController controller = loader.getController();
        controller.whichDataToSet("user");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Trader");

        primaryStage.show();
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
    void exit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void updInfoBtn(ActionEvent event) {

    }

    @FXML
    void initialize() throws SQLException {

    }

    @FXML
    void goToAdminMenu(ActionEvent event) throws IOException {
        exit(event);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminMain.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Trader");

        primaryStage.show();
    }

    public void disableAdminBtn(boolean y) throws SQLException {
        //adminMenuBtn.managedProperty().bind(adminMenuBtn.visibleProperty());
        adminMenuBtn.setVisible(y);
        setUserInfo();
        setWalletInfo();
    }


    public void setUserInfo() throws SQLException {
        DaoUsers dao = new DaoUsers();
        String[] userInfo = dao.activeUserInfo();

        welcomeUser.setText("Welcome " + userInfo[4]);
        usrName.setText(userInfo[1]);
        usrLastName.setText(userInfo[5]);
        usrEmail.setText(userInfo[9]);
        usrpaymnetMethod.setText(userInfo[6]);
    }

    public void setWalletInfo() throws SQLException {
        DaoWallet dao = new DaoWallet();
        Double[] wallet = dao.getWalletAmounts();
        usdAmt.setText(String.valueOf(wallet[0]));
        btcAmt.setText(String.valueOf(wallet[1]));
        ethAmt.setText(String.valueOf(wallet[2]));
    }
}
