import java.util.ArrayList;

public class Main {

    static ArrayList <Animale> animali = new ArrayList<>();
    static ArrayList <Pianta> piante = new ArrayList<>();

    public static void main(String[] args) {
        int anno = 0;
        riempiALanimali();
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

    //Funzione che riempie l'AL animali con 1000 esemplari
    public static void riempiALanimali () {
        Animale a = new Animale();

        for (int i = 0; i < 1000; i++) {
            a.inizializzaRandom();
            animali.set(i,a);
        }
    }

    //Funzione che fa riprodurre le piante
    public static void nuovoVegetale (int a) {
        boolean[] esiste = new boolean[10]; 
        if (a == 0) {
            for (int i = 0; i < 10; i++) {
                esiste[i] = true;
            }
        } else {
            for (int i = 0; i < 10; i++) {
                esiste[i] = controllaEsistenza(i);
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

}