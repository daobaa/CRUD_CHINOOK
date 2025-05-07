package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@SuppressWarnings("resource")
/**
 * Clase {@code CArtists}.
 *
 * Proporciona funcionalidad para añadir un nuevo artista a la base de datos
 * Chinook. La clase calcula manualmente el siguiente ID disponible para el
 * nuevo artista, en lugar de depender de una secuencia autoincremental.
 * 
 * <p>
 * Esta clase es útil en entornos donde no se permite modificar la estructura
 * de la base de datos, como actividades académicas.
 * </p>
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
public class CArtists {

    /**
     * Solicita al usuario el nombre de un artista y lo añade a la base de datos.
     * 
     * <p>
     * – Calcula el siguiente ID disponible manualmente mediante una consulta
     * {@code SELECT MAX(artist_id)}.  
     * – Si el nombre tiene menos de dos caracteres, se cancela la operación.  
     * – Muestra un mensaje de éxito si la inserción se realiza correctamente.
     * </p>
     */
    public static void AddArtist() {
        String url      = "jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari   = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, usuari, password)) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Añade el nombre de un artista:");
            String input = sc.nextLine();
            if (input.length() < 2) {
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
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, nextId);
                statement.setString(2, input);
                statement.executeUpdate();
                System.out.println("\nArtista añadido correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}