package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DoubleCheckController {

    @FXML
    private Button continueBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void CancelAction(ActionEvent event) {
        UserTableViewController.DoubleCheck = 1;
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void ContinueAction(ActionEvent event) {
        UserTableViewController.DoubleCheck = 2;
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    //constructor

    public DoubleCheckController() {

    }
}
