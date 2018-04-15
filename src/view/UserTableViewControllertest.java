package view;

import application.ViewUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.UseraccountsView;

import java.io.IOException;

public class UserTableViewControllertest {


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

    /**
     * The data as an observable list of user accounts
     */
    private ObservableList<UseraccountsView> personData = FXCollections.observableArrayList();

    // Reference to the main application.
    // private ViewUsers viewUsers;

    private UserTableViewControllertest utvr;

    public UserTableViewControllertest() throws IOException {

        setData();
        makescene();

        utvr.setMainApp(this);
        //table.setItems(getPersonData());
    }

    public void setData(){
        personData.add(new UseraccountsView(1, "chris", "admin", 123L));
        personData.add(new UseraccountsView(1, "dhris", "admin", 121L));
        personData.add(new UseraccountsView(1, "fhris", "admin", 124L));
    }

    public void makescene() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/view/UsersTableView.fxml"));
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //ObservableList function to return data
    public ObservableList<UseraccountsView> getPersonData() {
        return personData;
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
    public void setMainApp(UserTableViewControllertest utvr) {
        this.utvr = utvr;

        table.setItems(utvr.getPersonData());
    }

}
