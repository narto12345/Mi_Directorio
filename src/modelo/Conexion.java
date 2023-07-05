package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static final String URL = "jdbc:mysql://localhost:3306/miDirectorio?autoReconnet=true&useSSL=false";
    public static final String usuario = "root";
    public static final String contraseña = "12345";

    public Connection getConnection() {
        Connection conexion = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, usuario, contraseña);
            System.out.println("Conexion exitosa");

        } catch (Exception ex) {
            System.err.println("Error, " + ex);
        }

        return conexion;
    }

}
