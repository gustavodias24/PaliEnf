package benicio.solucoes.palienf.utils;

import com.google.gson.Gson;

import java.util.List;

import benicio.solucoes.palienf.model.DiagnosticoModel;
import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DiagnosticosUtils {

    public static DiagnosticoModel[] returnListaDiagnostico(Context c){
        StringBuilder rawJson = new StringBuilder();

        try {
            // Abrir o arquivo JSON
            InputStream inputStream = c.getAssets().open("carga");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // Ler cada linha do arquivo JSON e append no StringBuilder
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rawJson.append(line);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Gson().fromJson(rawJson.toString(), DiagnosticoModel[].class);
    }

}
