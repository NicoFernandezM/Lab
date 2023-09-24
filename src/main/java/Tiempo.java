import java.text.DecimalFormat;
import java.util.Scanner;

public class Tiempo {
    public static void main(String[] args) {
        double[][] datosTemperatura = recopilarDatos();
        menu(datosTemperatura);
    }

    public static void menu(double[][] datosTemperatura) {
        int opcionUsuario;
        imprimirMatriz(datosTemperatura);

        do {
            mostrarMenu();
            opcionUsuario = pedirInput();

            switch (opcionUsuario) {
                case 1 -> mostrarDiaMasCaluroso(datosTemperatura);
                case 2 -> mostrarDiaMasFrio(datosTemperatura);
                case 3 -> mostrarTemperaturaMasBaja(datosTemperatura);
                case 4 -> mostrarTemperaturaMasAlta(datosTemperatura);
                case 5 -> mostrarTemperaturaPromedio(datosTemperatura);
                case 6 -> System.out.println("Programa finalizado.");
            }
        } while (opcionUsuario != 7);
    }

    private static void mostrarDiaMasFrio(double[][] datosTemperatura) {
        double min = 100;
        int dia = 0;

        for(int i = 0; i < datosTemperatura.length; i++) {
            double temperaturaDia = calcularPromedioArreglo(datosTemperatura[i]);

            if(temperaturaDia < min) {
                min = temperaturaDia;
                dia = i + 1;
            }
        }

        System.out.println("El día " + dia + " fue el día más frío con una temperatura promedio de: " + formatearNumero(min));
    }

    private static void mostrarDiaMasCaluroso(double[][] datosTemperatura) {
        double max = 0;
        int dia = 0;

        for(int i = 0; i < datosTemperatura.length; i++) {
            double temperaturaDia = calcularPromedioArreglo(datosTemperatura[i]);

            if(temperaturaDia > max) {
                max = temperaturaDia;
                dia = i + 1;
            }
        }

        System.out.println("El día " + dia + " fue el día más caluroso con una temperatura promedio de: " + formatearNumero(max));
    }

    private static void mostrarTemperaturaPromedio(double [][] datosTemperatura) {
        double promedioTemperatura = 0;

        for(int i = 0; i < datosTemperatura.length; i++) {
            promedioTemperatura += calcularPromedioArreglo(datosTemperatura[i]) / datosTemperatura.length;
        }

        System.out.println("Temperatura promedio: " + formatearNumero(promedioTemperatura));
    }

    public static void mostrarMenu() {
        System.out.println("""
                Menú
                1) Mostrar día más caluroso.
                2) Mostrar día más frío.
                3) Mostrar hora y día de la temperatura más baja.
                4) Mostrar hora y día de la temperatura más alta.
                5) Promedio de la temperatura en la semana.
                6) Salir.
                """);
    }

    public static double[][] recopilarDatos() {
        double[][] datosRecopilados = new double[7][24];

        for (int i = 0; i < datosRecopilados.length; i++) {
            for (int j = 0; j < datosRecopilados[i].length; j++) {
                datosRecopilados[i][j] = generarTemperatura();
            }
        }

        return datosRecopilados;
    }

    public static void mostrarTemperaturaMasAlta(double[][] datosRecopilados) {
        double[] datosTemperaturaMasAlta = encontrarTemperaturaMasAlta(datosRecopilados);
        String[] tipoDeDato = {"Temperatura: ", "Día: ", "Hora: "};

        for (int i = 0; i < datosTemperaturaMasAlta.length; i++) {
            System.out.println(tipoDeDato[i] + formatearNumero(datosTemperaturaMasAlta[i]));
        }
    }

    public static void mostrarTemperaturaMasBaja(double[][] datosRecopilados) {
        double[] datosTemperaturaMasBaja = encontrarTemperaturaMasBaja(datosRecopilados);
        String[] tipoDeDato = {"Temperatura: ", "Día: ", "Hora: "};

        for (int i = 0; i < datosTemperaturaMasBaja.length; i++) {
            System.out.println(tipoDeDato[i] + formatearNumero(datosTemperaturaMasBaja[i]));
        }
    }

    public static double[] encontrarTemperaturaMasAlta(double[][] datosRecopilados) {
        //Intensidad, día y hora.
        double[] datosTemperaturaMasAlta = new double[3];
        double max = 0;

        for (int i = 0; i < datosRecopilados.length; i++) {
            for (int j = 0; j < datosRecopilados[i].length; j++) {
                if (datosRecopilados[i][j] > max) {
                    max = datosRecopilados[i][j];

                    datosTemperaturaMasAlta[0] = max;
                    datosTemperaturaMasAlta[1] = i + 1;
                    datosTemperaturaMasAlta[2] = j;
                }
            }
        }

        return datosTemperaturaMasAlta;
    }

    public static double[] encontrarTemperaturaMasBaja(double[][] datosRecopilados) {
        //Intensidad, día y hora.
        double[] datosTemperaturaMasBaja = new double[3];
        double min = 100;

        for (int i = 0; i < datosRecopilados.length; i++) {
            for (int j = 0; j < datosRecopilados[i].length; j++) {
                if (datosRecopilados[i][j] < min) {
                    min = datosRecopilados[i][j];

                    datosTemperaturaMasBaja[0] = min;
                    datosTemperaturaMasBaja[1] = i + 1;
                    datosTemperaturaMasBaja[2] = j;
                }
            }
        }

        return datosTemperaturaMasBaja;
    }

    public static double calcularPromedioArreglo(double [] arreglo) {
        double promedio = 0;

        for(int i = 0; i < arreglo.length; i++) {
            promedio += (arreglo[i] / arreglo.length);
        }
        return promedio;
    }

    public static int pedirInput() {
        Scanner scanner = new Scanner(System.in);
        int inputUsuario = scanner.nextInt();

        if (inputUsuario < 1 || inputUsuario > 5) {
            System.out.println("Opción inválida.");
            return pedirInput();
        }

        return inputUsuario;
    }

    public static double generarTemperatura() {
        return ((Math.random() * 14) + 10);
    }

    public static String formatearNumero(double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(numero);
    }

    public static void imprimirMatriz(double [][] matriz) {
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                System.out.print(formatearNumero(matriz[i][j]) + "  ");
            }
            System.out.println();
        }
    }
}