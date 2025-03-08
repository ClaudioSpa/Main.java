/*import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;*/
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;


class Animale{
    private String nomeSp;                                          //Nome specie (lupo, gatto, rinoceronte, ...)
    private int codiceSp;                                           //Codice della specie dell'animale
    private long codiceAn;                                          //Codice "Privato" dell'animale
    private char sesso;                                             //Sesso animale, m o f
    private int etaMorte;                                           //Eta' in cui l'animale muore
    private int etaAdulto;                                          //Eta' in cui l'animale diventa adulto
    private int numeroCucMax;                                       //Numero massimo di cuccioli
    private int eta;                                                //Eta' dell'animale
    private ArrayList<Integer> pianteNec = new ArrayList<>();       //Piante necessarie all'animale per sopravvivere
    private int numeroCuc;                                          //Numero di cuccioli dell'animale
    private int ciboAnnuo;                                          //Cibo annuo necessario all'animale per sopravvivere.

    //GETTERS e SETTERS
    public String getNomeSp() {
        return nomeSp;
    }

    public void setNomeSp(String nomeSp) {
        this.nomeSp = nomeSp;
    }

    public long getCodiceAn() {
        return codiceAn;
    }

    public void setCodiceAn(long codiceAn) {
        this.codiceAn = codiceAn;
    }

    public int getCodice() {
        return codiceSp;
    }

    public void setCodice(int codiceSp) {
        this.codiceSp = codiceSp;
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

    public ArrayList<Integer> getPianteNec() {
        return pianteNec;
    }

    public void setPianteNec(ArrayList<Integer> pianteNec) {
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
    public Animale(String nomeSp, int codiceSp, int codiceAn, char sesso, int etaMorte, int etaAdulto, int numeroCucMax, int eta,
            ArrayList<Integer> pianteNec, int numeroCuc, int ciboAnnuo) {
        this.nomeSp = nomeSp;
        this.codiceSp = codiceSp;
        this.codiceAn = codiceAn;
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
    public Animale() {}

    //Genera un Animale dato l'id dell'animale
    public JSONObject genera(int id) {
        String strJson = LeggiJson.leggiJson("animali.json");
        JSONArray jsArr = LeggiJson.estrapolaArray(strJson, "animali");
        
        //Ciclo per trovare l'elemento dell'array con il codice richiesto
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject animale = jsArr.getJSONObject(i);
            if (animale.getInt("codice") == id)
                return animale;
        }
        
        return null;
    }

    //Crea un nuovo cucciolo (riempie i campi della classe) dato il codice
    public void creaAnimale (int id) {
        try {
            JSONObject animale = genera(id);

            nomeSp = animale.getString("nome");                                  
            codiceSp = animale.getInt("codice"); 

            if (System.currentTimeMillis() % 2 == 0)   
                sesso = 'm';
            else
                sesso = 'f';  

            etaMorte = animale.getInt("eta_morte");                              
            etaAdulto = animale.getInt("eta_adulto");                              
            numeroCucMax = animale.getInt("num_max_cuccioli");                            
            eta = 0;  
            
            for (int i = 0; i < animale.getJSONArray("piante_mangiate").length(); i++) {
                pianteNec.add(animale.getJSONArray("piante_mangiate").getInt(i));
            }       
        
            numeroCuc = 0;                                  
            ciboAnnuo = animale.getInt("cibo_annuo");
            codiceAn = System.currentTimeMillis();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //toString del metodo
    @Override
    public String toString() {
        return "Animale" + "[nomeSp=" + nomeSp + ", codice specie=" + codiceSp + ", codice animale=" + codiceAn + ", sesso=" + sesso + 
        ", etaMorte=" + etaMorte + ", etaAdulto=" + etaAdulto + ", numeroCucMax=" + numeroCucMax + ", eta=" + eta + ", pianteNec="
        + pianteNec + ", numeroCuc=" + numeroCuc + ", ciboAnnuo=" + ciboAnnuo + "]";
    }

    //Crea un cucciolo random
    public void inizializza () {
        Random rand = new Random();
        creaAnimale(rand.nextInt(10));
    }
    
    //Crea un animale con un'eta' random (cucciolo o adulto)
    public void inizializzaRandom () {
        inizializza();
        Random rand = new Random();
        eta = rand.nextInt(etaMorte);
    }
}