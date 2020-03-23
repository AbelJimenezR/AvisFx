package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection conectar() {
        String myDriver = "java.sql.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/avis";
        Connection conn;
        try {
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "java", "javatest");
            return conn;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            conn = null;
            return conn;
        }
    }
}