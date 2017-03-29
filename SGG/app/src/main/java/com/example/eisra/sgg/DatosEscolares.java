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

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class DatosEscolares extends AppCompatActivity {

    private TextView nombre,cct,sector,zona,direccion;
    private Spinner turno;
    private Button boton;
public String ip="192.168.1.68";

    public class Escuela
    {
        private String nombre;
        private String cct;
        private String sector;
        private String zona;
        private String direccion;
        private int turno;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCct() {
            return cct;
        }

        public void setCct(String cct) {
            this.cct = cct;
        }

        public String getSector() {
            return sector;
        }

        public void setSector(String sector) {
            this.sector = sector;
        }

        public String getZona() {
            return zona;
        }

        public void setZona(String zona) {
            this.zona = zona;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public int getTurno() {
            return turno;
        }

        public void setTurno(int turno) {
            this.turno = turno;
        }
    }
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

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if(nombre.getText().toString().trim()==null && cct.getText().toString().trim()==null & zona.getText().toString().trim()==null && direccion.getText().toString().trim()==null)

               new CargarDatos().execute("http://"+ip+":5000/updateEscuela?nombre=" + nombre.getText().toString().trim()+"&turno="+turno.getSelectedItemPosition()+"&cct="+cct.getText().toString().trim()+"&sector="+sector.getText().toString().trim()+"&zonaEscolar="+zona.getText().toString().trim()+"&ubicacion="+direccion.getText().toString().trim()+"&usuarios_idusuarios="+MainActivity.idUsuario);
               // Toast.makeText(getApplicationContext(), turno.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }
        });
        new ConsultarDatos().execute("http://" + ip + ":5000/getEscuela?id=" + MainActivity.idUsuario);

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

            final Gson gson= new Gson();
            gson.fromJson(result,Escuela.class);

            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                nombre.setText(ja.getString(1));
                if(ja.getString(2)=="matutino")turno.setSelection(1);
                else turno.setSelection(2);
                direccion.setText(ja.getString(2));
                cct.setText(ja.getString(3));
                sector.setText(ja.getString(4));
                zona.setText(ja.getString(5));
                direccion.setText(ja.getString(6));
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
