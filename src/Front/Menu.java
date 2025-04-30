package Front;

import java.util.Scanner;
import Back.Artists;

public class Menu{
    public void mainMenu(){
        Scanner sc = new Scanner(System.in);
        Boolean keepGoing = true;
        while(keepGoing){
            System.out.println("Menu Principal");
            System.out.println("1 - Consultar todos los artistas");
            System.out.println("2 - Consultar artistas por su nombre");
            System.out.println("3 - Consultar los 5 primeros albumes por el nombre del artista");
            System.out.println("4 - Añadir un artista");
            System.out.println("5 - Modificar el nombre de un artista");
            System.out.println("6 - Borrar un artista");
            System.out.println("7 - Salir");

            System.out.println("\nSelecciona una opción: ");
            int chosenOption = sc.nextInt();
            System.out.println("");
            switch(chosenOption){
                case 1:
                    Artists.ReadArtists();
                case 2:
                    //QueryByName()
                case 3:
                    //AlbumsByName()
                case 4:
                    //AddArtist()
                case 5:
                    //ModifyName()
                case 6:
                    //DelArtist()
                case 7:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Input no valido");
            }
        }
        sc.close();
    }
}
