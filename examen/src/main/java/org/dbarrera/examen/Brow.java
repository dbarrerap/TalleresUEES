package org.dbarrera.examen;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by david on 6/30/13.
 */
public class Brow extends Activity {

    String mName, mTeacher;
    ListView lv;
    ArrayList<String> arrayList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brow_lv);
        if (savedInstanceState == null){
            setupWidgets();
        }

    }

    private void setupWidgets() {
        lv = (ListView)findViewById(R.id.listView);
        populateLV();
    }

    private void populateLV() {
        DoPOST mDoPOST = new DoPOST(this);
        mDoPOST.execute("");
    }

    public class DoPOST extends AsyncTask<String, Void, Boolean> {

        Context mContext;
        String workshopSearch = "";

        //Result
        String wName, wTeacher;
        Exception ex = null;

        DoPOST(Context context){
            mContext = context;
        }

        DoPOST(Context context, String stringToSearch){
            mContext = context;
            workshopSearch = stringToSearch;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("opcion","wildSearch"));

                HttpParams httpParams = new BasicHttpParams();

                HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
                HttpConnectionParams.setSoTimeout(httpParams, 15000);

                HttpClient httpClient = new DefaultHttpClient(httpParams);
                HttpPost httpPost = new HttpPost("http://10.0.2.2/examen/index.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();

                String result = EntityUtils.toString(entity);

                JSONObject jsonObject = new JSONObject(result);

                System.out.println(jsonObject.getString("curso"));

//                wName = jsonObject.getString("Nombre");
//                wTeacher = jsonObject.getString("Profesor");
            } catch (Exception e) {
                Log.e(BrowseWorkshop.class.getName(), e.getMessage());
                ex = e;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(ex != null){
                Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}