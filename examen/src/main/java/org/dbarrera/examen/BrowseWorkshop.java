package org.dbarrera.examen;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
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
import java.util.List;

/**
 * Created by david on 6/25/13.
 */
public class BrowseWorkshop extends Activity implements View.OnClickListener {

    TextView mCredits, mTeacher, mStartDate, mSchedule, mName, mNameSearch;
    Button searchButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_workshop);

        setupWidgets();
    }

    private void setupWidgets() {

        mNameSearch = (EditText)findViewById(R.id.et_w_nameSearch);

        mName = (TextView)findViewById(R.id.tv_name);

        mCredits = (TextView)findViewById(R.id.tv_credits);
        mTeacher = (TextView)findViewById(R.id.tv_profesor);

        mStartDate = (TextView)findViewById(R.id.tv_fecha_inicio);
        mSchedule = (TextView)findViewById(R.id.tv_horario);

        searchButton = (Button)findViewById(R.id.buttonSearch);
        searchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSearch:
                DoPOST mDoPost = new DoPOST(this, mNameSearch.getText().toString());
                mDoPost.execute("");
                searchButton.setEnabled(false);
                break;
        }
    }

    public class DoPOST extends AsyncTask<String, Void, Boolean> {

        Context mContext;
        String workshopSearch = "";

        //Result
        String wName, wCredits, wTeacher, wSchedule, wDate;
        Exception ex = null;

        DoPOST(Context context, String stringToSearch){
            mContext = context;
            workshopSearch = stringToSearch;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("opcion","wToSearch"));
                nameValuePairs.add(new BasicNameValuePair("wName", workshopSearch));

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

                System.out.println(result);

                wName = jsonObject.getString("Nombre");

                wCredits = jsonObject.getString("Creditos");
                wTeacher = jsonObject.getString("Profesor");

                wDate = jsonObject.getString("FechaInicio");
                wSchedule = jsonObject.getString("Horario");

            } catch (Exception e) {
                Log.e("UEES Workshop", e.getMessage());
                ex = e;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            mName.setText(wName);
            mCredits.setText(wCredits);
            mTeacher.setText(wTeacher);
            mStartDate.setText(wDate);
            mSchedule.setText(wSchedule);

            searchButton.setEnabled(true);

            if(ex != null){
                Toast.makeText(mContext, ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}