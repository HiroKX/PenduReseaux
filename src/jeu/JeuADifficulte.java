package jeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static tools.Outils.isInteger;

public class JeuADifficulte extends Jeu{

    private final ArrayList<String> lMotsSimple;
    private final ArrayList<String> lMotsMoyen;
    private final ArrayList<String> lMotsDifficile;
    private final ArrayList<String> lMotsImpossible;

    public JeuADifficulte(BufferedReader in, PrintStream out) {
        super(in, out);
        this.lMotsSimple = new ArrayList<String>();
        lMotsSimple.add("essai");
        lMotsSimple.add("porte");
        lMotsSimple.add("passer");
        lMotsSimple.add("casse");
        lMotsSimple.add("test");
        lMotsSimple.add("main");
        lMotsSimple.add("malin");
        this.lMotsMoyen = new ArrayList<String>();
        lMotsMoyen.add("moyen");
        lMotsMoyen.add("moyen");
        lMotsMoyen.add("moyen");
        lMotsMoyen.add("moyen");
        lMotsMoyen.add("moyen");
        lMotsMoyen.add("moyen");
        lMotsMoyen.add("moyen");
        this.lMotsDifficile = new ArrayList<String>();
        lMotsDifficile.add("hard");
        lMotsDifficile.add("hard");
        lMotsDifficile.add("hard");
        lMotsDifficile.add("hard");
        lMotsDifficile.add("hard");
        lMotsDifficile.add("hard");
        lMotsDifficile.add("hard");
        this.lMotsImpossible = new ArrayList<String>();
        lMotsImpossible.add("impossible");
        lMotsImpossible.add("impossible");
        lMotsImpossible.add("impossible");
        lMotsImpossible.add("impossible");
        lMotsImpossible.add("impossible");
        lMotsImpossible.add("impossible");
        lMotsImpossible.add("impossible");
        
    }

    @Override
    public boolean init() throws IOException {
        String info = "Bienvenue dans le " + getNom() + "!\nVeuillez rentrer une difficulté de mot :\n";
        info += "0 : facile\n";
        info += "1 : moyen\n";
        info += "2 : difficile\n";
        info += "3 : impossible\n";
        out.println(info);
        int diff = getNumberDifficulty(info, 3);
        System.out.println(diff);
        switch (diff) {
            case 0:
                this.motChoisis = this.lMotsSimple.get((int) (Math.random() * this.lMotsSimple.size()));
                break;
            case 1:
                this.motChoisis = this.lMotsMoyen.get((int) (Math.random() * this.lMotsMoyen.size()));
                break;
            case 2:
                this.motChoisis = this.lMotsDifficile.get((int) (Math.random() * this.lMotsDifficile.size()));
                break;
            case 3:
                this.motChoisis = this.lMotsImpossible.get((int) (Math.random() * this.lMotsImpossible.size()));
                break;
            default:
                break;
        }
        info = "Veuillez entrer un nombre d'essais maximal !";
        out.println(info);
        String mot = waitMessage();
        if (mot.length() < 1 && !isInteger(mot)) {
            out.println(info);
            mot = waitMessage();
        }
        this.setMaxFails(Integer.parseInt(mot));

        return false;
    }


    public int getNumberDifficulty(String info,int max) throws IOException {
        boolean again = true;
        String mot = waitMessage();
        if(isInteger(mot)){
            int diff = Integer.parseInt(mot);
            if(diff >= 0 && diff <= 3){
                again = false;
            }
        }
        while(mot.length() < 1 || !isInteger(mot) || again){
            out.println(info);
            mot = waitMessage();
            if(isInteger(mot)){
                int diff = Integer.parseInt(mot);
                if(diff >= 0 && diff <= 3){
                    again = false;
                }
            }
        }
        return Integer.parseInt(mot);
    }


    @Override
    public String getNom() {
        return "Jeu a difficulté";
    }
}
