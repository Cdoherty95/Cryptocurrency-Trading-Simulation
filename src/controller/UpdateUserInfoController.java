package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserInfoController implements DAOInterface {

    /*
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    */

    @FXML
    private TextField usernameIn;

    @FXML
    private PasswordField pass1In;

    @FXML
    private PasswordField pass2In;

    @FXML
    private TextField fNameIn;

    @FXML
    private TextField lNameIn;

    @FXML
    private TextField emIn;

    @FXML
    private TextField payNameIn;

    @FXML
    private TextField accountIn;

    @FXML
    private TextField routingIn;

    @FXML
    private Button registerBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label errorLbl;

    private String[] userInfo;

    private boolean checkBankInfo(){
        try{
            Integer.parseInt(accountIn.getText());
        }catch (NumberFormatException e){
            errorLbl.setText("Account Number Must Be An Integer");
            return false;
        }try {
            Integer.parseInt(routingIn.getText());
        }catch (NumberFormatException e) {
            errorLbl.setText("Routing Number Must Be An Integer");
            return false;
        }
        return true;
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        if(checkBankInfo()) {//ints are ints
            daoUsers.updateUserInfo(Integer.parseInt(userInfo[0]), fNameIn.getText(), lNameIn.getText(), emIn.getText(), payNameIn.getText(), Integer.parseInt(accountIn.getText()),
                    Integer.parseInt(routingIn.getText()));
            exit(event);
            new WhichUserMainMenu("user");
        }
    }

    @FXML
    void cancel(ActionEvent event) throws IOException, SQLException {
        exit(event);
        new WhichUserMainMenu("user");
    }

    public void exit(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void initialize() throws SQLException {
        assert usernameIn != null : "fx:id=\"usernameIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert pass1In != null : "fx:id=\"pass1In\" was not injected: check your FXML file 'Register.fxml'.";
        assert pass2In != null : "fx:id=\"pass2In\" was not injected: check your FXML file 'Register.fxml'.";
        assert fNameIn != null : "fx:id=\"fNameIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert lNameIn != null : "fx:id=\"lNameIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert emIn != null : "fx:id=\"emIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert payNameIn != null : "fx:id=\"payNameIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert accountIn != null : "fx:id=\"accountIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert routingIn != null : "fx:id=\"routingIn\" was not injected: check your FXML file 'Register.fxml'.";
        assert registerBtn != null : "fx:id=\"registerBtn\" was not injected: check your FXML file 'Register.fxml'.";
        userInfo = daoUsers.activeUserInfo();
        setUserInfo();
    }

    private void setUserInfo() {

        usernameIn.setText(userInfo[1]);
        fNameIn.setText(userInfo[4]);
        lNameIn.setText(userInfo[5]);
        emIn.setText(userInfo[9]);
        payNameIn.setText(userInfo[6]);
        accountIn.setText(userInfo[7]);
        routingIn.setText(userInfo[8]);

    }

}
