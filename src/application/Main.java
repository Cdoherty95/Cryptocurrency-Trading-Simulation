package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.DaoUsers;

import java.sql.SQLException;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws SQLException {
		DaoUsers doa = new DaoUsers();
		doa.programstart();
		System.out.println("Cleared all active users");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/view/AdminMain.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cryptocurrency Trader");
			//primaryStage.getIcons().add(new Image("icon.jpg"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
