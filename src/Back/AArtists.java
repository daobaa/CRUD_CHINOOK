package Back;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("resource")
/**
 * Clase {@code AArtists}.
 * 
 * Esta clase proporciona un metodo para buscar un artista por nombre y 
 * mostrar sus cinco primeros albumes registrados en la base de datos.
 * <p>
 * El metodo {@link #AlbumsByName()} solicita al usuario el nombre (o parte del nombre)
 * del artista, obtiene su ID, y luego lista hasta cinco albumes asociados,
 * mostrando su ID y titulo.
 * </p>
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
public class AArtists {
    /**
     * Solicita al usuario el nombre de un artista y, si existe,
     * muestra los primeros cinco albumes de dicho artista.
     * <p>
     * - Si el nombre introducido tiene menos de dos caracteres, el metodo termina sin hacer consulta.
     * - Si no se encuentra ningun artista con ese patrón, informa al usuario.
     * - Si lo encuentra, recupera hasta cinco albumes ordenados por su ID.
     * </p>
     */
    public static void AlbumsByName(){
        String url="jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";
        // Conexión y consulta
        try(Connection connection = DriverManager.getConnection(url, usuari, password)){
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce el nombre de un artista:");
            String input = sc.nextLine();
            if(input.length() < 2){
                System.out.println("Has d'introduir almenys 2 caràcters");
                return;
            }

            String ArtistQ = "SELECT artist_id FROM artist WHERE name LIKE ?";

            try(PreparedStatement artistStatement = connection.prepareStatement(ArtistQ)){
                artistStatement.setString(1, "%" + input + "%");
                ResultSet artistResultSet = artistStatement.executeQuery();

                if(artistResultSet.next()){
                    int artistId = artistResultSet.getInt("artist_id");
                    System.out.println("Artista encontrado. ID: " + artistId);

                    String albumQuery = "SELECT album_id, title FROM album WHERE artist_id = ? ORDER BY album_id LIMIT 5";
                    try(PreparedStatement albumStatement = connection.prepareStatement(albumQuery)){
                        albumStatement.setInt(1, artistId);
                        ResultSet albumResultSet = albumStatement.executeQuery();

                        System.out.println("Álbumes de " + input + ":");
                        while(albumResultSet.next()){
                            int albumId = albumResultSet.getInt("album_id");
                            String albumTitle = albumResultSet.getString("title");
                            System.out.println("ID_ALBUM: " + albumId + ", NOM_ALBUM: " + albumTitle + ", NOM_ARTISTA: " + input);
                        }
                    }
                } else{
                    System.out.println("No se encontró un artista con ese nombre.");
                }
            }
        } catch(SQLException e){
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}
