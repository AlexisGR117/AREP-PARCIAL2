package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.*;

/**
 * Clase que actúa como proxy para acceder a un servicio matemático.
 *
 * @author Jefer Alexis Gonzalez Romero
 * @version 1.0 (02/04/2024)
 */
public class ServiceProxy {

    private static String[] math_services;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static int instance = 0;

    /**
     * Método principal de la aplicación.
     *
     * @param args Parámetros de la línea de comandos.
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getPort());
        math_services = System.getenv("MATH_SERVICES").split(",");
        get("/factors", (req, res) -> {
            res.type("application/json");
            String output = invoker(math_services[instance] + "/factors?value=" + req.queryParams("value").replace(" ", "%20"));
            changeInstance();
            return output;
        });
        get("/primes", (req, res) -> {
            res.type("application/json");
            String output = invoker(math_services[instance] + "/primes?value=" + req.queryParams("value").replace(" ", "%20"));
            changeInstance();
            return output;
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
        return 4568;
    }


    /**
     * Cambia la instancia del servicio matemático a utilizar.
     */
    private static void changeInstance() {
        if (instance == math_services.length - 1) instance = 0;
        else instance++;
    }

    /**
     * Realiza una petición HTTP GET a una URL y devuelve la respuesta.
     *
     * @param url La URL a la que realizar la petición.
     * @return La respuesta de la petición en formato de cadena.
     * @throws IOException Si ocurre un error al realizar la petición.
     */
    private static String invoker(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
        } else System.out.println("GET request not worked");
        System.out.println(response);
        System.out.println("GET DONE");
        return response.toString();
    }
}
