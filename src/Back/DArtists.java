package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("resource")
/**
 * Clase {@code DArtists}.
 * 
 * Proporciona funcionalidad para eliminar un artista de la base de datos 
 * {@code chinook_v2}, identificándolo mediante su ID.
 * 
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
public class DArtists {

    /**
     * Solicita al usuario el ID de un artista y lo elimina de la base de datos.
     * 
     * <p>
     * Si el ID proporcionado no existe o está siendo referenciado por otras 
     * tablas (como {@code album}), la operación puede fallar debido a restricciones 
     * de integridad referencial.
     * </p>
     */
    public static void DelArtist() {
        String url      = "jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari   = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, usuari, password)) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce el id del artista que deseas eliminar:");
            int input = sc.nextInt();

            String sql = "DELETE FROM artist WHERE artist_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, input);
                statement.executeUpdate();
                System.out.println("\nL'artista s'ha esborrat correctament.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}