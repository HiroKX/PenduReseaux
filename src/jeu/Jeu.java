package jeu;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class Jeu {

    BufferedReader in;
    PrintStream out;
    private final ArrayList<Character> motAct;

    protected String motChoisis;
    private int maxFails;

    private int nbFails = 0;

    private long startChrono, finChrono;

    private ArrayList<String> lMots;
    protected ArrayList<Character> lLettres;


    public Jeu(BufferedReader in, PrintStream out) {
        this.in = in;
        this.out = out;
        this.maxFails = 5;
        this.lLettres = new ArrayList<Character>();
        this.motAct = new ArrayList<Character>();
        this.lMots = new ArrayList<String>();
        lMots.add("essai");
        lMots.add("porte");
        lMots.add("passer");
        lMots.add("casse");
        lMots.add("test");
        lMots.add("main");
        lMots.add("malin");
        this.motChoisis = lMots.get(new Random().nextInt((lMots.size())));
    }

    public boolean newGame() {
        String info = "";
        if(isWon()){
            info +="Bravo ! Vous avez gagné ! Le mot était : "+ motChoisis+ "\n";
        }else{
            info +="Dommage ! Vous avez perdu! Le mot était : "+ motChoisis+ "\n";
        }
        info += "Voulez vous commencer une autre partie ? (0 : Non, 1 : Oui)";
        sendMessage(info);
        try {
            String input = "a";
            while (!Objects.equals(input, "0") && !Objects.equals(input, "1")) {
                input = waitMessage();
                sendMessage(info);
            }
            return Objects.equals(input, "1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEtatMot() {
        char[] mot = new char[motChoisis.length()];
        for (int i = 0; i < motChoisis.length(); i++) {
            mot[i] = '_';
        }
        for (Character c : lLettres) {
            ArrayList<Integer> occ = findOccurences(motChoisis, c);
            for (Integer i : occ) {
                mot[i] = c;
            }
        }
        return String.valueOf(mot);
    }

    public ArrayList<Integer> findOccurences(String word, Character letter) {
        int index = word.indexOf(letter);
        ArrayList<Integer> occ = new ArrayList<Integer>();
        while (index >= 0) {
            occ.add(index);
            index = word.indexOf(letter, index + 1);
        }
        return occ;
    }

    public boolean addLettres(String c) {
        if (!lLettres.contains(c.toLowerCase().charAt(0))) {
            lLettres.add(c.toLowerCase().charAt(0));
            if (!motChoisis.contains(c.toLowerCase()))
                nbFails++;
            return true;
        }
        return false;
    }

    public boolean init() throws IOException {
        ChoisirCHrono();
        out.println("Bienvenue dans le "+getNom()+"!\nAppuyer sur entrée pour continuer !");
        in.readLine();
        return false;
    }

    public void game() throws IOException {
        String info = "Voici l'état du Jeu : " + getEtatMot() + "\n";
        info += "Il vous reste : " + (this.getMaxFails() - this.getNbFails()) + "/" + this.getMaxFails() + " essais";
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

    public void repeatGame() throws IOException {
        String info = "Voici l'état du Jeu : " + getEtatMot() + "\n";
        info += "Il vous reste : " + (this.getMaxFails() - this.getNbFails()) + "/" + this.getMaxFails() + " essais \n";
        info += "Vous avez rentrer une lettre déjà présente.";
        sendMessage(info);
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

    public String waitMessage() throws IOException {
        String message = in.readLine();
        while (in.ready() && message == null) {
            message = in.readLine();
        }
        return message;
    }


    public void sendMessage(String info){
        out.println(info);
    }


    public boolean isWon(){
        return Objects.equals(getEtatMot(), motChoisis);
    }

    public boolean isEnd(){
        if(nbFails==maxFails || finChrono<=System.currentTimeMillis()){
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
            e.printStackTrace();
        }
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

    public abstract String getNom();

    public int getNbFails() {
        return nbFails;
    }

    public void setNbFails(int nbFails) {
        this.nbFails = nbFails;
    }

    public int getMaxFails() {
        return maxFails;
    }

    public void setMaxFails(int maxFails) {
        this.maxFails = maxFails;
    }
}
