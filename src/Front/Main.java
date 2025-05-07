/**
 * Clase {@code Main}.
 * 
 * Esta clase contiene el metodo principal {@code main} que se ejecuta al
 * iniciar el programa. Crea una instancia de la clase {@link Menu} y llama al 
 * metodo {@link Menu#mainMenu()} para mostrar el menu principal al usuario.
 * 
 * @author Diego Andres Olivera Abarca
 * @version 1.0
 * @since 2025-05-07
 */
package Front;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mainMenu();
    }
}
