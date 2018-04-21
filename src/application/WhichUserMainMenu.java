package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DaoUsers;

import java.io.IOException;
import java.sql.SQLException;


public class WhichUserMainMenu {
    DaoUsers dao = new DaoUsers();

    public WhichUserMainMenu() throws IOException, SQLException {
        figureOutActiveUser();
    }

    public void figureOutActiveUser() throws IOException, SQLException {
        String[] userInfo = dao.activeUserInfo();
        for (String s : userInfo) {
            System.out.println(s);
        }
        if (userInfo[2].equals("admin")) {
            System.out.println("User is an admin");
            //exit(event);
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminMain.fxml"));
            Parent root = (Parent) loader.load();
            //Parent root = FXMLLoader.load(getClass().getResource("/view/UsersTableView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cryptocurrency Trader");
            primaryStage.show();
        } else {
            System.out.println("User is regular user");
            //exit(event);
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserMain.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cryptocurrency Trader");

            primaryStage.show();
        }
    }

}
