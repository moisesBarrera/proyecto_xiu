package com.example.eisra.sgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class Tareas extends AppCompatActivity {
    RelativeLayout generarEquipos;
    Switch equipos;
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

    }

    public void CrearTarea(View v){
        Intent i= new Intent(this, MenuGrupos.class);
        startActivity(i);
        finish();
    }
}
