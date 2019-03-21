package Model;

import javafx.collections.ObservableList;

import java.sql.*;

public class MysqlDB {

    private Connection Conn;
    private Statement st;
    private ResultSet rs;



    //Constructor
    public MysqlDB () {


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaDB","root","George@1$");
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
        try{

           String query = String.format("SELECT * FROM stocks WHERE id_person=%s",id);
            rs = st.executeQuery(query);

            while(rs.next()){
                observableList.add(new Stocks(rs.getString("stock_name"), rs.getInt("quantity"),rs.getDouble("price_paid")));

            }

        }catch(Exception e){
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

    public void deleteData(String name){

        try{
            String query = String.format("DELETE FROM stocks WHERE stock_name = '%s' ",name);

            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.execute();


        }catch(Exception e){
            System.out.println(">>>Error (deleteData):" + e);
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
public void updateStocks(int value, String name){

    try{

        String query = String.format("UPDATE stocks SET quantity = quantity + %s WHERE stock_name = '%s'",value,name);
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
