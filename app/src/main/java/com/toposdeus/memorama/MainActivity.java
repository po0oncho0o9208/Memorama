package com.toposdeus.memorama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends  AppCompatActivity implements View.OnClickListener {
    GridView grilla;
    InterstitialAd mInterstitialAd;
    TextView txtest, txtgriditem;
    SharedPreferences sharedPref;
    Button btnatras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grilla = (GridView) findViewById(R.id.grilla);
        txtest = findViewById(R.id.txtestrella);
        txtgriditem = findViewById(R.id.txttitulo);
        btnatras = findViewById(R.id.atras);
        sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE);


        int contador = 0;

        for (int n = 0; n < 4; n++) {
            for (int i = 0; i < 27; i++) {
                contador += sharedPref.getInt(n + "record" + i, 0);
            }
        }
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/birdyame.ttf");

        txtest.setText(contador + " X ");

        txtest.setTypeface(font);

        ///crear estructuira de mis datos

        ArrayList<Datos> datos = new ArrayList<Datos>();

        datos.add(new Datos(1, R.drawable.uno1, "UNO"));
        datos.add(new Datos(2, R.drawable.dos2, "DOS"));

        datos.add(new Datos(3, R.drawable.tres3, "TRES"));
        datos.add(new Datos(4, R.drawable.cuatro4, "CUATRO"));

        datos.add(new Datos(5, R.drawable.cinco5, "CINCO"));
        datos.add(new Datos(6, R.drawable.seis6, "SEIS"));

        datos.add(new Datos(7, R.drawable.siete7, "SIETE"));
        datos.add(new Datos(8, R.drawable.ocho8, "OCHO"));

        datos.add(new Datos(9, R.drawable.nueve9, "NUEVE"));
        datos.add(new Datos(10, R.drawable.unoin, "ONE"));

        datos.add(new Datos(11, R.drawable.dosin, "TWO"));
        datos.add(new Datos(12, R.drawable.tresin, "THREE"));

        datos.add(new Datos(13, R.drawable.cuatroin, "FOUR"));
        datos.add(new Datos(14, R.drawable.cinco5, "FIVE"));

        datos.add(new Datos(15, R.drawable.seisin, "SIX"));
        datos.add(new Datos(16, R.drawable.sietein, "SEVEN"));

        datos.add(new Datos(17, R.drawable.ochoin, "EIGHT"));
        datos.add(new Datos(18, R.drawable.nuevein, "NINE"));

        datos.add(new Datos(19, R.drawable.uva, " UVAS"));
        datos.add(new Datos(20, R.drawable.oso, " 1 OSO"));

        datos.add(new Datos(21, R.drawable.dulces, "2 DULCES"));
        datos.add(new Datos(22, R.drawable.tazas, "2 TAZAS"));

        datos.add(new Datos(23, R.drawable.tambores, "3 TAMBORES"));
        datos.add(new Datos(24, R.drawable.tacos, "3 TACOS"));

        datos.add(new Datos(25, R.drawable.conejo, "4 CONEJOS"));
        datos.add(new Datos(26, R.drawable.focos, "4 FOCOS"));

        datos.add(new Datos(27, R.drawable.corbatas, "5 CORBATAS"));
        datos.add(new Datos(28, R.drawable.fantasmas, "5 FANTASMAS"));

        datos.add(new Datos(29, R.drawable.sombreros, "6 SOMBREROS"));
        datos.add(new Datos(30, R.drawable.soles, "6 SOLES"));

        datos.add(new Datos(31, R.drawable.sillas, "7 SILLAS"));
        datos.add(new Datos(32, R.drawable.sandias, "7 SANDIAS"));

        datos.add(new Datos(33, R.drawable.estrellas, "8 ESTRELLAS"));
        datos.add(new Datos(34, R.drawable.ojos, "8 OJOS"));

        datos.add(new Datos(35, R.drawable.naranjas, "9 NARANJAS"));
        datos.add(new Datos(36, R.drawable.nubes, "9 NUBES"));

        datos.add(new Datos(37, R.drawable.atras4, "CAMPEON"));


        Adaptador miadaptador = new Adaptador(getApplicationContext(), datos);
        grilla.setAdapter(miadaptador);


        grilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Datos obj = (Datos) parent.getItemAtPosition(position);
                    Intent paso = new Intent(getApplicationContext(), Detalle.class);
                    paso.putExtra("objeto", (Serializable) obj);

                    startActivity(paso);
                }

        });



    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(MainActivity.this, Principal.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Principal.class);
        startActivity(intent);
        finish();
    }


}
