package jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class JeuSimple extends Jeu{

    public JeuSimple(BufferedReader in, PrintStream out){
        super(in,out);
    }


    @Override
    public String getNom() {
        return "Jeu simple";
    }
}
