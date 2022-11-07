package jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class JeuChronometre extends Jeu{

    private long startChrono, finChrono;

    public JeuChronometre(BufferedReader in, PrintStream out){
        super(in,out);
    }

    @Override
    public boolean init() throws IOException {
        ChoisirChrono();
        out.println("Bienvenue dans le "+getNom()+"!\nAppuyer sur entrée pour continuer !");
        in.readLine();
        return false;
    }

    @Override
    public void game() throws IOException {
        String info = "Voici l'état du Jeu : " + getEtatMot() + "\n";
        info += "Il vous reste : " + (finChrono - (System.currentTimeMillis()))/1000 + " secondes";
        out.println(info);
        String message = "";
        while (message.length() == 0) {
            message = waitMessage();
        }
        if (message.length() == 1) {
            if (!addLettres(message)){
                repeatGame();
            }
        }
    }

    @Override
    public boolean isEnd(){
        if(finChrono<=System.currentTimeMillis()){
            return true;
        }else {
            return false;
        }
    }

    public void ChoisirChrono(){
        out.println("Indiquez le temps du chronometre");
        String input1 = null;
        try {
            input1 = in.readLine();


        } catch (IOException e) {
            out.println("test: error");
            e.printStackTrace();
        }
        //out.println("test: " + input1);
        Chronometre(Long.parseLong(input1));
    }

    public void Chronometre(long t){
        StartChrono();
        FinChrono(t*1000);
    }

    public void StartChrono(){
        startChrono = System.currentTimeMillis();
    }

    public void FinChrono(long t){
        finChrono = startChrono + t;
    }

    @Override
    public String getNom() {
        return "Jeu Chronométré";
    }
}
