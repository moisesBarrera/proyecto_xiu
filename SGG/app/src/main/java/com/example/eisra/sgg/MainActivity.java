package com.example.eisra.sgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = (TextView) findViewById(R.id.textView13);
    }

    public void abrirRegistro(View v)
    {
        Intent i= new Intent(this, Registro.class);
        startActivity(i);
    }
}
