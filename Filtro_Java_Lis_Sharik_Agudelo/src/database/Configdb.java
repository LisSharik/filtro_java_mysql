package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Configdb {
    static Connection objConnection = null;
    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://uq8jvtgiz81jrnkp:B9QKcKaEzsmCHrsE10uj@brwtmafm4vegak9pzod5-mysql.services.clever-cloud.com:3306/brwtmafm4vegak9pzod5";
            String user = "uq8jvtgiz81jrnkp";
            String password = "B9QKcKaEzsmCHrsE10uj";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Perfect Connection");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed");

        }catch (SQLException e){
            System.out.println("Error >> Could not stablish a connection white the db");
            System.out.println(e.getMessage());
        }

        return objConnection;
    }

    public  static void closeConnection(){
        try{
            if(objConnection != null) objConnection.close();
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
}
