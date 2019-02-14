package Controller;

import Model.HashMD5;
import Model.MysqlDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    //Variables
    private MysqlDB mysqlDB = new MysqlDB();
    @FXML
    private TextField registerNameField;
    @FXML
    private PasswordField registerPasswordField;


    //On start, this executes first
    @Override
    public void initialize(URL url, ResourceBundle rb){




    }


    public void regButtonClicked(ActionEvent actionEvent) throws IOException {
        String HashedPassword = registerUser(registerPasswordField.getText());
        mysqlDB.insertData(registerNameField.getText(), HashedPassword);

        //After registering then the user will be moved to TradingFloor Controller


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TradingFloor.fxml"));
        Parent home_page = (Parent) loader.load();

        TradingFloorController tradingFloorController = loader.getController();
        tradingFloorController.setSessionID(mysqlDB.selectID(registerNameField.getText()));

        Scene home_page_scene = new Scene(home_page, 500,400);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


    public String registerUser(String passwordToHash){

        HashMD5 hashMD5 = new HashMD5();
        return hashMD5.generatePassword(passwordToHash);

    }

}
