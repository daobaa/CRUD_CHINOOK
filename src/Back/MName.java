package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("resource")
/**
 * Clase {@code MName}.
 * 
 * Permite modificar el nombre de un artista en la base de datos {@code chinook_v2}
 * usando su ID como referencia.
 * 
 * <p>
 * Solicita al usuario el ID del artista y el nuevo nombre. Si el ID existe, 
 * actualiza su nombre en la tabla {@code artist}.
 * </p>
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
public class MName {

    /**
     * Modifica el nombre de un artista en la base de datos.
     * 
     * <p>
     * Si el nombre introducido tiene menos de 2 caracteres, no se realiza la modificación.
     * </p>
     * 
     * <p>
     * En caso de error de conexión o SQL, se muestra el error por consola.
     * </p>
     */
    public static void ModifyName() {
        String url = "jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, usuari, password)) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce el id del artista que deseas modificar:");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("¿Por qué nombre quieres modificarlo?");
            String inputNou = sc.nextLine();
            if (inputNou.length() < 2) {
                System.out.println("Has d'introduir almenys 2 caràcters");
                return;
            }

            String sql = "UPDATE artist SET name = ? WHERE artist_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, inputNou);
                statement.setInt(2, input);
                statement.executeUpdate();
                System.out.println("\nL'artista s'ha modificat correctament.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}