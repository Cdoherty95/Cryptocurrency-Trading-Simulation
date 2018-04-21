package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrencyHistoryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button exitBtn;

	@FXML
	private Button mainMenuBtn;

	@FXML
	private TableView<?> btcTableView;

	@FXML
	private TableColumn<?, ?> btcTimeCol;

	@FXML
	private TableColumn<?, ?> btcUsdCol;

	@FXML
	private TableColumn<?, ?> btcEthCol;

	@FXML
	private TableView<?> ethTableView;

	@FXML
	private TableColumn<?, ?> ethTimeCol;

	@FXML
	private TableColumn<?, ?> ethUsdCol;

	@FXML
	private TableColumn<?, ?> ethBtcCol;

	@FXML
	void exit(ActionEvent event) {

	}

	@FXML
	void mainMenu(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert mainMenuBtn != null : "fx:id=\"mainMenuBtn\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert btcTableView != null : "fx:id=\"btcTableView\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert btcTimeCol != null : "fx:id=\"btcTimeCol\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert btcUsdCol != null : "fx:id=\"btcUsdCol\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert btcEthCol != null : "fx:id=\"btcEthCol\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert ethTableView != null : "fx:id=\"ethTableView\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert ethTimeCol != null : "fx:id=\"ethTimeCol\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert ethUsdCol != null : "fx:id=\"ethUsdCol\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";
		assert ethBtcCol != null : "fx:id=\"ethBtcCol\" was not injected: check your FXML file 'CurrencyHistroy.fxml'.";

	}
}
