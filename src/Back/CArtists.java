package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@SuppressWarnings("resource")
public class CArtists {
    public static void AddArtist(){
        // Detalles de conexión
        String url="jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";
        // Conexión y consulta
        try(Connection connection = DriverManager.getConnection(url, usuari, password)){
            Scanner sc = new Scanner(System.in);

            System.out.println("Añade el nombre de un artista:");
            String input = sc.nextLine();
            if(input.length() < 2){
                System.out.println("Has d'introduir almenys 2 caràcters");
                return;
            }

            int nextId = 1;
            String getMaxIdSQL = "SELECT MAX(artist_id) FROM artist";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(getMaxIdSQL)) {
                if (rs.next()) {
                    nextId = rs.getInt(1) + 1;
                }
            }

            String sql = "INSERT INTO artist(artist_id, name) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, nextId);
                statement.setString(2, input);
                statement.executeUpdate();
                System.out.println("\nArtista añadido correctamente.");
            }
        } catch(SQLException e){
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}
