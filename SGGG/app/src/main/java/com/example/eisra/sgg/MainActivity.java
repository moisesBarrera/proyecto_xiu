package com.example.eisra.sgg;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static String ip="192.168.1.70:5000";
    public static int idUsuario=0;
    TextView usuario,contraseña;
    Button boton;

    private TextView registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (TextView)findViewById(R.id.editText);
        contraseña= (TextView)findViewById(R.id.editText2);
        registrar = (TextView) findViewById(R.id.textView13);
        boton = (Button)findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ConsultarDatos().execute("http://"+ip+"/login?usuario=" + usuario.getText().toString().trim()+"&contrasena="+contraseña.getText().toString().trim()+"");
                System.out.println("http://"+ip+"/login?usuario=" + usuario.getText().toString().trim()+"&contrasena="+contraseña.getText().toString().trim()+"");
            }});

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
            System.out.println(result);
            if (result.contains("true")) {
                idUsuario=Integer.parseInt(result.substring(45,46));
                iniciar();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Usuario o Contraseña inexistentes", Toast.LENGTH_LONG).show();

            }


        }

    }
    public void iniciar()
    {
        Intent i= new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
    public void abrirRegistro(View v)
    {
        Intent i= new Intent(this, Registro.class);
        startActivity(i);
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
