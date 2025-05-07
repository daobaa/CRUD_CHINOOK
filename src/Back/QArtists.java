package Back;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("resource")
/**
 * Clase {@code QArtists}.
 * 
 * Permite realizar una consulta de artistas en la base de datos {@code chinook_v2}
 * filtrando por nombre parcial introducido por el usuario.
 * 
 * <p>
 * Si se encuentra coincidencia, se muestran los IDs y nombres de los artistas
 * cuyo nombre contenga la cadena proporcionada.
 * </p>
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
public class QArtists {

    /**
     * Consulta y muestra artistas cuyo nombre contiene el texto introducido por el usuario.
     * 
     * <p>
     * La búsqueda es sensible al contenido parcial del nombre. Requiere al menos
     * dos caracteres para realizar la consulta.
     * </p>
     * 
     * <p>
     * En caso de error en la conexión o en la consulta SQL, se muestra el error por consola.
     * </p>
     */
    public static void QueryByName() {
        String url = "jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, usuari, password)) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce el nombre de un artista:");
            String input = sc.nextLine();
            if (input.length() < 2) {
                System.out.println("Has d'introduir almenys 2 caràcters");
                return;
            }

            String sql = "SELECT * FROM artist WHERE name LIKE ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + input + "%");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("artist_id");
                    String nom = resultSet.getString("name");
                    System.out.println("ID: " + id + ", Nom: " + nom);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}