package com.example.eisra.sgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eisra.sgg.Servicios.peticiones;

public class MenuGrupos extends AppCompatActivity {

    TextView titulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grupos);
        titulos = (TextView) findViewById(R.id.titulo);

        titulos.setText("Grupo: "+Grupos.nombreGrupo);
    }
    public void aaaa(View v)
    {
        Intent hola = new Intent(this,TodosAlumnos.class);
        startActivity(hola);
    }
    public void VistaTareas(View v){
        Intent i= new Intent(this, MenuTarea.class);
        peticiones.idGrup = Grupos.idGrupo;
        startActivity(i);
    }
    public void asientos(View v){
        Intent i= new Intent(this, Acomodar.class);
        startActivity(i);
    }


}
