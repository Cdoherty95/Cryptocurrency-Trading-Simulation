package view;

import application.ViewUsers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.UseraccountsView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

import static javafx.application.Application.launch;

public class UserTableViewControllertest extends Application {


    //FXML Definitions
    @FXML
    private Label heading;
    @FXML
    private TableView<UseraccountsView> table;
    @FXML
    private TableColumn<UseraccountsView, Integer> userIdCol;
    @FXML
    private TableColumn<UseraccountsView, String> usernameCol;
    @FXML
    private TableColumn<UseraccountsView, String> roleCol;
    @FXML
    private TableColumn<UseraccountsView, Long> lastLoggedInCol;

    // Reference to the main application.
    private UserTableViewControllertest UserTableViewControllertest;

    private UserTableViewControllertest(){
        setData();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Role"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        lastLoggedInCol.setCellValueFactory(new PropertyValueFactory<>("LastLoggedIn"));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(UserTableViewControllertest viewUsers) {

        table.setItems(viewUsers.getPersonData());
    }

    private Stage primaryStage;
    //= new Stage();
    private BorderPane rootLayout;
    //= new BorderPane();

    /**
     * The data as an observable list of user accounts
     */
    private ObservableList<UseraccountsView> personData = FXCollections.observableArrayList();


    public void setData(){
        personData.add(new UseraccountsView(1, "chris", "admin", 123L));
        personData.add(new UseraccountsView(1, "dhris", "admin", 121L));
        personData.add(new UseraccountsView(1, "fhris", "admin", 124L));
    }

    //ObservableList function to return data
    public ObservableList<UseraccountsView> getPersonData() {
        return personData;
    }



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Crypto App");

        initRootLayout();

        DisplayUsers();

    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the person overview inside the root layout.
     */
    public void DisplayUsers() {
        try {

            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewUsers.class.getResource("/view/UsersTableViewtest.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            UserTableViewControllertest controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
