package com.example.eisra.sgg;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eisra.sgg.Modelos.Alumno;
import com.example.eisra.sgg.Modelos.DatosEscuela;
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
import java.util.StringTokenizer;

public class MenuTarea extends AppCompatActivity {


    public static List<String> lista = new ArrayList<String>();
    public List<tarea> listatareas = new ArrayList<tarea>();
    public String[] h ={""};
    ArrayAdapter<String> listaAdap;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tarea);
    }


    @Override
    public void onResume(){
        super.onResume();
        new ConsultarDatos().execute("http://"+MainActivity.ip+"/getTareaPorGrupo?id="+peticiones.idGrup);
        listaAdap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        list = (ListView)findViewById(R.id.Tareas);
        list.setAdapter(listaAdap);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuTarea.this, detalle_tarea.class);
                String d = listatareas.get(position).getIdtarea() + "";
                intent.putExtra("id", d);
                startActivity(intent);
            }
        });
    }

    public void VistaTareas(View v){
        Intent i= new Intent(this, Tareas.class);
        startActivity(i);
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
                listatareas = new ArrayList<tarea>();
                lista.clear();
                for(int i=0;i<j.length();i++) {
                    tarea tare = gson.fromJson(j.getString(i).toString(), tarea.class);
                    String a = tare.getNombre();
                    listatareas.add(tare);
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
