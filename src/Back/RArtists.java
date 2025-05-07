package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Clase {@code RArtists}.
 * 
 * Permite leer y mostrar todos los artistas registrados en la base de datos
 * {@code chinook_v2}.
 * 
 * <p>
 * Recupera los registros de la tabla {@code artist} y muestra por consola
 * el ID y el nombre de cada artista.
 * </p>
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
public class RArtists {

    /**
     * Ejecuta una consulta para obtener todos los artistas y los muestra por consola.
     * 
     * <p>
     * En caso de producirse un error de conexión o SQL, se informa por consola.
     * </p>
     */
    public static void ReadArtists() {
        String url = "jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, usuari, password)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM artist";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("artist_id");
                String nom = resultSet.getString("name");
                System.out.println("ID: " + id + ", Nom: " + nom);
            }
        } catch (SQLException e) {
            System.out.println("Error en la connexió o la consulta: " + e.getMessage());
        }
    }
}