package Back;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class QArtists {
    public static void QueryByName(){
        // Detalles de conexión
        String url="jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";
        // Conexión y consulta
        try(Connection connection = DriverManager.getConnection(url, usuari, password)){
            // Crear un objeto Statement para ejecutar consultas
            Statement statement=connection.createStatement();
            // Ejecutar una consulta SELECT
            String sql = "SELECT * FROM artist";
            ResultSet resultSet = statement.executeQuery(sql);
            // Procesar el resultado
            while(resultSet.next()){
                int id = resultSet.getInt("artist_id");
                String nom = resultSet.getString("name");
                System.out.println("ID: " + id + ", Nom: " + nom);
            }
        } catch(SQLException e){
            System.out.println("Error en la connexió o la consulta: " + e.getMessage());
        }
    }
}
