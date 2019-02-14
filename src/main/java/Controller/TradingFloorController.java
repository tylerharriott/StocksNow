package Controller;

import Model.MysqlDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TradingFloorController implements Initializable {

    public int sessionID;
    private MysqlDB mysqlDB = new MysqlDB();

    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb){


    }


    public void setSessionID(int number){
        sessionID = number;
        welcomeLabel.setText("Welcome " + mysqlDB.selectName(number) + mysqlDB.selectQuantitySQL(number));


    }






}
