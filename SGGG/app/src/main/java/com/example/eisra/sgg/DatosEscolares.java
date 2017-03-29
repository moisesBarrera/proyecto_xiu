package com.example.eisra.sgg;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eisra.sgg.Modelos.DatosEscuela;
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
import java.util.List;
import java.util.ArrayList;


public class DatosEscolares extends AppCompatActivity {

    private TextView nombre,cct,sector,zona,direccion;
    private Spinner turno;
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_escolares);
        nombre=(TextView) findViewById(R.id.editText3);
        cct=(TextView) findViewById(R.id.editText4);
        sector=(TextView) findViewById(R.id.editText7);
        zona=(TextView) findViewById(R.id.editText8);
        direccion=(TextView) findViewById(R.id.editText9);
        turno=(Spinner) findViewById(R.id.spTurno);
        boton =(Button) findViewById(R.id.button);
        new ConsultarDatos().execute("http://"+MainActivity.ip+"/getEscuela?id="+MainActivity.idUsuario);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CargarDatos().execute("http://"+MainActivity.ip+"/updateEscuela?nombre=" + nombre.getText().toString().trim()+"&turno="+turno.getSelectedItemPosition()+"&cct="+cct.getText().toString().trim()+"&sector="+sector.getText().toString().trim()+"&zonaEscolar="+zona.getText().toString().trim()+"&ubicacion="+direccion.getText().toString().trim()+"&usuarios_idusuarios="+MainActivity.idUsuario);
            }
        });
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

            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();

        }
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
                DatosEscuela frutas = gson.fromJson(j.getString(0).toString(), DatosEscuela.class);
                nombre.setText(frutas.getNombre());
                cct.setText(frutas.getCct());
                direccion.setText(frutas.getUbicacion());
                sector.setText(frutas.getSector());
                zona.setText(frutas.getZonaEscolar());
                turno.setSelection(frutas.getTurno());


            } catch (Exception e) {
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
