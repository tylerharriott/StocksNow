package Controller;

import Model.MysqlDB;
import Model.Stocks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class TradingFloorController implements Initializable {

    private ObservableList<Stocks> stocksTableAry = FXCollections.observableArrayList();

    //FXML
    @FXML
    private TableView<Stocks> tableView;
    @FXML
    private TableColumn<Stocks,String> nameColumn;
    @FXML
    private TableColumn<Stocks,Double> plColumn, paidColumn;
    @FXML
    private TableColumn<Stocks, BigDecimal> currentPriceColumn;
    @FXML
    private TableColumn<Stocks, Integer> quantityColumn;
    @FXML
    private TextField tickerField,pricePaidField,quantityField;
    @FXML
    private Pane removePane,paneBottomRight;



    public int sessionID;
    private MysqlDB mysqlDB = new MysqlDB();

    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        //set up the columns and the table
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tickerName"));
        plColumn.setCellValueFactory(new PropertyValueFactory<>("PLValue"));
        paidColumn.setCellValueFactory(new PropertyValueFactory<>("pricePaid"));
        currentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("currentPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        tableView.setItems(stocksTableAry);


    }


    public void setSessionID(int number){
        sessionID = number;
        welcomeLabel.setText("Welcome " + mysqlDB.selectName(number) + mysqlDB.selectQuantitySQL(number));


    }

    public void btn_AddSymbol() throws IOException {

        String name = tickerField.getText();
        double pricePaid = Double.valueOf(pricePaidField.getText());
        int quantity = Integer.valueOf(quantityField.getText());

        stocksTableAry.add( new Stocks(name,quantity,pricePaid));

        tickerField.clear();
        pricePaidField.clear();
        quantityField.clear();

    }

    public void showAddPane(){

        paneBottomRight.toFront();



    }

    public void showRemovePane(){

        removePane.toFront();



    }





}
