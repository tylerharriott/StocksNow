import java.sql.*;

public class TestMe {
    public static void main(String[] args) {
        try{


             Connection Conn;
             Statement st;
            ResultSet rs;
            String passwordn = "";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Conn = DriverManager.getConnection("jdbc:mysql://192.168.1.18/DBubuntu","tyler","password");
            st = Conn.createStatement();





                    String query =String.format("SELECT * FROM testDB");
                    rs = st.executeQuery(query);

            while(rs.next()){

                passwordn = rs.getString("id");
                System.out.println(passwordn);
            }

             query = String.format("INSERT INTO testDB (id) VALUES (1)");
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.execute();










        }catch(Exception e){
            System.out.println(">>>>Error: " + e);
        }
    }
}
