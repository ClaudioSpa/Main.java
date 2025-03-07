/*import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;*/
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

class Pianta{
    private int etaMorte;                       //eta' in cui la pianta muore
    private int eta;                            //eta' della pianta
    private String nome;                        //Nome pianta (melo, pero, pesca, ...)
    private int codice;                         //Codice pianta
    private int riproduzione;                   //Riproduzione annua della pianta

    //GETTERS e SETTERS
    public int getEtaMorte() {
        return etaMorte;
    }

    public void setEtaMorte(int etaMorte) {
        this.etaMorte = etaMorte;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public int getRiproduzione() {
        return riproduzione;
    }

    public void setRiproduzione(int riproduzione) {
        this.riproduzione = riproduzione;
    }

    //COSTRUTTORE di tutti gli elementi
    public Pianta(int etaMorte, int eta, String nome, int codice, int riproduzione) {
        this.etaMorte = etaMorte;
        this.eta = eta;
        this.nome = nome;
        this.codice = codice;
        this.riproduzione = riproduzione;
    }

    //COSTRUTTORE vuoto
    public Pianta(){}

    //toString del metodo
    @Override
    public String toString() {
        return "Pianta [nome = " + nome + ", codice pianta = " + codice + ", eta' morte = " + etaMorte +
        ", eta' = " + eta + ", riproduzione annua = " + riproduzione + "]";
    }

    public void inizializza() {
        Random rand = new Random();
        creaPianta(rand.nextInt(10));
    }

    //Crea una pianta (riempie i campi della classe) dato il codice
    public void creaPianta (int id) {
        try {

            JSONObject pianta = genera(id);
            etaMorte = pianta.getInt("eta_morte");
            eta = 0;
            nome = pianta.getString("nome");
            codice = pianta.getInt("codice");
            riproduzione = pianta.getInt("riproduzione");
            
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //Genera una Pianta dato l'id della pianta
    public JSONObject genera(int id) {
        String strJson = LeggiJson.leggiJson("piante.json");
        JSONArray jsArr = estrapolaArray(strJson);
        JSONObject pianta;

        //Ciclo per trovare l'elemento dell'array con il codice richiesto
        for (int i = 0; i < jsArr.length(); i++) {
            pianta = jsArr.getJSONObject(i);
            if (pianta.getInt("codice") == id)
                return pianta;
        }
        
        return null;
    }

    //Data una stringa in formato JSON, ritorna un oggetto JSONArray, oppure null se ci dovessero essere errori durante l'esecuzione.
    public JSONArray estrapolaArray (String strJson) {
        try {
            // Trasforma la stringa JSON in un oggetto
            JSONObject jsonObject = new JSONObject(strJson);

            //Crea l'array con tutti le piante
            JSONArray pianteArray = jsonObject.getJSONArray("piante");
            return pianteArray;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        
    }
    

    
}