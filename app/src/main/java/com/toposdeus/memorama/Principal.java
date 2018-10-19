package com.toposdeus.memorama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Principal extends AppCompatActivity {


    ImageView play,ajustes,trofeos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        play = findViewById(R.id.btnplay);
        ajustes = findViewById(R.id.btnajustes);
        trofeos = findViewById(R.id.btntrofeos);

    }

    public void play(View view) {
        Intent intent1 = new Intent(this, Niveles.class);
        startActivity(intent1);
        // Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetondigital/"));
        // startActivity(intent1);
    }

}
