package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MysqlDB {

    private ObservableList<Stocks> stocksTemp = FXCollections.observableArrayList();
    private Connection Conn;
    private Statement st;
    private ResultSet rs;



    //Constructor
    public MysqlDB () {


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","George@1$");
            st = Conn.createStatement();



        }catch(Exception e){
            System.out.println(">>>>Error (Constructor): " + e);
        }


    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    public String selectPasswordSQL(String name){
    String passwordn ="";
        try{

            String query =String.format("SELECT password FROM register WHERE name=('%s')",name);
            rs = st.executeQuery(query);

            while(rs.next()){

                passwordn = rs.getString("password");

            }


        }catch(Exception e){
            System.out.println(">>>Error (selectPasswordSQL): " + e);
        }

        return passwordn;
    }
////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectStocks(ObservableList<Stocks> observableList,int id){
            try {

                String query = String.format("select stock_name, sum(quantity),sum(total) from stocks WHERE id_person= %s group by stock_name ", id);
                rs = st.executeQuery(query);

                while (rs.next()) {

                            observableList.add(new Stocks(rs.getString("stock_name"), rs.getInt("sum(quantity)"), rs.getDouble("sum(total)")));

                    }


            } catch (Exception e) {
                System.out.println(">>>Error (selectStocks): " + e);
            }



    }
////////////////////////////////////////////////////////////////////////////////////////////////

    public String selectQuantitySQL(int id){
        String quantity ="";
        try{

            String query =String.format("SELECT `quantity` FROM stocks WHERE `id_person`=%s",id);
            rs = st.executeQuery(query);

            while(rs.next()){

                quantity = rs.getString("quantity");

            }


        }catch(Exception e){
            System.out.println(">>>Error (selectQuantitySQL): " + e);
        }

        return quantity;
    }


////////////////////////////////////////////////////////////////////////////////////////////////

    public void insertData(String name, String password){

        try{

            String query = String.format("INSERT INTO register (name, password) VALUES ('%s','%s')",name,password);
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.execute();


        }catch(Exception e){
            System.out.println(">>>Error (insertData): " + e);
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteData(String name, int quantity,int id){

        try{

            String query4 = String.format("UPDATE stocks set quantity = quantity - %s WHERE stock_name = '%s' AND id_person = %s LIMIT 1",quantity,name,id);
            System.out.println(query4);
            PreparedStatement preparedStatement4 = Conn.prepareStatement(query4);
            preparedStatement4.execute();



            String query2 = "DELETE FROM stocks WHERE quantity <= 0";
            System.out.println(query2);
            PreparedStatement preparedStatement2 = Conn.prepareStatement(query2);
            preparedStatement2.execute();


            String query = String.format("UPDATE stocks SET total = price_paid * quantity WHERE stock_name = '%s' AND id_person = %s LIMIT 1",name,id);
            System.out.println(query);
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.execute();






        }catch(Exception e){
            System.out.println(">>>Error (insertStocks): " + e);
        }


    }
////////////////////////////////////////////////////////////////////////////////////////////////
    public int selectID(String name){
        int id = 999;
        try{

            String query =String.format("SELECT id FROM register WHERE name=('%s')",name);
            rs = st.executeQuery(query);

            while(rs.next()){

                id = Integer.parseInt(rs.getString("id"));

            }


        }catch(Exception e){
            System.out.println(">>>Error (selectID): " + e);
        }

        return id;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectName(String name){

        try{

            String query =String.format("SELECT stock_name FROM stocks WHERE stock_name='%s'",name);
            rs = st.executeQuery(query);

            while(rs.next()) {

                name = rs.getString("name");

            }


        }catch(Exception e){
            System.out.println(">>>Error (selectName): " + e);
        }


    }

//////////////////////////////////////////////////////////////////////////////////////////////////



    public boolean checkForStockName(String name){

        boolean ret =false;
        String value = "";

        try{

            String query = String.format("SELECT IFNULL( (SELECT stock_name FROM stocks WHERE stock_name = '%s' ) ,'not found')",name);
            rs = st.executeQuery(query);
            while(rs.next()) {

                value = rs.getString(1);

            }



        }catch(Exception e){
            System.out.println(">>>Error (check Failed): " + e);
        }

        if( !value.equals("not found") ){
            ret = true;
        }



        return ret;
    }




/////////////////////////////////////////////////////////////////////////////////////////////////
    public void insertStocks(String symbol, double price, int quantity, int ID){

        try{
            String query = String.format("INSERT INTO stocks (stock_name,price_paid,quantity,id_person,total) VALUES ('%s',%s,%s,%s,quantity * price_paid) ",symbol,price,quantity,ID);
            System.out.println(query);
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.execute();

         //   SELECT * FROM stocks WHERE id_person=2;

        }catch(Exception e){
            System.out.println(">>>Error (insertStocks): " + e);
        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////
public void updateStocks(int value, String name, double price){
        double totalResult = value * price;

    try{

        String query = String.format("UPDATE stocks SET total = total + %s, quantity = quantity + %s, price_paid = total / quantity WHERE stock_name = '%s'",totalResult,value,name);
        System.out.println(query);
        PreparedStatement preparedStatement = Conn.prepareStatement(query);
        preparedStatement.execute();


    }catch(Exception e){
        System.out.println(">>>Error (updateStocks): " + e);
    }

}


    public Connection getConn() {
        return Conn;
    }

    public void setConn(Connection conn) {
        Conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }



}
