package Back;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QArtists {
    public static void QueryByName(){
        // Detalles de conexión
        String url="jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";
        // Conexión y consulta
        try(Connection connection = DriverManager.getConnection(url, usuari, password)){
            Scanner sc2 = new Scanner(System.in);

            System.out.println("Introduce el nombre de un artista:");
            String input = sc2.nextLine();
            if(input.length() < 2){
                System.out.println("Has d'introduir almenys 2 caràcters");
            } else{
                String sql = "SELECT * FROM artist WHERE name LIKE ?";

                try(PreparedStatement statement = connection.prepareStatement(sql)){
                    statement.setString(1, "%" + input + "%");
                    ResultSet resultSet = statement.executeQuery();

                    while(resultSet.next()){
                        int id = resultSet.getInt("artist_id");
                        String nom = resultSet.getString("name");
                        System.out.println("ID: " + id + ", Nom: " + nom);
                    }
                }
            }
            sc2.close();
        } catch(SQLException e){
            System.out.println("Error en la connexió o la consulta: " + e.getMessage());
        }
    }
}
