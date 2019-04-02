package Controller;


import Model.HashMD5;
import Model.MysqlDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class LoginController {

    @FXML
    private TextField nameTextfield;
    @FXML
    private PasswordField passwordTextfield;
    @FXML
    private Label wrongPassLabel;

    private HashMD5 hashMD5 = new HashMD5();
    private MysqlDB mysqlDB = new MysqlDB();


    public void buttonClicked(ActionEvent actionEvent) throws IOException {

        System.out.println("hello world");
        if(hashMD5.verifyMatch(nameTextfield.getText(), passwordTextfield.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TradingFloor.fxml"));
            Parent home_page = (Parent) loader.load();

            TradingFloorController tradingFloorController = loader.getController();
            tradingFloorController.populateTable(mysqlDB.selectID(nameTextfield.getText()));
            System.out.println("ID: " + mysqlDB.selectID(nameTextfield.getText()));

            Scene home_page_scene = new Scene(home_page, 1000,600);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            app_stage.setX((primScreenBounds.getWidth() - app_stage.getWidth()) / 2);
            app_stage.setY((primScreenBounds.getHeight() - app_stage.getHeight()) / 4);
        }
        else {

            nameTextfield.clear();
            passwordTextfield.clear();
            wrongPassLabel.setVisible(true);
            timerForlabel();
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


    public void timerForlabel() {
        TimerTask task = new TimerTask() {
            public void run() {
               wrongPassLabel.setVisible(false);
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 8000L;
        timer.schedule(task, delay);
    }

}
