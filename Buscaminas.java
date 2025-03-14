import java.util.Random;
import java.util.Scanner;

public class Buscaminas {
    private static String[][] tablero;
    private String[][] minas;
    private int x,y;
    
    /*Creamos una clase que genere el tablero que se va a mostrar de 5 filas y 5 columnas */
    public Buscaminas() {
        tablero = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = "*";
            }
        }
    }
    /*Creamos una clase para que imprima el tablero con dos bucles for con la longitud del tablero */
    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*Creamos una clase para generar minas usando random */
    public void generarMinas() {
        /*Generamos una nueva matriz de 5*5 donde se guardaran las minas */
        minas = new String[5][5];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(5);
            int y = random.nextInt(5);
            /*Usando el random hacemos que entre un rango del 0 al 4 (rango de 5 valores) 
             con un if vemos si esas cordenadas estan vacias y si estan vacias agregamos un "B" 
             en esa posicion que simula la bomba*/
            if (minas[x][y] == null) {
                minas[x][y] = "B";
            }/*Si en esa posicion ya hay un caracter resta una del valor i para que se repita el bucle una vez mas 
            para recuperar el intento perdido */ 
            else {
                i--;
            }
        }
        /*Con estos for lo que hace es que vaya buscando cordenada por cordenada y viendo si no hay una mina ahi es decir una "B"
         si no hay una "B" agrega un "*" en el hueco que hay para asi rellenar todos los huecos y si hay un "B" pasa a la siguiente
         cordenada*/
        for (int i = 0; i < minas.length; i++) {
            for (int j = 0; j < minas[i].length; j++) {
                if (minas[i][j] == null) {
                    minas[i][j] = "*";
                }
            }
        }
    }
    /*Creamos una  clase para que imprima las minas*/
    public void imprimirMinas(){
        for (int i = 0; i < minas.length; i++) {
            for (int j = 0; j < minas[i].length; j++) {
                System.out.print(minas[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*Creamos una clase para que carge el usuario los valores de las cordenadas */
    public void Cargajugador(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la coordenada x: ");
        x = scanner.nextInt();
        System.out.println("Ingrese la coordenada y: ");
        y = scanner.nextInt();
    }
    /*Y por ultimo creamos la clase juego donde se llamara a todas las clases creadas anteriormente */
    public void Juego(){
        while (true) {
        Scanner scanner = new Scanner(System.in);
        /*Comprobamos que  las cordenadas introducidas estan en un rango de entre 0 y 4 incluidos los dos */
        if((x >= 0 && y >= 0)&& ( x <= 4 && y <= 4 )) {
            /*Comprobamos si en la posicion indicada hay una mina es decir una "B" y en caso de que lo haya 
             que envie un mensaje de que has perdido, te imprime las posiciones de las mionas y te pide si 
             quieres volver a jugar o no 
             */
            if (minas[x][y].equals("B")) {
                System.out.println("Perdiste");
                System.out.println("Tablero de minas: ");
                imprimirMinas();
                System.out.println("¿Quieres volver a jugar? (s/n): ");
                String respuesta = scanner.next();
                /*En caso de volver a jugar es decir pulsar "s" se crea un nuevo juego y se empieza desde 
                 * el principio
                */
                if (respuesta.equalsIgnoreCase("s")) {
                    Buscaminas nuevoJuego = new Buscaminas();
                    nuevoJuego.generarMinas();
                    nuevoJuego.imprimirTablero();
                    nuevoJuego.Cargajugador();
                    nuevoJuego.Juego();
                } else {/*Si pulsas n o cualquier otra texla te imprime un mensaje y te hace un break para terminar el codigo */
                    System.out.println("Gracias por jugar!");
                    break;
                }
                /*En caso de que en las cordenadas introducidas no hubiese una mina "B"
                  remplaza el caracter "*" por una "A" tanto en el tablero que se imprime como el de las minas
                  de que no hay nada y vuelve a imprimirte el tablero con el cambio realizado 
                  y vuelve a preguntarte por las cordenadas 
                  */
            }else{
                tablero[x][y] = "A";
                minas[x][y] = "A";
                imprimirTablero();
                Cargajugador();
                }
                /*En caso de meter una cordenada fuera de rango es decir mayor de 4 y menor que 0
                 * te imprime que es incorrecta y te vuelve a pedir que carges las Cordenadas
                 */
            }else{
            System.out.println("Coordenadas incorrectas");
            Cargajugador();
        }
    }
    }

    public static void main(String[] args) {
        Buscaminas buscaminas = new Buscaminas();
        buscaminas.generarMinas();
        buscaminas.imprimirTablero();
        buscaminas.Cargajugador();
        buscaminas.Juego();
    }
}

