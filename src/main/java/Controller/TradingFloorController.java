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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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

        refreshAll();
        myTimer();




    }

    public void myTimer(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask(){
            @Override
            public void run() {


              tableView.getItems().clear();
              mysqlDB.selectStocks(stocksTableAry,sessionID);
              tableView.refresh();


            }
        },0,10*1000);

    }




    public void populateTable(int number){
        sessionID = number;
        welcomeLabel.setText("Welcome " + mysqlDB.selectName(number) + mysqlDB.selectQuantitySQL(number));

        mysqlDB.selectStocks(stocksTableAry,sessionID);
        tableView.setItems(stocksTableAry);

    }

    public void btn_AddSymbol(){

        mysqlDB.insertStocks(
                tickerField.getText(),
                Double.valueOf(pricePaidField.getText()),
                Integer.valueOf(quantityField.getText())
                ,sessionID);


        tickerField.clear();
        pricePaidField.clear();
        quantityField.clear();


        stocksTableAry.removeAll();
        mysqlDB.selectStocks(stocksTableAry,sessionID);
        tableView.refresh();

        System.out.println("--------------------------------");
        for(Stocks tyler : stocksTableAry){
            System.out.println(tyler.getTickerName());
        }
        System.out.println("--------------------------------");



    }

    public void refreshAll(){


        plColumn.setCellFactory(column -> {
            return new TableCell<Stocks, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<Stocks> currentRow = getTableRow();

                    if (!isEmpty()) {

                        if(item >= 0.00)
                            currentRow.setStyle("-fx-background-color:lightgreen");
                        else
                            currentRow.setStyle("-fx-background-color:lightcoral");
                    }
                }
            };
        });




    }


    public void showAddPane(){
        paneBottomRight.toFront();
    }


    public void showRemovePane(){ removePane.toFront(); }





}
