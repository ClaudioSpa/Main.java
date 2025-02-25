import java.util.ArrayList;

class Animale{
    private String nomeSp;                                  //Nome specie (lupo, gatto, rinoceronte, ...)
    private char sesso;                                     //Sesso animale, m o f
    private int etaMorte;                                   //Eta' in cui l'animale muore
    private int etaAdulto;                                  //Eta' in cui l'animale diventa adulto
    private int numeroCucMax;                               //Numero massimo di cuccioli
    private int eta;                                        //Eta' dell'animale
    ArrayList<String> pianteNec = new ArrayList<>();        //Piante necessarie all'animale per sopravvivere
    private int numeroCuc;                                  //Numero di cuccioli dell'animale
}