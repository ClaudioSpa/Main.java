import java.io.BufferedReader;
import java.io.FileReader;

class LeggiJson {
    //Legge il file json e ritorna una stringa
    public static String leggiJson(String filename) {
        String jsonText = "";
        try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

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
}