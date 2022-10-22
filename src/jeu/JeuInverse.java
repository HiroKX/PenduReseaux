package jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class JeuInverse extends Jeu{

    private ArrayList<Character> letters;


    public JeuInverse(BufferedReader in, PrintStream out){
        super(in,out);
        letters = new ArrayList<Character>();
        letters.add('a');
        letters.add('b');
        letters.add('c');
        letters.add('d');
        letters.add('e');
        letters.add('f');
        letters.add('g');
        letters.add('h');
        letters.add('i');
        letters.add('j');
        letters.add('k');
        letters.add('l');
        letters.add('m');
        letters.add('n');
        letters.add('o');
        letters.add('p');
        letters.add('q');
        letters.add('r');
        letters.add('s');
        letters.add('t');
        letters.add('u');
        letters.add('v');
        letters.add('w');
        letters.add('x');
        letters.add('y');
        letters.add('z');
    }
    
    @Override
    public boolean init() throws IOException {
        return false;
    }

    @Override
    public String getNom() {
        return null;
    }
}
