package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.stream.Stream;

public class Client {
    final static int port = 9632;

    public static void main(String[] args) {

        Socket socket;
        DataInputStream userInput;
        PrintStream theOutputStream;

        try {
            InetAddress serveur = InetAddress.getByName("127.0.0.1");
            socket = new Socket(serveur, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            while(true) {
                System.out.println(in.readLine());
                while (in.ready()) {
                    System.out.println(in.readLine());
                }
                String message = reader.readLine();
                out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
