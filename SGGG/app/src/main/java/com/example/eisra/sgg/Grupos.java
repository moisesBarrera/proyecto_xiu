package com.example.eisra.sgg;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.eisra.sgg.Modelos.Alumno;
import com.example.eisra.sgg.Modelos.Grupo;
import com.example.eisra.sgg.Modelos.tarea;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ArrayTypeAdapter;

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

public class Grupos extends AppCompatActivity {

    public static List<String> lista = new ArrayList<String>();
    public List<Grupo> listagrupos = new ArrayList<Grupo>();
    ArrayAdapter<String> listaAdap;
    ListView list;
    public static int idGrupo=0;
    public static String nombreGrupo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
    }

public void menu(View v)
{
    Intent i = new Intent(Grupos.this,CrearGrupo.class);
    startActivity(i);
}


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new ConsultarDatos().execute("http://"+MainActivity.ip+"/gruposByUser?usuarios_idusuarios="+MainActivity.idUsuario);
        lista.clear();
        listaAdap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        list = (ListView)findViewById(R.id.listapapu);
        list.setAdapter(listaAdap);
    }

    public void onResume(){
        super.onResume();

        new ConsultarDatos().execute("http://"+MainActivity.ip+"/gruposByUser?usuarios_idusuarios="+MainActivity.idUsuario);
        lista.clear();
        listaAdap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        list = (ListView)findViewById(R.id.listapapu);
        list.setAdapter(listaAdap);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Grupos.this, MenuGrupos.class);
                int d = listagrupos.get(position).getIdgrupo();
                idGrupo = d;
                nombreGrupo=listagrupos.get(position).getNombre();
                startActivity(intent);
            }
        });


    }

    private class ConsultarDatos extends AsyncTask<String, Void, String> {
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
                listagrupos = new ArrayList<Grupo>();
                lista.clear();
                for(int i=0;i<j.length();i++) {
                    Grupo grup = gson.fromJson(j.getString(i).toString(), Grupo.class);
                    String a = grup.getNombre();
                    listagrupos.add(grup);
                    lista.add(a);
                }
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
