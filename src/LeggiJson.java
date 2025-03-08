import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

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

    //Data una stringa in formato JSON, ritorna un oggetto JSONArray, oppure null se ci dovessero essere errori durante l'esecuzione.
    public static JSONArray estrapolaArray (String strJson, String key) {
        try {
            // Trasforma la stringa JSON in un oggetto
            JSONObject jsonObject = new JSONObject(strJson);

            //Crea l'array con tutti le piante
            JSONArray arr = jsonObject.getJSONArray(key);
            return arr;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }    
    }
}