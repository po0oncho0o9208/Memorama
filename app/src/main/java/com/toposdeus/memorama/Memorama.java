package com.toposdeus.memorama;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

public class Memorama extends AppCompatActivity implements View.OnClickListener {
    int imagenest[] = new int[]{R.drawable.avernom, R.drawable.brozom, R.drawable.camilasodim, R.drawable.cantinflasm,
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
    Chronometer crono;
    ImageView botontemp;
    int[] botonesimg;
    boolean[] contestados;
    int ganador = 0;
    ImageView[] botones;
    TextView txtpunt, txtintent;
    int intentos = 0;
    int carta1 = 0;
    int carta2 = 0;
    Animation vibrar, presentacion, mover;
    // String cartas[] = new String[]{"hola", "adios", "viernes", "jueves", "helado", "topo", "hola", "adios", "viernes", "jueves", "helado", "topo"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_memorama);
        txtpunt = findViewById(R.id.txtpunt);
        txtintent = findViewById(R.id.txtintent);
        layout = findViewById(R.id.layoutmemo);
        crono = findViewById(R.id.crono);
        String cadena = getIntent().getExtras().getString("cadena");
        vibrar = AnimationUtils.loadAnimation(Memorama.this, R.anim.vibrarbotones);
        presentacion = AnimationUtils.loadAnimation(Memorama.this, R.anim.entradaquiz);
        mover = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandar);
        txtpunt.setText("Puntuacion 0");
        txtintent.setText("Intentos 0");
        mlargo = Integer.parseInt(String.valueOf(cadena.charAt(0)));
        mancho = Integer.parseInt(String.valueOf(cadena.charAt(2)));
        botonesimg = new int[mancho * mlargo];
        botones = new ImageView[mancho * mlargo];
        contestados = new boolean[mancho * mlargo];
        mostrarimagenes(mancho * mlargo);
        crearbotones();
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
            botones[i].setBackground(getResources().getDrawable(botonesimg[i]));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < botones.length; i++) {
                    botones[i].setEnabled(true);
                    botones[i].startAnimation(presentacion);
                    botones[i].setBackground(getResources().getDrawable(R.drawable.fondomemo));
                    crono.setBase(SystemClock.elapsedRealtime());
                    crono.start();
                }
            }
        }, mancho * 500);

    }

    @Override
    public void onClick(View v) {

    }


    public void crearbotones() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        // int ancho = metrics.widthPixels / (100) * 20;

        int anchobtn = ((metrics.widthPixels / (mancho)) / 100) * 99;

        for (int i = 0; i <= (mlargo); i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            LinearLayout.LayoutParams lll = (LinearLayout.LayoutParams) row.getLayoutParams();
            lll.gravity = Gravity.CENTER;
            lll.setMargins(0, (metrics.widthPixels / mancho) / 5, 0, 0);
            row.setLayoutParams(lll);

            for (int j = 0; j < mancho; j++) {

                if ((j + (i * mancho)) <= (mancho * mlargo) - 1) {
                    final ImageView btnTag = new ImageView(this);
                    btnTag.setLayoutParams(new LinearLayout.LayoutParams(anchobtn, anchobtn));
                    LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
                    lllp.gravity = Gravity.CENTER;
                    lllp.setMargins((metrics.widthPixels / mancho) / 10, 0, (metrics.widthPixels / mancho) / 10, 0);
                    btnTag.setLayoutParams(lllp);
                    btnTag.setId(j + (i * mancho));
                    botones[j + (i * mancho)] = btnTag;
                    // StateListDrawable states = new StateListDrawable();
                    //  int o = j + (i * 3);
                    //  states.addState(new int[]{android.R.attr.state_enabled}, drawbg(getResources().getDrawable(R.drawable.fondomemo), 0));
                    //  states.addState(new int[]{android.R.attr.state_pressed}, drawbg(getResources().getDrawable(botonesimg[j + (i * mancho)]), 0));
                    // states.addState(new int[]{android.R.attr.state_enabled}, drawbg(getResources().obtainTypedArray(
                    //      imagenes[nivel]).getDrawable(j + (i * 3)), j + (i * 3), 0));
                    //states.addState(new int[]{android.R.attr.state_enabled}, drawbg(resizeImagen(this, getResources().obtainTypedArray(
                    //  imagenes[nivel]).getResourceId(j + (i * 3), 1), ancho, ancho), j + (i * 3), 0));
                    //btnTag.setBackground(states);
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
                            if (carta1 == 0) {

                                btnTag.setBackground(getResources().getDrawable(botonesimg[finalJ + (finalI * mancho)]
                                ));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                carta1 = botonesimg[finalJ + (finalI * mancho)];
                                botontemp = btnTag;
                                btnTag.setEnabled(false);
                            } else {
                                for (int i = 0; i < botones.length; i++) {
                                    botones[i].setEnabled(false);
                                }
                                new Hilo().execute();
                                btnTag.setBackground(getResources().getDrawable(botonesimg[finalJ + (finalI * mancho)]));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                // btnTag.setText(cartas[finalJ + (finalI * mancho)]);
                                carta2 = botonesimg[finalJ + (finalI * mancho)];
                                intentos++;
                                txtintent.setText("Intentos " + intentos);
                                if (carta1 == carta2) {
                                    contestados[btnTag.getId()] = true;
                                    contestados[botontemp.getId()] = true;
                                    for (int i = 0; i < botones.length; i++) {
                                        if (!contestados[i]) {
                                            botones[i].setEnabled(true);
                                        }
                                    }
                                    botontemp.startAnimation(mover);
                                    btnTag.startAnimation(mover);
                                    ganador++;
                                    txtpunt.setText("Puntuacion " + ganador);
                                    carta1 = 0;
                                    carta2 = 0;

                                } else {
                                    botontemp.startAnimation(vibrar);
                                    btnTag.startAnimation(vibrar);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            botontemp.setBackground(getResources().getDrawable(R.drawable.fondomemo));
                                            btnTag.setBackground(getResources().getDrawable(R.drawable.fondomemo));
                                            for (int i = 0; i < botones.length; i++) {
                                                if (!contestados[i]) {
                                                    botones[i].setEnabled(true);
                                                }
                                            }
                                        }
                                    }, 700);
                                    carta1 = 0;
                                    carta2 = 0;
                                    // btnTag.setBackground(getResources().getDrawable(R.drawable.btnagregarback));
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
            botonesimg[i] = imagenest[i];
            i++;

        }
        for (int i = (total / 2); i < total; ) {
            botonesimg[i] = imagenest[i - (total / 2)];
            i++;
        }
        for (int i = 0; i < total; i++) {
            int index = (int) (Math.random() * total);
            int btntemp = botonesimg[i];
            botonesimg[i] = botonesimg[index];
            botonesimg[index] = btntemp;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo, menu);

        return super.onCreateOptionsMenu(menu);
    }

    class Hilo extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if (ganador == botones.length / 2) {
                crono.stop();

                int tiempo = ((int) (SystemClock.elapsedRealtime() - crono.getBase())) / 1000;

                int minutos = tiempo / 60;
                int segundos = tiempo - (minutos * 60);


                Toast.makeText(Memorama.this, "Tiempo: " + minutos + ":" + segundos,
                        Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Memorama.this, Menu.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2500);
            }
        }
    }
}