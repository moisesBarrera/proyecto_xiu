package com.example.eisra.sgg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.internal.bind.ArrayTypeAdapter;

public class Grupos extends AppCompatActivity {

    public String[] lista={"3°A","2°B"};
    ArrayAdapter<String> listaAdap;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        listaAdap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        list = (ListView)findViewById(R.id.listapapu);
        list.setAdapter(listaAdap);
    }
}
