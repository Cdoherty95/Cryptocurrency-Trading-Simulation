package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DaoUsers;

import java.sql.SQLException;

public class Main extends Application {

	static DaoUsers dao = new DaoUsers();

	@Override
	public void start(Stage primaryStage) throws SQLException {
		DaoUsers dao = new DaoUsers();
		//this makes all the Active Users Inactive
		dao.makeAllInactive();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/view/AdminMain.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/view/Exchange.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cryptocurrency Trader");
			//primaryStage.getIcons().add(new Image("icon.jpg"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		//this makes all the Active Users Inactive
		dao.makeAllInactive();
		//UpdateCurrencyPrices updCur = new UpdateCurrencyPrices();
		//updCur.start();
		launch(args);
		//this makes all the Active Users Inactive
		dao.makeAllInactive();

	}
}
