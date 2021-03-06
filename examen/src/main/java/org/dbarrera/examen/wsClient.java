package org.dbarrera.examen;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 6/25/13.
 */
public class wsClient extends Thread {

    Context c;

    wsClient(Context context){
        this.c = context;
    }

    private static final String url = "http://10.0.2.2/examen/index.php";

    private List<NameValuePair> parametros = null;

    public void setStudParam(String nombre, String matricula_cedula, String direccion,
                              String telefono, String email, String carrera, String anio_ingreso,
                              String curso){
        this.parametros = new ArrayList<NameValuePair>();
        this.parametros.add(new BasicNameValuePair("opcion","NEWSTUD"));
        this.parametros.add(new BasicNameValuePair("nombre",nombre));
        this.parametros.add(new BasicNameValuePair("matricula_cedula",matricula_cedula));
        this.parametros.add(new BasicNameValuePair("direccion",direccion));
        this.parametros.add(new BasicNameValuePair("telefono",telefono));
        this.parametros.add(new BasicNameValuePair("email",email));
        this.parametros.add(new BasicNameValuePair("carrera",carrera));
        this.parametros.add(new BasicNameValuePair("anio_ingreso",anio_ingreso));
        this.parametros.add(new BasicNameValuePair("curso",curso));
    }

    public void setWorkParam(String nombre, String codigo, String creditos,
                             String profesor, String unidad, String fecha_inicio, String horario,
                             String horas){
        this.parametros = new ArrayList<NameValuePair>();
        this.parametros.add(new BasicNameValuePair("opcion","NEWWSHOP"));
        this.parametros.add(new BasicNameValuePair("nombre",nombre));
        this.parametros.add(new BasicNameValuePair("codigo",codigo));
        this.parametros.add(new BasicNameValuePair("creditos",creditos));
        this.parametros.add(new BasicNameValuePair("profesor",profesor));
        this.parametros.add(new BasicNameValuePair("unidad",unidad));
        this.parametros.add(new BasicNameValuePair("fecha_inicio",fecha_inicio));
        this.parametros.add(new BasicNameValuePair("horario",horario));
        this.parametros.add(new BasicNameValuePair("horas",horas));
    }

    public void run() {
        JSONObject json = JSONParser.getJSONFromUrl(url, parametros);
        try {
            Log.d(wsClient.class.getName(),json.toString());
            if (json.getString("exito") != null){
                Log.d(wsClient.class.getName(),"Success! Data has been saved.");
                Toast.makeText(c, "Success! Data has been saved.", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(wsClient.class.getName(),"Error! Parsing could not be made.");
                Toast.makeText(c, "Error! Parsing could not be made.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}