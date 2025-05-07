package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("resource")
public class MName {
    public static void ModifyName(){
        String url="jdbc:postgresql://localhost:5432/chinook_v2";
        String usuari = "postgres";
        String password = "postgres";

        try(Connection connection = DriverManager.getConnection(url, usuari, password)){
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce el id del artista que deseas modificar:");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("Por qué nombre quieres modificarlo?");
            String inputNou = sc.nextLine();
            if(inputNou.length() < 2){
                System.out.println("Has d'introduir almenys 2 caràcters");
                return;
            }

            String sql = "UPDATE artist SET name = ? WHERE artist_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, inputNou);
                statement.setInt(2, input);
                statement.executeUpdate();
                System.out.println("\nNombre del artista modificado correctamente.");
            }
        } catch(SQLException e){
            System.out.println("Error en la connexió o la consulta:");
            e.printStackTrace();
        }
    }
}
