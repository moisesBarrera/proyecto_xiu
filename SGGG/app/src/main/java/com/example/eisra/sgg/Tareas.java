package com.example.eisra.sgg;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.eisra.sgg.Modelos.Alumno;
import com.example.eisra.sgg.Modelos.DatosEscuela;
import com.example.eisra.sgg.Modelos.DatosMaestro;
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

import static android.R.attr.max;

public class Tareas extends AppCompatActivity {
    RelativeLayout generarEquipos;
    Switch equipos;
    EditText NombreTarea;
    EditText numIntegrantes;
    int idTarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        generarEquipos =  (RelativeLayout) findViewById(R.id.View_GenerarEquipo);
        generarEquipos.setVisibility(View.INVISIBLE);
        equipos = (Switch) findViewById(R.id.switch_1);
        equipos.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    generarEquipos.setVisibility(View.VISIBLE);
                } else {
                    generarEquipos.setVisibility(View.INVISIBLE);
                }
            }
        });
        NombreTarea = (EditText) findViewById(R.id.txt_NomTarea);
        numIntegrantes = (EditText) findViewById(R.id.txt_NumInte);

    }

    public void CrearTarea(View v){
        String NameHomework = NombreTarea.getText().toString();
        boolean enEquipos = equipos.isChecked();
        Random rand = null;
        try{
            String sql = "http://"+peticiones.ip+"/crearTarea?nombre=" + NameHomework+"&grupo_idgrupo="+peticiones.idGrup+"&esEnEquipo="+enEquipos;
            new CargarDatosTarea().execute(sql);
            Toast.makeText(getApplicationContext(), "Tarea Crada", Toast.LENGTH_LONG).show();
           /* if(enEquipos){

            } else {
                for(int i=3;i<=9;i++){
                    new CargarDatos().execute("http://"+peticiones.ip+"/asignarIntegrantesATarea?tarea_idtarea=" + 3+"&Alumnos_idAlumnos="+i);
                    new CargarDatos().execute("http://"+peticiones.ip+"/asignarCalificacion?tarea_idtarea=" + 3+"&Alumnos_idAlumnos="+i+"&calificacion="+ rand.nextInt((6 - 10) + 1) + 6);
                }
            }*/
        } catch(Exception e){
            e.getMessage();
        }
        finish();
    }

    private class CargarDatosTarea extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                System.out.println(urls[0]);
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }

    private class CargarDatos extends AsyncTask<String, Void, String> {
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

            Gson gson = new Gson();
            try {
                JSONObject obj = new JSONObject(result);
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
