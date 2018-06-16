package com.example.sujal.pocketmed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sujal on 6/3/2017.
 */
public class Information extends AppCompatActivity {
    Button fet;
    ListView lv;
    String medName[];
    String medDesc[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_main);
        fet = (Button)findViewById(R.id.fetch);
        lv = (ListView)findViewById(R.id.medList);
        fet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TabelAsyncTask().execute("http://192.168.137.188:8080/med/a.php");
            }
        });
    }
    public class TabelAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result ="";
            try{
                result = httprequest(params[0]);

            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("med");
                medName = new String[ja.length()];
                medDesc = new String[ja.length()];
                for(int i=0;i<ja.length();i++)
                {
                    medName[i] = ja.getJSONObject(i).getString("name");
                    medDesc[i] = ja.getJSONObject(i).getString("desc");
                }
                showinListView();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void showinListView() {
        InfoAdapter cA = new InfoAdapter(this, medName, medDesc);
        lv.setAdapter(cA);
    }

    public String httprequest(String url) throws IOException
    {
        OkHttpClient client = new OkHttpClient();
        Request request =  new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
