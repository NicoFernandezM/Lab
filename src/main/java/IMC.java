import java.text.DecimalFormat;

public class IMC {
    public static void main(String[] args) {
        inicializar();
    }

    private static void inicializar() {
        double [][] estaturas = new double[4][10];
        double [][] pesos = new double[4][10];
        double [][] tablaIMC = new double[4][10];

        llenarMatriz(estaturas, 1.5, 0.15);
        llenarMatriz(pesos, 35, 45);

        llenarTablaIMC(tablaIMC, estaturas, pesos);
        mostrarMatriz(tablaIMC);

        mostrarDetallesIMC(tablaIMC, estaturas, pesos);
    }

    public static void mostrarDetallesIMC(double [][] tablaIMC, double [][] estaturas, double [][] pesos) {
        String [] categorias = new String[] {"Bajo peso", "Normal", "Sobrepeso", "Obeso"};

        for(int i = 0; i < tablaIMC.length; i++) {
            System.out.println("Curso " + (i + 1) + ": ");
            int [] contadorPorCategoria = contarPorCategoria(tablaIMC[i]);

            for(int j = 0; j < categorias.length; j++) {
                System.out.println(categorias[j] + ": " + contadorPorCategoria[j]);
            }

            mostrarDetallesPesoEstatura(estaturas[i], pesos[i]);
        }
    }

    public static void mostrarDetallesPesoEstatura(double [] estaturas, double [] pesos) {
        System.out.println("Estatura promedio: " + formatearNumero(calcularPromedioArreglo(estaturas)));
        System.out.println("Peso promedio: " + formatearNumero(calcularPromedioArreglo(pesos)));
    }

    public static int [] contarPorCategoria(double [] curso) {
        int [] contadorPorCategoria = new int[4];

        for(int i = 0; i < curso.length; i++) {
            contadorPorCategoria[getIndiceCategoria(curso[i])]++;
        }

        return contadorPorCategoria;
    }

    public static int getIndiceCategoria(double imc) {
        if(imc < 18.5) {
            return 0;
        } else if(imc >= 18.5 && imc < 25) {
            return 1;
        } else if(imc >= 25 && imc < 30) {
            return 2;
        } else {
            return 3;
        }
    }

    public static double calcularPromedioArreglo(double [] arreglo) {
        double promedio = 0;
        for(int i = 0; i < arreglo.length; i++) {
            promedio += arreglo[i]/arreglo.length;
        }

        return promedio;
    }

    public static void llenarTablaIMC(double [][] tablaIMC, double [][] estaturas, double [][] pesos) {
        for(int i = 0; i < estaturas.length; i++) {
            for(int j = 0; j < estaturas[0].length; j++) {
                tablaIMC[i][j] = calcularIMC(estaturas[i][j], pesos[i][j]);
            }
        }
    }

    public static double calcularIMC(double estatura, double peso) {
        return (peso / (Math.pow(estatura, 2)));
    }

    public static void llenarMatriz(double [][] matriz, double minimo, double intervalo) {
        for (int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = generarNumeroAleatorio(minimo, intervalo);
            }
        }
    }

    public static double generarNumeroAleatorio(double minimo, double intervalo) {
        return ((Math.random() * intervalo) + minimo);
    }

    public static void mostrarMatriz(double [][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                System.out.print(formatearNumero(matriz[i][j]) + "   ");
            }
            System.out.println();
        }
    }

    /*
    * Este método sirve para formatear un número decimal. Básicamente recibe un double y devuelve un
    * String que representa al número formateado. Es importante usar este método solo cuando se quiere
    * mostrar algo, ya que como dije anteriormente, devuelve un String, no un dato de tipo númerico.*/

    public static String formatearNumero(double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(numero);
    }
}
