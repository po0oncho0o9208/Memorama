package com.toposdeus.memorama;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Collections;

public class Memorama extends AppCompatActivity implements View.OnClickListener {
    int imagenes[] = new int[]{R.drawable.avernom, R.drawable.brozom, R.drawable.camilasodim, R.drawable.cantinflasm,
            R.drawable.capulinam, R.drawable.cardenasm, R.drawable.carmensalinasm, R.drawable.cepillinm, R.drawable.chabelom,
            R.drawable.chapom, R.drawable.cuahutemocm, R.drawable.chicharitom, R.drawable.compayitom, R.drawable.derbezm,
            R.drawable.danielbosognom, R.drawable.diegolunam, R.drawable.diegoriveram, R.drawable.donramonm, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,};
    int mlargo, mancho;
    LinearLayout layout;
    Button botontemp;
    int[] botones;
    String carta1 = null, carta2 = null;
    String cartas[] = new String[]{"hola", "adios", "viernes", "jueves", "helado", "topo", "hola", "adios", "viernes", "jueves", "helado", "topo"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_memorama);
        layout = findViewById(R.id.layoutmemo);

        mlargo = 3;
        mancho = 4;
        botones = new int[mancho * mlargo];
        mostrarimagenes(mancho * mlargo);
        crearbotones();


    }

    @Override
    public void onClick(View v) {

    }


    public void crearbotones() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        // int ancho = metrics.widthPixels / (100) * 20;

        int anchobtn = ((metrics.widthPixels / (mancho)) / 100) * 90;

        for (int i = 0; i <= (mlargo); i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            LinearLayout.LayoutParams lll = (LinearLayout.LayoutParams) row.getLayoutParams();
            lll.gravity = Gravity.CENTER;
            lll.setMargins(0, anchobtn / 5, 0, 0);
            row.setLayoutParams(lll);

            for (int j = 0; j < mancho; j++) {

                if ((j + (i * mancho)) <= (mancho * mlargo) - 1) {
                    final Button btnTag = new Button(this);
                    btnTag.setLayoutParams(new LinearLayout.LayoutParams(anchobtn, anchobtn));
                    LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
                    lllp.gravity = Gravity.CENTER;
                    lllp.setMargins((metrics.widthPixels / (100) * mancho), 0, (metrics.widthPixels / (100) * mancho), 0);
                    btnTag.setLayoutParams(lllp);
                    btnTag.setId(j + (i * mancho));
                    StateListDrawable states = new StateListDrawable();
                    //  int o = j + (i * 3);
                    states.addState(new int[]{android.R.attr.state_enabled}, drawbg(getResources().getDrawable(botones[j + (i * mancho)]), 0));
                    // states.addState(new int[]{android.R.attr.state_enabled}, drawbg(getResources().obtainTypedArray(
                    //      imagenes[nivel]).getDrawable(j + (i * 3)), j + (i * 3), 0));
                    //states.addState(new int[]{android.R.attr.state_enabled}, drawbg(resizeImagen(this, getResources().obtainTypedArray(
                    //  imagenes[nivel]).getResourceId(j + (i * 3), 1), ancho, ancho), j + (i * 3), 0));
                    btnTag.setBackground(states);
                    final int finalJ = j;
                    final int finalI = i;
                    btnTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // definirpregunta = finalJ + (finalI * 3);
                            //Animation mover = AnimationUtils.loadAnimation(Nivel.this, R.anim.mover);
                            //btnTag.startAnimation(mover);
                            //   Metodos.preferenciasonido(Nivel.this, R.raw.click);
                            //   Metodos.preferenciavibrar(Nivel.this, 50);
                            // Metodos.Guardarint(Nivel.this, definirpregunta, getString(R.string.quiz));
                            if (carta1 == null) {
                                btnTag.setBackground(getResources().getDrawable(R.drawable.btnagregarunenabled
                                ));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                btnTag.setText(cartas[finalJ + (finalI * mancho)]);
                                carta1 = cartas[finalJ + (finalI * mancho)];
                                botontemp = btnTag;
                                btnTag.setEnabled(false);
                            } else {
                                btnTag.setBackground(getResources().getDrawable(R.drawable.btnagregarunenabled));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                btnTag.setText(cartas[finalJ + (finalI * mancho)]);
                                carta2 = cartas[finalJ + (finalI * mancho)];


                                if (carta1 == carta2) {
                                    Toast.makeText(Memorama.this, "exito", Toast.LENGTH_LONG).show();
                                    btnTag.setEnabled(false);
                                    botontemp.setEnabled(false);
                                    carta1 = null;
                                    carta2 = null;
                                } else {
                                    Toast.makeText(Memorama.this, "fracaso", Toast.LENGTH_LONG).show();
                                    carta1 = null;
                                    botontemp.setBackground(getResources().getDrawable(R.drawable.btnagregarback));
                                    botontemp.setText("");
                                    botontemp.setEnabled(true);
                                    carta2 = null;
                                    btnTag.setBackground(getResources().getDrawable(R.drawable.btnagregarback));
                                    btnTag.setText("");
                                }
                            }
                        }
                    });

                    row.addView(btnTag);
                    row.setGravity(Gravity.CENTER);
                }
            }
            layout.addView(row);
        }
        layout.setGravity(Gravity.CENTER);

    }

    public Drawable drawbg(Drawable draw, int alfa) {


        Drawable marco = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, getApplicationContext().getTheme());
        Drawable[] drawarray = new Drawable[2];
        drawarray[0] = draw;
        if (false) {
            marco.setAlpha(140 + alfa);
        } else {
            marco.setAlpha(alfa);
        }
        drawarray[1] = marco;
        LayerDrawable layerdrawable = new LayerDrawable(drawarray);
        return layerdrawable;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intentds = new Intent(Memorama.this, Menu.class);
            startActivity(intentds);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarimagenes(int total) {

        for (int i = 0; i < total / 2; ) {
            botones[i] = imagenes[i];
            i++;

        }
        for (int i = (total / 2); i < total; ) {
            botones[i] = imagenes[i - (total / 2)];
            i++;
        }
    }


}