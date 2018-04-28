package application;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public interface MenuOperations {

	void viewTransactionHistory(ActionEvent event) throws IOException, SQLException;
	void viewUsers(ActionEvent event) throws IOException;
	void exit(ActionEvent event);
	void logout(ActionEvent event) throws IOException;
}
