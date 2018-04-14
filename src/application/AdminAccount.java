package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
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
import model.DaoSelectData;

public class AdminAccount extends Application {
	// extends Login 
//implements AdminOperations
	String UserID, Username, Role, LastLoggedIn;
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return UserID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		UserID = userID;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return Role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		Role = role;
	}
	/**
	 * @return the lastLoggedIn
	 */
	public String getLastLoggedIn() {
		return LastLoggedIn;
	}
	/**
	 * @param lastLoggedIn the lastLoggedIn to set
	 */
	public void setLastLoggedIn(String lastLoggedIn) {
		LastLoggedIn = lastLoggedIn;
	}

	@FXML
	private Label heading;
	@FXML
	private TableView<AdminAccount> table;
	@FXML
	private TableColumn<AdminAccount, String> c1Column;
	@FXML
	private TableColumn<AdminAccount, String> c2Column;
	
	ObservableList<AdminAccount> oblist = FXCollections.observableArrayList();
	
	public AdminAccount(String UserID, String Username) {
		this.UserID = UserID;
		this.Username = Username;
		//this.Role = Role;
		//this.LastLoggedIn = LastLoggedIn;
	}
	public AdminAccount() {
		
	}
	/*
	@Override
	public void viewTransactionHistory() {
		// TODO Auto-generated method stub

	}*/

	/*
	public void viewUserAccounts() {
		
		DaoSelectData dao = new DaoSelectData();
		try {
			dao.viewAllUsers();
			ResultSet rs = dao.viewAllUsers();
			while(rs.next()) {	
				//System.out.println(String.valueOf(rs.getInt("UserID"))+ rs.getString("Username") +
				//String.valueOf(rs.getInt("Role")) + String.valueOf(rs.getLong("LastLoggedIn")));
				oblist.add(new AdminAccount(String.valueOf(rs.getInt("UserID")), rs.getString("Username"),
					String.valueOf(rs.getInt("Role")), String.valueOf(rs.getLong("LastLoggedIn"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	/*
	@Override
	public void viewWallets() {
		// TODO Auto-generated method stub

	}
	@Override
	public void viewExchange() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void start(Stage primaryStage) {
		
	}*/
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/UsersTableView.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		c1Column.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		c2Column.setCellValueFactory(new PropertyValueFactory<>("Username"));
		/*
		DaoSelectData dao = new DaoSelectData();
		try {
			dao.viewAllUsers();
			ResultSet rs = dao.viewAllUsers();
			while(rs.next()) {	
				//System.out.println(String.valueOf(rs.getInt("UserID"))+ rs.getString("Username") +
				//String.valueOf(rs.getInt("Role")) + String.valueOf(rs.getLong("LastLoggedIn")));
				oblist.add(new AdminAccount(String.valueOf(rs.getInt("UserID")), rs.getString("Username")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setItems(oblist);
		*/
	}
		
	public static void main(String[] args) {
		launch(args);
	}
}
