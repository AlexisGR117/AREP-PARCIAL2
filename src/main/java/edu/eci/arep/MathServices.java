package edu.eci.arep;

import java.util.ArrayList;
import java.util.Objects;

import static spark.Spark.*;
import static spark.Spark.get;

public class MathServices {

    private static final String ERROR = "El número ingresado no es valido";

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getPort());
        get("/factors", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            ArrayList<Integer> output = factors(value);
            return jsonResponse("factors", value, output == null ? ERROR : arrayListToString(output));
        });
        get("/primes", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            ArrayList<Integer> output = primes(value);
            return jsonResponse("primes", value, output == null ? ERROR : arrayListToString(output));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String jsonResponse(String operation, int input, String output) {
        return "{" +
                " \"operation\": \"" + operation + "\"," +
                " \"input\": " + input + "," +
                " \"output\":  \"" + output + "\""+
                "}";
    }

    private static ArrayList<Integer> primes(int value) {
        if (value <= 1) return null;
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= value; i++) {
            if (Objects.requireNonNull(factors(i)).size() == 2) primes.add(i);
        }
        return primes;
    }

    private static ArrayList<Integer>  factors(int value) {
        if (value <= 0) return null;
        ArrayList<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= value; i++) {
            if (value%i == 0) factors.add(i);
        }
        return factors;
    }

    private static String arrayListToString(ArrayList<Integer> array) {
        StringBuilder stringArray = new StringBuilder();
        for (int number: array) {
            stringArray.append(number).append(", ");
        }
        int stringLength = stringArray.length();
        return stringArray.delete(stringLength -2, stringLength).toString();
    }
}