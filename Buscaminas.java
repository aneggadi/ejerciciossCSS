import java.util.Random;
import java.util.Scanner;

public class Buscaminas {
    private static String[][] tablero;
    private String[][] minas;
    private boolean finJuego;
    private int x,y;

    public Buscaminas() {
        tablero = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = "*";
            }
        }
    }
    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void generarMinas() {
        minas = new String[5][5];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(5);
            int y = random.nextInt(5);
            if (minas[x][y] == null) {
                minas[x][y] = "M";
            } else {
                i--;
            }
        }
        for (int i = 0; i < minas.length; i++) {
            for (int j = 0; j < minas[i].length; j++) {
                if (minas[i][j] == null) {
                    minas[i][j] = "*";
                }
            }
        }
    }
    public void imprimirMinas(){
        for (int i = 0; i < minas.length; i++) {
            for (int j = 0; j < minas[i].length; j++) {
                System.out.print(minas[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void Cargajugador(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la coordenada x: ");
        x = scanner.nextInt();
        System.out.println("Ingrese la coordenada y: ");
        y = scanner.nextInt();
    }
    public void Juego(){
        while (true) {
        Scanner scanner = new Scanner(System.in);
        if((x >= 0 && y >= 0)&& ( x <= 4 && y <= 4 )) {
            if (minas[x][y].equals("M")) {
                System.out.println("Perdiste");
                System.out.println("Tablero de minas: ");
                imprimirMinas();
                System.out.println("¿Quieres volver a jugar? (s/n): ");
                String respuesta = scanner.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    Buscaminas nuevoJuego = new Buscaminas();
                    nuevoJuego.generarMinas();
                    nuevoJuego.imprimirTablero();
                    nuevoJuego.Cargajugador();
                    nuevoJuego.Juego();
                } else {
                    System.out.println("Gracias por jugar!");
                    finJuego = false;
                    break;
                }
            }else{
                tablero[x][y] = " ";
                minas[x][y] = " ";
                imprimirTablero();
                    Cargajugador();
                }
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
