package com.example.eisra.sgg;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eisra.sgg.Modelos.Alumno;
import com.example.eisra.sgg.Modelos.tarea;
import com.example.eisra.sgg.Servicios.peticiones;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class detalle_tarea extends AppCompatActivity {

    public static List<String> lista = new ArrayList<String>();
    public List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    public tarea Tarea = null;
    public String[] h ={""};
    ArrayAdapter<String> listaAdap;
    ListView list;
    String id;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);
        Bundle parametros = getIntent().getExtras();
        id = parametros.getString("id");
        System.out.println(id);
        titulo = (TextView) findViewById(R.id.Titulo_Tarea);
    }

    @Override
    public void onResume(){
        super.onResume();
        new ConsultarDatosTarea().execute("http://"+MainActivity.ip+"/getarea?id="+ id);
    }



    private class ConsultarDatosTarea extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            try {
                Gson gson = new Gson();
                JSONObject obj = new JSONObject(result);
                JSONArray j = new JSONArray(obj.getString("Informacion").toString());
                Tarea = gson.fromJson(j.getString(0).toString(), tarea.class);
                titulo.setText(Tarea.getNombre());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private class ConsultarDatosAlumnos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            try {
                Gson gson = new Gson();
                JSONObject obj = new JSONObject(result);
                JSONArray j = new JSONArray(obj.getString("Informacion").toString());
                for(int i=0;i<j.length();i++) {
                    Alumno tare = gson.fromJson(j.getString(i).toString(), Alumno.class);
                    String a = tare.getNombre();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


    private class ConsultarDatosEquipos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            try {
                Gson gson = new Gson();
                JSONObject obj = new JSONObject(result);
                JSONArray j = new JSONArray(obj.getString("Informacion").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            conn.getResponseMessage();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
