package view;

import application.ViewUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UseraccountsView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserTableViewController {


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
    private UserTableViewController cserTableViewController;
    private ViewUsers viewUsers;

    private ObservableList<UseraccountsView> personData = FXCollections.observableArrayList();

    public UserTableViewController(){
        setData();

    }

    public void setData(){
        personData.add(new UseraccountsView(1, "chris", "admin", 123L));
        personData.add(new UseraccountsView(1, "dhris", "admin", 121L));
        personData.add(new UseraccountsView(1, "fhris", "admin", 124L));
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
    public void setMainApp(ViewUsers viewUsers) {
        this.viewUsers = viewUsers;

        table.setItems(viewUsers.getPersonData());
    }

}
