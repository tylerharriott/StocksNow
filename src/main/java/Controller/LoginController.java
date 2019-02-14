package Controller;


import Model.HashMD5;
import Model.MysqlDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField nameTextfield;
    @FXML
    private PasswordField passwordTextfield;

    private HashMD5 hashMD5 = new HashMD5();
    private MysqlDB mysqlDB = new MysqlDB();


    public void buttonClicked(ActionEvent actionEvent) throws IOException {

        if(hashMD5.verifyMatch(nameTextfield.getText(), passwordTextfield.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TradingFloor.fxml"));
            Parent home_page = (Parent) loader.load();

            TradingFloorController tradingFloorController = loader.getController();
            tradingFloorController.setSessionID(mysqlDB.selectID(nameTextfield.getText()));

            Scene home_page_scene = new Scene(home_page, 500,400);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
        else {
            System.out.println("Wrong password entered: Try again!");
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////

    public void signUpPressed (ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register.fxml"));
        Parent home_page = (Parent) loader.load();

        RegisterController registerController = loader.getController();


        Scene home_page_scene = new Scene(home_page, 500,400);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }




}
