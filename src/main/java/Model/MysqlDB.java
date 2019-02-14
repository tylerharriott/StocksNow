package Model;

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
            System.out.println(">>>>Error: " + e);
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
            System.out.println(">>>Error: " + e);
        }

        return passwordn;
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
            System.out.println(">>>Error: " + e);
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
            System.out.println(">>>Error: " + e);
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteData(){

        try{

            String query = "DELETE FROM register WHERE name = 'deon' " ;
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.execute();


        }catch(Exception e){
            System.out.println(">>>Error: " + e);
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////
    public int selectID(String name){
        int id = 0;
        try{

            String query =String.format("SELECT id FROM register WHERE name=('%s')",name);
            rs = st.executeQuery(query);

            while(rs.next()){

                id = Integer.parseInt(rs.getString("id"));

            }


        }catch(Exception e){
            System.out.println(">>>Error: " + e);
        }

        return id;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public String selectName(int id){
        String name ="";
        try{

            String query =String.format("SELECT name FROM register WHERE id=('%s')",id);
            rs = st.executeQuery(query);

            while(rs.next()){

                name = rs.getString("name");

            }


        }catch(Exception e){
            System.out.println(">>>Error: " + e);
        }

        return name;
    }

}
