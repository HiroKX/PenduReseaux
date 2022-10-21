package jeu;

import java.io.BufferedReader;
import java.io.PrintStream;

public class JeuADeux extends Jeu{

    public JeuADeux(BufferedReader in, PrintStream out){

        super(in, out);
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void game() {
    }

    @Override
    public boolean isWon() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String getNom() {
        return "Jeu à deux";
    }
}
