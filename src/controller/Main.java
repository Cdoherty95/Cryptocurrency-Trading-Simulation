package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application implements DAOInterface {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        //this makes all the Active Users Inactive
        daoUsers.makeAllInactive();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cryptocurrency Trader");
            //primaryStage.getIcons().add(new Image("icon.jpg"));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buildDB(){
        daoDBFunctions.createAllTables();
    }

    public static void main(String[] args) throws SQLException {
        buildDB();
        //this makes all the Active Users Inactive
        daoUsers.makeAllInactive();
        UpdateCurrencyPrices updCur = new UpdateCurrencyPrices();
        updCur.start();
        launch(args);
        //this makes all the Active Users Inactive
        daoUsers.makeAllInactive();
        updCur.stop();

    }
}
