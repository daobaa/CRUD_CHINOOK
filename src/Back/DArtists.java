package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("resource")
public class DArtists {
    public static void DelArtist(){
        String url="jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";

        try(Connection connection = DriverManager.getConnection(url, usuari, password)){
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce el id del artista que deseas eliminar:");
            int input = sc.nextInt();

            String sql = "DELETE FROM artist WHERE artist_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, input);
                statement.executeUpdate();
                System.out.println("\nL'artista s'ha esborrat correctament.");
            }
        } catch(SQLException e){
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}
