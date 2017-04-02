package com.example.eisra.sgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuPadres extends AppCompatActivity {


    public String[] lista={"Ernesto Galeana Cortez","Mario Wilbert Barrera           "};
    ArrayAdapter<String> listaAdap;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_padres);
        listaAdap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        list = (ListView)findViewById(R.id.listapapu);
        list.setAdapter(listaAdap);
    }



    public void padres(View v)
    {
        Intent i = new Intent(this,PadresDeFamilia.class);
        startActivity(i);
    }
}
