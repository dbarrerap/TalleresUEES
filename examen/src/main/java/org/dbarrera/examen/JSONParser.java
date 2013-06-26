package org.dbarrera.examen;

import android.util.Log;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;
import java.util.Locale;

/**
 * Created by david on 6/25/13.
 */
public class JSONParser {
    public static JSONObject getJSONFromUrl(String url, List<NameValuePair> parametros){

        /*
         * Crear conexion HTTP
         */
        InputStream inputStream = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        Log.d(JSONParser.class.getCanonicalName(),url + " " + parametros.toString());

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse httpRes = httpClient.execute(httpPost);
            inputStream = httpRes.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * Parsear respuesta del servidor
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
