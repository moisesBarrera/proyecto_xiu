package com.example.eisra.sgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button grupos,padres,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);



        grupos = (Button) findViewById(R.id.button2);
        padres = (Button) findViewById(R.id.button2);
        info = (Button) findViewById(R.id.button2);

    }


    public void Grupos()
    {
        Intent i= new Intent(this, MenuGrupos.class);
        startActivity(i);
    }

    public void Padres()
    {
        Intent i= new Intent(this, Registro.class);
        startActivity(i);
    }

    public void Info()
    {
        Intent i= new Intent(this, Registro.class);
        startActivity(i);
    }



}
