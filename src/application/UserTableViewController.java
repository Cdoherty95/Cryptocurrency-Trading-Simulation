package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DaoUsers;
import model.UseraccountsView;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
    //@FXML
    //private TableColumn<UseraccountsView, Long> lastLoggedInCol;
    @FXML
    private TableColumn<UseraccountsView, Date> lastindate;

    @FXML
    private Button exitBtn;

    @FXML
    private Button mainMenuBtn;

    @FXML
    private AnchorPane adminAnchorPane;

    @FXML
    private TextField userIDTextField;

    @FXML
    private Button rmvUserBtn;

    @FXML
    private Label errorLabl;

    DaoUsers daoUsers = new DaoUsers();
    //0= unset, 1=Cancel, 2=continue
    static int DoubleCheck=0;



    @FXML
    public void removeUser(ActionEvent event) throws IOException, SQLException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoubleCheck.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Trader");

        primaryStage.showAndWait();
        if (DoubleCheck==2){
            if(checkInput()){
               daoUsers.deleteUser(Integer.parseInt(userIDTextField.getText()));
               System.out.println("removed User");
               //Im not sure which works
               table.getItems().clear();
               table.getItems().removeAll();
               personData.clear();
               personData.removeAll();
               setData();
               setMainApp();
            }
        }
    }

    private boolean checkInput(){
        errorLabl.setText("");
        try{
            Integer.parseInt(userIDTextField.getText());

            try {
                int adminID = Integer.parseInt(daoUsers.activeUserInfo()[0]);
                if (adminID!=Integer.parseInt(userIDTextField.getText())){
                    return true;
                }else {
                    errorLabl.setText("You cannot remove yourself");
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }catch (Exception e){
            errorLabl.setText("Input The UserID integer");
            return false;
        }
    }

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
        new WhichUserMainMenu("admin");
    }

    // Reference to the main application.
    //private ViewUsers viewUsers;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        roleCol.setCellValueFactory(new PropertyValueFactory<>("Role"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        //lastLoggedInCol.setCellValueFactory(new PropertyValueFactory<>("LastLoggedIn"));
        lastindate.setCellValueFactory(new PropertyValueFactory<>("lastindate"));
        setMainApp();
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp() {
        //ViewUsers viewUsers
        //this.viewUsers = viewUsers;
        //UserTableViewController viewUsers = new UserTableViewController();

        table.setItems(getPersonData());
    }

    private ObservableList<UseraccountsView> personData = FXCollections.observableArrayList();

    /**
     * constructor that adds data
     */
    public UserTableViewController() throws SQLException {
        setData();
    }

    /**
     * method to get data.
     */
    public void setData() throws SQLException {

        ResultSet rs = daoUsers.viewAllUsers();
        // loop through the result set
        while (rs.next()) {
            int userID = rs.getInt("UserID");
            String role = rs.getString("Role");
            Long lastLoggedInUnixTime = rs.getLong("LastLoggedIn");
            String userName = rs.getString("Username");
            Date lastin = new Date(lastLoggedInUnixTime*1000);
            personData.add(new UseraccountsView(userID, userName, role, lastin));
        }
        rs.close();
    }

    /*
    public void changeDoubleCheckValue(int i){
        DoubleCheck=i;
        System.out.println(DoubleCheck);
    }*/

    //ObservableList function to return data
    public ObservableList<UseraccountsView> getPersonData() {
        return personData;
    }


}
