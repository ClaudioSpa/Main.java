import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static ArrayList <Animale> animali = new ArrayList<>();
    static ArrayList <Pianta> piante = new ArrayList<>();
    static int numeroPiante = contaJSON("piante.json");             //Contiene il numero di elementi presenti nel file json piante.json
    static int numeroAnimali = contaJSON("animali.json");           //contiene il numero di elementi presenti nel file json animali.json

    static int pianteNateAnno = 0;
    static int animaliNatiAnno = 0;
    static int pianteMangiate = 0;
    static int pianteMorte = 0;
    static int animaliMorti = 0;

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

            anno++;
            System.out.println("Anno: " + anno);
            String infoAnno = trascorriAnno();
            System.out.println(infoAnno); 
            
            pianteNateAnno = 0;
            animaliNatiAnno = 0;
            pianteMangiate = 0;
            pianteMorte = 0;
            animaliMorti = 0;
        }
    }

    //Funzione che fa trascorrere un anno all'interno del programma
    public static String trascorriAnno () {
        String str = "";
        boolean elimina = false;

        for (int i = 0; i < numeroAnimali; i++) {                           //Sezione riproduzione animale
            ArrayList<Animale> specie = new ArrayList<>();
            specie = partiziona(i);

            if (specie.size() > 1 && sessoDiverso(specie) && tuttiFigli(specie)) {
                riproduzione(specie);
            }
            else {
                elimina = true;
            }

            if (elimina) {
                eliminaAnimale(i);
            }
        }

        elementiEta();
        uccidiEsemplari();

        mangia();                              //Sezione nutimento animale

        str = produciStr();
    
        return str;
    }


    //Funzione che produce la stringa informativa 
    public static String produciStr (){
        return " Numero di animali nati in un anno: " + animaliNatiAnno +
                "\nNumero di animali morti in un anno: " + animaliMorti + 
                "\nNumero di piante nate in un anno: " + pianteNateAnno +
                "\nNumero di piante mangiate in un anno: " + pianteMangiate +
                "\nNumero di piante morte in un anno: " + pianteMorte;
    }

    //Funzione che aumenta l'eta' di tutti gli esemplari, sia piante sia animali
    public static void elementiEta () {
        for (int i = 0; i < animali.size(); i++) {
            animali.get(i).setEta(animali.get(i).getEta() + 1);
        }

        for (int i = 0; i < piante.size(); i++) {
            piante.get(i).setEta(piante.get(i).getEta() + 1);
        }
    }

    //Funzione che uccide tutti gli esemplari, sia piante sia animali, che hanno raggiunto l'eta' di morte
    public static void uccidiEsemplari () {
        for (int i = 0; i < animali.size(); i++) {
            if (animali.get(i).getEta() > animali.get(i).getEtaMorte())
                eliminaIndividuoAnimale(i);
        }

        for (int i = 0; i < piante.size(); i++) {
            if (piante.get(i).getEta() > piante.get(i).getEtaMorte())
                eliminaIndividuoPianta(i);
        }
    }

    //Funzione che riempie l'AL animali con numeroAnimali * 100 esemplari e l'AL piante con numeroPiante * 10000 esemplari.
    public static void riempiAL () {
        Animale a = new Animale();
        Pianta p = new Pianta();

        for (int i = 0; i < numeroAnimali * 100; i++) {
            a.inizializzaRandom();
            while (controllaCodice(a)) {
                a.setCodiceAn(a.getCodiceAn() + 1);
            }
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
                    pianteNateAnno++;
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

    //Funzione che fa riprodurre tutti gli animali di una stessa specie
    public static void riproduzione (ArrayList<Animale> s) {
        Animale a1 = new Animale();
        Animale a2 = new Animale();

        for (int i = 0; i < s.size(); i++) {
            a1 = s.get(i);
            if (a1.getEta() >= a1.getEtaAdulto()) {
                for (int j = 0; j < s.size(); j++) {
                    a2 = s.get(j);
                    if (a1.getSesso() != a2.getSesso() && a2.getEta() >= a2.getEtaAdulto()) {
                        riproduci(s, i, j);
                    }
                }
            }
        }
    }

    //Funzione che dato due animali completa la riproduzione fra gli stessi 
    public static void riproduci (ArrayList<Animale> s, int i, int j) {
        Random rand = new Random();
        int numFigli = rand.nextInt(s.get(j).getNumeroCucMax());

        for (int k = 0; k < numFigli; k++) {                            //Nascita nuovi animali
            Animale a = new Animale();
            a.creaAnimale(s.get(j).getCodice());
            while (controllaCodice(a)) {
                a.setCodiceAn(a.getCodiceAn() + 1);
            }
            animali.add(a);
            animaliNatiAnno++;
        }

        s.get(i).setNumeroCuc(numFigli);                                 //Assegnazione figli ai genitori
        s.get(j).setNumeroCuc(numFigli);
    }

    //Dato un codice ritorna tutti gli animali aventi quel codice, in un AL
    public static ArrayList<Animale> partiziona (int id) {
        ArrayList<Animale> a = new ArrayList<>();

        for (int i = 0; i < animali.size(); i++) {
            if (animali.get(i).getCodice() == id)
                a.add(animali.get(i));
        }

        return a;
    }

    //Dato un AL, controlla che gli animali al suo interno siano sia maschi che femmine, ritorna true se lo sono, false altrimenti
    public static boolean sessoDiverso (ArrayList<Animale> a) {
        boolean m = false, f = false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getSesso() == 'm')
                m = true;
            else
                f = true;

            if (m && f)
                return true;
        }

        return false;
    }

    //Dato un AL, controlla che gli animali al suo interno non abbiano gia' avuto figli, se tutti ce li hanno, ritorna false, true altrimenti.
    public static boolean tuttiFigli (ArrayList<Animale> a) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getNumeroCuc() == 0)
                return true;
        }
        return false;
    }

    //Controlla che il codice dell'animale sia univoco, ritorna false se lo e', true se non lo e'
    public static boolean controllaCodice (Animale a) {
        for (int i = 0; i < animali.size(); i++) {
            if (a.getCodice() == animali.get(i).getCodice())
                return true;
        }
        return false;
    }

    //Elimina l'animale con codice passato come parametro dall'AL, e dice che tale animale si e' estinto
    public static void eliminaAnimale (int id) {
        for (int i = 0; i < animali.size(); i++) {
            if (animali.get(i).getCodice() == id)
                animali.remove(i);
        }

        System.out.println("L'animale " + trovaAnimale(id) + " si e' estinto");
    }

    //Dato il codice di un animale, ritorna il suo nome
    public static String trovaAnimale (int id) {
        String strJson = LeggiJson.leggiJson("animali.json");
        JSONArray jsArr = LeggiJson.estrapolaArray(strJson, "animale");
        JSONObject animale = jsArr.getJSONObject(id);
        return animale.getString("nome");
    }

    //Funzione che fa mangiare tutti gli animali
    public static void mangia () {
        int codVeg = 0;             //Codice del vegetale che l'animale mangia
        for (int i = 0; i < animali.size(); i++) {
            codVeg = vegetaleMaggiore(animali.get(i).getPianteNec());
            if (codVeg != -1) {
                if (animali.get(i).getEta() >= animali.get(i).getEtaAdulto()) {                 //Animale adulto
                    consuma(animali.get(i).getCiboAnnuo(), codVeg);
                } else {                                                                        //Animale cucciolo
                    consuma(animali.get(i).getCiboAnnuo() / 2, codVeg);
                }
            } else {
                eliminaAnimaleSucc(animali.get(i).getCodice(), i);                             //Se un anno finisce il cibo, tutti gli animali della specie che necessita quella pianta muoiono
            }
        }

    }

    //Funzione che elimina tutti gli animali successivi a un determinato codice
    public static void eliminaAnimaleSucc (int id, int s) {
        for (int i = s; i < animali.size(); i++) {
            if (animali.get(i).getCodice() == id)
                animali.remove(i);
        }
    }

    //Funzione che fa "mangiare" un animale (elimina quella pianta dall'array in quella quantita')
    public static void consuma (int cibo, int cod) {
        for (int i = 0; i < piante.size() && cibo > 0; i++) {  
            if (piante.get(i).getCodice() == cod) {
                eliminaIndividuoPianta(i);
                cibo--;
                pianteMangiate++;
            }
        }
    } 

    //Funzione che elimina una pianta dall'AL piante 
    public static void eliminaIndividuoPianta (int i) {
        piante.remove(i);
        pianteMorte++;
    }

    //Funzione che elimina un animale dall'AL animali
    public static void eliminaIndividuoAnimale (int i) {
        animali.remove(i);
        animaliMorti++;
    }

    //Dato un AL di vegetali, ritorna il codice di quello in quantita' maggiore nel sistema
    public static int vegetaleMaggiore (ArrayList <Integer> pn) {
        int magg = 0;
        int[] numPiante = new int[pn.size()];
        Arrays.fill(numPiante, 0);

        for (int i = 0; i < pn.size(); i++) {
            for (int j = 0; j < piante.size(); j++) {
                if (pn.get(i) == piante.get(j).getCodice()) {
                    numPiante[i]++;
                }
            }

            if (numPiante[i] > magg)
                magg = numPiante[i];
        }

        if (magg != 0) {
            for (int i = 0; i < numPiante.length; i++) {
                if (magg == numPiante[i])
                    return pn.get(i);
            }
        }
        return -1;
        
    }

}