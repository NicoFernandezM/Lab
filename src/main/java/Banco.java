public class Banco {
    public static void main(String[] args) {
        double [][] tablaInteres = generarTablaInteres();
        int [][] datos = generarDatosPrueba();
        probarPrograma(tablaInteres, datos);
    }

    public static void probarPrograma(double [][] tablaIntereses, int [][] datos) {
        for(int i = 0; i < datos.length; i++) {
            int credito = datos[i][0];
            int cuotas = datos[i][1];
            double montoTotal = calcularInteres(credito, cuotas, tablaIntereses);
            mostrarSimulacion(montoTotal, cuotas);
        }
    }

    public static double calcularInteres(int credito, int cuotas, double [][] tabla) {
        int posicionCredito = calcularPosicionCredito(credito);
        int posicionInteres = calcularPosicionInteres(cuotas);

        double montoTotal = credito + (credito * tabla[posicionCredito][posicionInteres]);

        return montoTotal;
    }

    public static int calcularPosicionCredito(int credito) {
        if(credito <= 1000000) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int calcularPosicionInteres(int cuotas) {
        if(cuotas >= 1 && cuotas <= 12) {
            return 0;
        } else if(cuotas >= 13 && cuotas <= 23) {
            return 1;
        } else {
            return 2;
        }
    }

    public static void mostrarSimulacion(double montoTotal, int cuotas) {
        double cuotaMensual = montoTotal / cuotas;

        System.out.println("Monto total a pagar: " + montoTotal +
                ", Cuota mensual: " + cuotaMensual);
    }

    public static double [][] generarTablaInteres() {
        return new double[][] {{0.25, 0.3, 0.35},
                {0.15, 0.2, 0.25}};
    }

    private static int[][] generarDatosPrueba() {
        return new int[][] {{1000000, 10}, {500000, 25}, {2500000, 20}};
    }
}
