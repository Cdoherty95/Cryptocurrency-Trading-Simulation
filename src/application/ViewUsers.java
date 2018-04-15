package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.UseraccountsView;
import view.UserTableViewController;

import java.io.IOException;

public class ViewUsers extends Application{


    private Stage primaryStage;
    //= new Stage();
    private BorderPane rootLayout;
    //= new BorderPane();

    /**
     * The data as an observable list of user accounts
     */
    private ObservableList<UseraccountsView> personData = FXCollections.observableArrayList();


    public ViewUsers()  {
        setData();
    }

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
            loader.setLocation(ViewUsers.class.getResource("/view/UsersTableView.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            UserTableViewController controller = loader.getController();
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
