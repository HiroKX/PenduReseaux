package jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class JeuSimple extends Jeu{

    public JeuSimple(BufferedReader in, PrintStream out){
        super(in,out);
    }


    @Override
    public boolean init() throws IOException {
        out.println("Bienvenue dans le jeu Simple !\n Appuyer sur entrée pour continuer !");
        in.readLine();
        return false;
    }


    @Override
    public String getNom() {
        return "Jeu simple";
    }
}
