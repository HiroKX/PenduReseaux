package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.stream.Stream;

public class Client {
    final static int port = 9632;

    public static void main(String[] args) {

        Socket socket;
        DataInputStream userInput;
        PrintStream theOutputStream;


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Entrer l'ip du serveur :");
            String ip = reader.readLine();

            InetAddress serveur = InetAddress.getByName(ip);
            socket = new Socket(serveur, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            while(true) {
                String message = in.readLine();
                System.out.println(message);
                while (in.ready()) {
                    message = in.readLine();
                    System.out.println(message);
                }
                if(Objects.equals(message, "Au revoir !")){
                    out.close();
                    in.close();
                    socket.close();
                }else {
                    message = reader.readLine();
                    System.out.println(message);
                    System.out.println(message.length());
                    System.out.println(message.matches("[^[a-zA-Z0-9]+$]"));

                    while (message.length() > 1|| message.matches("[^[a-zA-Z0-9]+$]")) {
                        System.out.println("Cela ne correspond pas au caractères demander...");
                        message = reader.readLine();
                    }
                    out.println(message);
                }
            }
        } catch (Exception e) {
            System.out.println("Deconnecter");
        }
    }

}
