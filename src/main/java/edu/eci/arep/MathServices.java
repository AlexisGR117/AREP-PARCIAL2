package edu.eci.arep;

import java.util.ArrayList;
import java.util.Objects;

import static spark.Spark.*;


/**
 * Clase que proporciona servicios matemáticos, como el cálculo de factores y primos.
 *
 * @author Jefer Alexis Gonzalez Romero
 * @version 1.0 (02/04/2024)
 */
public class MathServices {

    private static final String ERROR = "El número ingresado no es valido";

    /**
     * Método principal de la aplicación.
     *
     * @param args Parámetros de la línea de comandos.
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getPort());
        get("/factors", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            System.out.println("factors: " + value);
            ArrayList<Integer> output = factors(value);
            return jsonResponse("factors", value, output == null ? ERROR : arrayListToString(output));
        });
        get("/primes", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            System.out.println("primes: " + value);
            ArrayList<Integer> output = primes(value);
            return jsonResponse("primes", value, output == null ? ERROR : arrayListToString(output));
        });
    }

    /**
     * Obtiene el puerto para la aplicación.
     *
     * @return El número de puerto.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    /**
     * Genera una respuesta en formato JSON con la operación realizada, el valor de entrada y la salida correspondiente.
     *
     * @param operation El nombre de la operación realizada.
     * @param input     El valor de entrada de la operación.
     * @param output    La salida de la operación.
     * @return Una cadena en formato JSON con la información de la operación.
     */
    public static String jsonResponse(String operation, int input, String output) {
        return "{" +
                " \"operation\": \"" + operation + "\"," +
                " \"input\": " + input + "," +
                " \"output\":  \"" + output + "\"" +
                "}";
    }

    /**
     * Calcula los números primos menores o iguales que un valor dado.
     *
     * @param value El valor de entrada para calcular los números primos.
     * @return Una lista de enteros con los números primos encontrados, o null si el valor de entrada es menor o igual que 1.
     */
    public static ArrayList<Integer> primes(int value) {
        if (value <= 1) return null;
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= value; i++) {
            if (Objects.requireNonNull(factors(i)).size() == 2) primes.add(i);
        }
        return primes;
    }

    /**
     * Calcula los factores de un número dado.
     *
     * @param value El número para calcular sus factores.
     * @return Una lista de enteros con los factores encontrados, o null si el valor de entrada es menor o igual que 0.
     */
    public static ArrayList<Integer> factors(int value) {
        if (value <= 0) return null;
        ArrayList<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= value; i++) {
            if (value % i == 0) factors.add(i);
        }
        return factors;
    }

    /**
     * Convierte una lista de enteros en una cadena de texto, separando los números por comas y sin la última coma.
     *
     * @param array Una lista de enteros a convertir en una cadena de texto.
     * @return Una cadena de texto con los números separados por comas y sin la última coma.
     */
    public static String arrayListToString(ArrayList<Integer> array) {
        StringBuilder stringArray = new StringBuilder();
        for (int number : array) {
            stringArray.append(number).append(", ");
        }
        int stringLength = stringArray.length();
        return stringArray.delete(stringLength - 2, stringLength).toString();
    }
}
