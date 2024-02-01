package Actividad_3_1;

import java.io.*;
import java.net.*;
import java.util.Random;

public class AdivinaNumeroServer {
    public static void main(String[] args) {
        //puerto que usamos
        int port = 2000;
        //creamos un serverSocket
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Servidor iniciado en el puerto " + port + ". Esperando al cliente...");
            //aceptamos la conexión
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            //creamos el PrintWriter y el BufferedReader de la conexión
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Random random = new Random();
            //generamos un numero aleatorio entre el 0 y el 100 (ambos incluidos)
            int numerosecreto = random.nextInt(101);
            int guess = -1;
            while (guess != numerosecreto) {
                String input = in.readLine();
                try {
                    //convertimos el String a int
                    guess = Integer.parseInt(input);
                    if (guess < numerosecreto) {
                        //le decimos al cliente que el numero secreto es mayor
                        out.println("El numero secreto es mayor");
                    } else if (guess > numerosecreto) {
                        //le decimos al cliente que el numero secreto es menor
                        out.println("El numero secreto es menor");
                    } else {
                        //le decimos al cliente que el numero secreto es correcto
                        out.println("¡Correcto! ese es el numero secreto ");
                    }
                    //si el String no es un numero
                } catch (NumberFormatException e) {
                    //le decimos al cliente que el numero secreto es correcto
                    out.println("ingresa un número válido.");
                }
            }
            //le decimos al cliente que el numero secreto es correcto
            System.out.println("Número adivinado. Cerrando servidor.");
        } catch (IOException e) {
            System.out.println("Excepción al intentar iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
