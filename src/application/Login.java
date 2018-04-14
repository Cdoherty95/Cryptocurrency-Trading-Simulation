package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DaoUsers;

public class Login extends Exchange {
	
	public int ID;
	@FXML
	private Label Welcome;
	@FXML
	private Label errorLable;
	@FXML
	private TextField Username;
	@FXML
	public TextField Password;	
		
	public void login(ActionEvent event) throws SQLException, IOException {
		
		if (!Username.getText().isEmpty() && !Password.getText().isEmpty()) {
		DaoUsers dao = new DaoUsers();
			if(dao.loginPasswordMatching(Username.getText(), Password.getText())) {
				//Move on to get user information
				String[] userInfo = dao.activeUserInfo();
				if(userInfo[2]=="admin") {
					System.out.println("User is an admin");
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/AdminMain.fxml"));
					Scene scene = new Scene(root,400,400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				}else {
					System.out.println("User is regular user");
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/UserMain.fxml"));
					Scene scene = new Scene(root,400,400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				}
			}else { // if the Username or password is incorrect
				System.out.println("Bad Username or password");
				errorLable.setText("Username Or Password is incorrect");
			}
		}else {
			errorLable.setText("Please input Username and Password");
		}
	}
}
