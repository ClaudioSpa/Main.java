import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Animale{
    private String nomeSp;                                  //Nome specie (lupo, gatto, rinoceronte, ...)
    private int codice;                                     //Codice animale
    private char sesso;                                     //Sesso animale, m o f
    private int etaMorte;                                   //Eta' in cui l'animale muore
    private int etaAdulto;                                  //Eta' in cui l'animale diventa adulto
    private int numeroCucMax;                               //Numero massimo di cuccioli
    private int eta;                                        //Eta' dell'animale
    ArrayList<String> pianteNec = new ArrayList<>();        //Piante necessarie all'animale per sopravvivere
    private int numeroCuc;                                  //Numero di cuccioli dell'animale
    private int ciboAnnuo;                                  //Cibo annuo necessario all'animale per sopravvivere.

    //GETTERS e SETTERS
    public String getNomeSp() {
        return nomeSp;
    }

    public void setNomeSp(String nomeSp) {
        this.nomeSp = nomeSp;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public int getEtaMorte() {
        return etaMorte;
    }

    public void setEtaMorte(int etaMorte) {
        this.etaMorte = etaMorte;
    }

    public int getEtaAdulto() {
        return etaAdulto;
    }

    public void setEtaAdulto(int etaAdulto) {
        this.etaAdulto = etaAdulto;
    }

    public int getNumeroCucMax() {
        return numeroCucMax;
    }

    public void setNumeroCucMax(int numeroCucMax) {
        this.numeroCucMax = numeroCucMax;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public ArrayList<String> getPianteNec() {
        return pianteNec;
    }

    public void setPianteNec(ArrayList<String> pianteNec) {
        this.pianteNec = pianteNec;
    }

    public int getNumeroCuc() {
        return numeroCuc;
    }

    public void setNumeroCuc(int numeroCuc) {
        this.numeroCuc = numeroCuc;
    }

    public int getCiboAnnuo() {
        return ciboAnnuo;
    }

    public void setCiboAnnuo(int ciboAnnuo) {
        this.ciboAnnuo = ciboAnnuo;
    }

    //COSTRUTTORE di tutti i campi
    public Animale(String nomeSp, int codice, char sesso, int etaMorte, int etaAdulto, int numeroCucMax, int eta,
            ArrayList<String> pianteNec, int numeroCuc, int ciboAnnuo) {
        this.nomeSp = nomeSp;
        this.codice = codice;
        this.sesso = sesso;
        this.etaMorte = etaMorte;
        this.etaAdulto = etaAdulto;
        this.numeroCucMax = numeroCucMax;
        this.eta = eta;
        this.pianteNec = pianteNec;
        this.numeroCuc = numeroCuc;
        this.ciboAnnuo = ciboAnnuo;
    }

    //COSTRUTTORE vuoto
    public Animale(){}

    public static String leggiJson(String filename) {
       String jsonText = "";
       try {
        BufferReader bufferedReader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            jsonText += line + "\n";
        }

        bufferedReader.close();

       } catch (Exception e) {
        e.printStackTrace();
       }

       return jsonText;
    }

    public void genera(int id) {
        String strJson = leggiJson("animali.json");

        System.out.println(strJson);
    }

    
    
    
}