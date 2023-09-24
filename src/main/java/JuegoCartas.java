import java.util.Scanner;

public class JuegoCartas {
    public static void main(String[] args) {
        iniciarPrograma();
    }

    private static void iniciarPrograma() {
        String [][] cartas = crearMatrizCartas();
        inicializarCartas(cartas);

        int opcionIngresada = 1;

        do {
            jugar(cartas);
            opcionIngresada = ingresarOpcion();
        }while (opcionIngresada == 1);
    }

    public static int ingresarOpcion() {
        int opcion = 2;
        while(opcion != 0 && opcion != 1) {
            System.out.println("0-Salir." +
                    "\n1-Seguir jugando.");
            opcion = pedirInput();
        }

        return opcion;
    }

    public static int pedirInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                return scanner.nextInt();
            }catch (Exception e) {
                System.out.println("Por favor ingrese un número.");
            }
        }
    }

    public static void jugar(String [][] cartas) {
        int [] puntajes = new int[2];

        for(int i = 0; i < 2; i++) {
            System.out.println("Jugador " + (i+1) + ": ");

            for(int j = 0; j < 3; j++) {
                String [] cartaObtenida = obtenerCarta(cartas);
                System.out.println("Carta obtenida: " + cartaObtenida[0]);
                puntajes[i] += Integer.parseInt(cartaObtenida[1]);
            }

            System.out.println("Puntaje: " + puntajes[i]);
        }

        mostrarGanador(puntajes);
    }

    private static void mostrarGanador(int [] puntajes) {
        int jugador1 = 20 - puntajes[0];
        int jugador2 = 20 - puntajes[1];

        if((jugador1 < 0 && jugador2 < 0) || jugador1 == jugador2) {
            System.out.println("Empate.");
        } else if((jugador1 < 0 || jugador2 < jugador1) && jugador2 >= 0) {
            System.out.println("Jugador 2 ganó con un puntaje de: " + puntajes[1]);
        } else {
            System.out.println("Jugador 1 ganó con un puntaje de: " + puntajes[0]);
        }
    }

    public static String [][] crearMatrizCartas() {
        return new String[2][12];
    }

    public static void agregarCarta(String [][] cartas, String nombre, String puntaje) {
        for(int i = 0; i < cartas[0].length; i++) {
            if(cartas[0][i] == null) {
                cartas[0][i] = nombre;
                cartas[1][i] = puntaje;
                return;
            }
        }
    }

    public static void inicializarCartas(String [][] cartas) {
        agregarCarta(cartas, "As", "11");
        agregarCarta(cartas, "Jota", "10");
        agregarCarta(cartas, "Reina", "10");
        agregarCarta(cartas, "Rey", "10");
        agregarCarta(cartas, "Dos", "2");
        agregarCarta(cartas, "Tres", "3");
        agregarCarta(cartas, "Cuatro", "4");
        agregarCarta(cartas, "Cinco", "5");
        agregarCarta(cartas, "Seis", "6");
        agregarCarta(cartas, "Siete", "7");
        agregarCarta(cartas, "Ocho", "8");
        agregarCarta(cartas, "Nueve", "9");
    }

    public static String[] obtenerCarta(String [][] cartas) {
        int indice = obtenerNumeroRandom();
        return new String[] {cartas[0][indice], cartas[1][indice]};
    }

    public static int obtenerNumeroRandom() {
        return (int) (Math.random() * 12);
    }
}
