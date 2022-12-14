package serveur;

import jeu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur extends Thread {

    final static int port = 9632;
    private Socket socket;
    
    private ServerSocket socketServeur;

    public static void main(String[] args) {
        try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur sur "+ socketServeur.getInetAddress());
            while (true) {
                Socket socketClient = socketServeur.accept();
                Serveur t = new Serveur(socketClient);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Serveur(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        traitements();
    }

    public void traitements() {
        try {
            System.out.println("Connexion avec le client : " + socket.getInetAddress()+"\nChannel : "+socket.getChannel()+"\nPort :"+socket.getPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            newGame(in,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newGame(BufferedReader in, PrintStream out) throws IOException {
        Jeu j = init(in, out);
        if(j == null){
            j = init(in, out);
        }
        j.init();
        while(!j.isWon() && !j.isEnd()) {
            j.game();
        }
        if(j.newGame()){
            newGame(in,out);
        }
        else{
            out.println("Au revoir !");
            this.close(in,out);
        }
    }


    public Jeu init(BufferedReader in, PrintStream out) {
        String info = "Bonjour, quel partie souhaite tu lancer ? (Il suffit d'indiquer le numéro de la partie)\n";
        info += "0 : Jeu Simple \n";
        info += "1 : Jeu Inverse \n";
        info += "2 : Jeu A Difficulte \n";
        info += "3 : Jeu Chronométré \n";
        info += "4 : Quitter \n";
        out.println(info);
        String input = "";
        try {
            input = in.readLine();
            while (input.length() > 1 || !input.matches("[^[a-zA-Z]+$]")) {
                out.println(info);
                input = in.readLine();
                while (in.ready()) {
                    input = in.readLine();
                }
            }
        }catch (NullPointerException|IOException e){
            System.out.println("Le client à deconnecter.");
        }
        return createJeu(input, in, out);
    }

    private Jeu createJeu(String input,BufferedReader in,PrintStream out) {
        if(input == null) return null;
        int inputInt = Integer.parseInt(input);
        return switch (inputInt) {
            case 0 -> new JeuSimple(in,out);
            case 1 -> new JeuInverse(in,out);
            case 2 -> new JeuADifficulte(in,out);
            case 3 -> new JeuChronometre(in, out);
            case 4  -> close(in,out);
            default -> null;
        };
    }
    public Jeu close(BufferedReader in,PrintStream out){
        out.close();
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
