import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static ArrayList <Animale> animali = new ArrayList<>();
    static ArrayList <Pianta> piante = new ArrayList<>();
    static int numeroPiante = contaJSON("piante.json");             //Contiene il numero di elementi presenti nel file json piante.json
    static int numeroAnimali = contaJSON("animali.json");           //contiene il numero di elementi presenti nel file json animali.json

    public static void main(String[] args) {
        int anno = 0;
        riempiAL();             //Inizializza gli ArrayList
        nuovoVegetale(anno);

        while (true) {
            try {
                Thread.sleep(600000);                           //Ogni 600 secondi passa un'anno
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String infoAnno = trascorriAnno();

            System.out.println("Anno: " + anno);
            System.out.println(infoAnno);
            anno++;
        }
    }

    //Funzione che fa trascorrere un anno all'interno del programma
    public static String trascorriAnno () {
        String str = "";
        return str;
    }

    //Funzione che riempie l'AL animali con numeroAnimali * 100 esemplari e l'AL piante con numeroPiante * 10000 esemplari.
    public static void riempiAL () {
        Animale a = new Animale();
        Pianta p = new Pianta();

        for (int i = 0; i < numeroAnimali * 100; i++) {
            a.inizializzaRandom();
            animali.set(i,a);
        }

        for (int i = 0; i < numeroPiante * 10000; i++) {
            p.inizializzaRandom();
            piante.set(i,p);
        }
    }

    //Funzione che fa riprodurre le piante
    public static void nuovoVegetale (int a) {
        Pianta p = new Pianta();
        boolean[] esiste = new boolean[numeroPiante]; 

        if (a == 0) {
            for (int i = 0; i < numeroPiante; i++) {
                esiste[i] = true;
            }
        } else {
            for (int i = 0; i < numeroPiante; i++) {
                esiste[i] = controllaEsistenza(i);
            }
        }

        for (int i = 0; i < numeroPiante; i++) {
            if (esiste[i]) {
                int ripr = numeroRiproduzione(i);
                for (int j = 0; j < ripr; j++) {
                    p.creaPianta(i);
                    piante.set(i,p);
                }
            }
        }
    }

    //Controlla che nell'AL piante esistano degli esemplari con il codice passato come parametro, ritorna true se esistono, false altrimenti
    public static boolean controllaEsistenza (int id) {
        for (int i = 0; i < piante.size(); i++) {
            if (piante.get(i).getCodice() == id)
                return true;
        }
        return false;
    }

    //Ritorna il numero di elementi presenti in un file JSON
    public static int contaJSON (String filename) {
        String strJson = LeggiJson.leggiJson(filename);
        JSONArray jsArr = LeggiJson.estrapolaArray(strJson, filename);
        return jsArr.length();
    }

    //Ritorna il valore di riproduzione di una specie di pianta, dato il codice della pianta
    public static int numeroRiproduzione (int id) {
        String strJson = LeggiJson.leggiJson("piante.json");
        JSONArray jsArr = LeggiJson.estrapolaArray(strJson, "piante");
        JSONObject pianta = jsArr.getJSONObject(id);
        return pianta.getInt("riproduzione");
    }

}