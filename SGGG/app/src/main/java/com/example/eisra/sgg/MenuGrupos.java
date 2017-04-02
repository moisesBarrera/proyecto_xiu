package com.example.eisra.sgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuGrupos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grupos);
    }
    public void aaaa(View v)
    {
        Intent hola = new Intent(this,TodosAlumnos.class);
        startActivity(hola);
    }
    public void VistaTareas(View v){
        Intent i= new Intent(this, Tareas.class);
        startActivity(i);
    }


}
