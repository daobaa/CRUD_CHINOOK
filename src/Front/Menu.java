/**
 * Clase {@code Menu}.
 * 
 * Esta clase proporciona la interfaz principal de usuario para la gestión de los artistas.
 * Muestra un menú con opciones para que el usuario realize diversas acciones como consultar,
 * añadir, modificar y borrar artistas.
 * 
 * @author Diego Andrés Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
package Front;

import java.util.Scanner;
import Back.RArtists;
import Back.QArtists;
import Back.AArtists;
import Back.CArtists;
import Back.MName;
import Back.DArtists;

public class Menu{
    /**
     * Muestra el menú principal al usuario y maneja la entrada para realizar acciones
     * relacionadas con los artistas. Las opciones del menú incluyen:
     * <ul>
     *      <li>Consultar todos los artistas</li>
     *      <li>Consultar artistas por su nombre</li>
     *      <li>Consultar los 5 primeros albumes por el nombre del artitsta</li>
     *      <li>Añadir un nuevo artista</li>
     *      <li>Modificar el nombre de un artista</li>
     *      <li>Borrar un artista</li>
     *      <li>Salir del programa</li>
     * </ul>
     * El metodo utiliza un {@link Scanner} para capturar el input del usuario y manejar la
     * opción seleccionada llamando a los metodos correspondientes de las clases del paquete
     * {@code Back}.
     * 
     * @throws java.util.InputMismatchException Si el usuario introduce un valor no entero.
     */
    public void mainMenu(){
        Scanner sc = new Scanner(System.in);
        try{
            Boolean keepGoing = true;
            while(keepGoing){
                // Mostrar las opciones del menú
                System.out.println("\nMenu Principal");
                System.out.println("1 - Consultar todos los artistas");
                System.out.println("2 - Consultar artistas por su nombre");
                System.out.println("3 - Consultar los 5 primeros albumes por el nombre del artista");
                System.out.println("4 - Añadir un artista");
                System.out.println("5 - Modificar el nombre de un artista");
                System.out.println("6 - Borrar un artista");
                System.out.println("7 - Salir");
    
                // Solicitar input del usuario
                System.out.println("\nSelecciona una opción: ");
                int chosenOption = sc.nextInt();
                System.out.println("");
                // Declaración del switch para manejar el input
                switch(chosenOption){
                    case 1:
                        RArtists.ReadArtists();
                        break;
                    case 2:
                        QArtists.QueryByName();
                        break;
                    case 3:
                        AArtists.AlbumsByName();
                        break;
                    case 4:
                        CArtists.AddArtist();
                        break;
                    case 5:
                        MName.ModifyName();
                        break;
                    case 6:
                        DArtists.DelArtist();
                        break;
                    case 7:
                        System.out.println("Saliendo del programa.");
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Input no valido\n");
                        break;
                }
            }
        } finally{
            sc.close(); // Asegurar que el Scanner se cierre después de usarlo
        }
    }
}
