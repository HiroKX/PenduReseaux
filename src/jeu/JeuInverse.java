package jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import static tools.Outils.isInteger;

public class JeuInverse extends Jeu{

    private ArrayList<String> letters;


    public JeuInverse(BufferedReader in, PrintStream out){
        super(in,out);
        letters = new ArrayList<String>();
        letters.add("a");
        letters.add("b");
        letters.add("c");
        letters.add("d");
        letters.add("e");
        letters.add("f");
        letters.add("g");
        letters.add("h");
        letters.add("i");
        letters.add("j");
        letters.add("k");
        letters.add("l");
        letters.add("m");
        letters.add("n");
        letters.add("o");
        letters.add("p");
        letters.add("q");
        letters.add("r");
        letters.add("s");
        letters.add("t");
        letters.add("u");
        letters.add("v");
        letters.add("w");
        letters.add("x");
        letters.add("y");
        letters.add("z");
    }

    @Override
    public boolean init() throws IOException {
        String info = "Bienvenue dans le "+getNom()+"!\nVeuillez rentrer un mot !";
        out.println(info);
        String mot = waitMessage();
        if(mot.length() < 1){
            out.println(info);
            mot = waitMessage();
        }
        this.motChoisis = mot;
        info = "Veuillez entrer un nombre d'essais maximal !";
        out.println(info);
        mot = waitMessage();
        if(mot.length() < 1 && !isInteger(mot)){
            out.println(info);
            mot = waitMessage();
        }
        this.setMaxFails(Integer.parseInt(mot));
        return false;
    }


    @Override
    public void game() throws IOException {
        String info ="C'est parti ! \n";
        while(!isWon() && !isEnd()) {
            info += "Voici l'état du Jeu : " + getEtatMot() + "\n";
            info += "Il reste : " + (this.getMaxFails() - this.getNbFails()) + "/" + this.getMaxFails() + " essais \n";
            String lettres = letters.get((int) (Math.random() * letters.size()));
            while(lLettres.contains(lettres.charAt(0))){
                lettres = letters.get((int) (Math.random() * letters.size()));
            }
            info += "Le serveur a choisis la lettre : "+ lettres+"\n";
            if(this.addLettres(lettres)){
                info += "Bonne déduction !\n\n";
            }else{
                info+="Dommage...\n\n";
            }
        }
        info += "Voici l'état du Jeu : " + getEtatMot() + "\n";
        info += "Il reste : " + (this.getMaxFails() - this.getNbFails()) + "/" + this.getMaxFails() + " essais \n";
        info += "Appuyer sur entrée pour voir le résultat !";
        out.println(info);
        in.readLine();
    }

    @Override
    public String getNom() {
        return "Jeu Inverse";
    }
}
