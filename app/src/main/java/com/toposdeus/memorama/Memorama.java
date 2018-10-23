package com.toposdeus.memorama;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class Memorama extends AppCompatActivity implements View.OnClickListener {
    int[][][] matriz =
            //filas
            {{{1, 2, 2, 3, 2, 4, 5, 4, 5,  2, 2, 3, 2, 4, 5, 4, 5, 6,  1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {2, 3, 2, 4, 5, 4, 5, 6, 6, 5, 6, 7, 8, 9, 10, 6, 2, 4, 6, 7, 7},
                    {2, 2, 3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6}},
                    //columnas
                    {{2, 2, 3, 3, 5, 3, 3, 4, 4, 2, 3, 3, 5, 3, 3, 4, 4, 4,  2, 2, 2, 2, 2, 2, 2, 2, 2},
                            {3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6},
                            {4, 4, 4, 4, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 7, 7, 8, 8},
                            {4, 4, 4, 4, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 7, 7, 8, 8}}};

    // int[][] matriz2 = {{2, 2, 3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {2, 2, 3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
    int imagenest[] = new int[]{R.drawable.uno1, R.drawable.dos2, R.drawable.tres3, R.drawable.cuatro4,
            R.drawable.cinco5, R.drawable.seis6, R.drawable.siete7, R.drawable.ocho8, R.drawable.nueve9, R.drawable.unoin, R.drawable.dosin, R.drawable.tazas,
            R.drawable.tresin, R.drawable.tacos, R.drawable.cuatroin, R.drawable.focos, R.drawable.cincoin,
            R.drawable.fantasmas, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,
            R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback, R.drawable.btnagregarback,};
    int imagenesingles[] = new int[]{R.drawable.uno1, R.drawable.unoin, R.drawable.dos2, R.drawable.dosin, R.drawable.tres3,
            R.drawable.tresin, R.drawable.cuatro4, R.drawable.cuatroin, R.drawable.cinco5, R.drawable.cincoin, R.drawable.seis6, R.drawable.seisin,
            R.drawable.siete7, R.drawable.sietein, R.drawable.ocho8, R.drawable.ochoin, R.drawable.nueve9, R.drawable.nuevein};

    int mlargo, mancho;
    LinearLayout layout;
    TextView txtpunt;
    ImageView botontemp;
    int[] botonesimg;
    boolean[] contestados;
    int ganador = 0;
    ImageView[] botones;
    int intentos = 0, id, carta1 = 0, carta2 = 0;
    Animation vibrar, mover, animstar, animstarnull, animmarco, animcarta1, animcarta2, girari, seacaba;
    // String cartas[] = new String[]{"hola", "adios", "viernes", "jueves", "helado", "topo", "hola", "adios", "viernes", "jueves", "helado", "topo"};
    Button atras;
    //boton atras
    int dificultad;
    DisplayMetrics metrics;
    int anchobtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_memorama);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        txtpunt = findViewById(R.id.txtpunt);
        layout = findViewById(R.id.layoutmemo);
        dificultad = getIntent().getExtras().getInt("dificultad");
        id = getIntent().getExtras().getInt("id");

        vibrar = AnimationUtils.loadAnimation(Memorama.this, R.anim.vibrarbotones);
        mover = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandar);
        animstar = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandarstar);
        animstarnull = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandarstarnull);
        animmarco = AnimationUtils.loadAnimation(Memorama.this, R.anim.animacionmarco);
        animcarta1 = AnimationUtils.loadAnimation(Memorama.this, R.anim.carta1);
        animcarta2 = AnimationUtils.loadAnimation(Memorama.this, R.anim.carta2);
        seacaba = AnimationUtils.loadAnimation(Memorama.this, R.anim.seterminatiempo);
        girari = AnimationUtils.loadAnimation(Memorama.this, R.anim.girarinfinito);

        mlargo = matriz[0][dificultad][id];
        mancho = matriz[1][dificultad][id];
        metrics = getResources().getDisplayMetrics();
        anchobtn = ((metrics.widthPixels / mancho + 1));
        botonesimg = new int[mancho * mlargo];
        botones = new ImageView[mancho * mlargo];
        contestados = new boolean[mancho * mlargo];
        atras = findViewById(R.id.atras);
        mostrarimagenes(mancho * mlargo);
        switch (dificultad) {

            case 0:
                break;
            case 1:
                break;
            case 2:
                txtpunt.setText("   00:00");

                break;
        }

        crearbotones();
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
            botones[i].setBackground(resize(getResources().getDrawable(botonesimg[i]), anchobtn));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < botones.length; i++) {
                    botones[i].setEnabled(true);
                    botones[i].startAnimation(animcarta1);
                    botones[i].setBackground(resize(getResources().getDrawable(R.drawable.fondomemo), anchobtn));

                    switch (dificultad) {
                        case 0:
                            break;
                        case 1:
                            porintentos();
                            break;
                        case 2:
                            portiempo();
                            break;
                        case 3:
                            break;
                    }
                }
            }
        }, mancho * 1000);

    }

    private void porintentos() {
        txtpunt.setText("5");

    }

    private void portiempo() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                int minutos = (int) ((millisUntilFinished / 1000) / 60);
                int segundos = (int) ((millisUntilFinished / 1000) - (minutos * 60));
                if (segundos < 10) {
                    txtpunt.setText("   0" + minutos + ":" + "0" + segundos);
                } else {
                    txtpunt.setText("   0" + minutos + ":" + "" + segundos);
                }
                if (segundos < 10 && minutos == 0) {
                    txtpunt.startAnimation(seacaba);
                    txtpunt.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }

            }

            public void onFinish() {

                Toast.makeText(Memorama.this, "perdiste", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Memorama.this, Nivel.class);
        i.putExtra("dificultad", dificultad);
        i.putExtra("pagina", pagina());
        startActivity(i);
        finish();

    }


    public void crearbotones() {
        // int ancho = metrics.widthPixels / (100) * 20;


        for (int i = 0; i <= (mlargo); i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            LinearLayout.LayoutParams lll = (LinearLayout.LayoutParams) row.getLayoutParams();
            lll.gravity = Gravity.CENTER;
            lll.setMargins(0, metrics.widthPixels / (mancho * 12), 0, 0);
            row.setLayoutParams(lll);

            for (int j = 0; j < mancho; j++) {

                if ((j + (i * mancho)) <= (mancho * mlargo) - 1) {
                    final ImageView btnTag = new ImageView(this);
                    btnTag.setLayoutParams(new LinearLayout.LayoutParams(anchobtn, anchobtn));
                    LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
                    lllp.gravity = Gravity.CENTER;
                    lllp.setMargins(metrics.widthPixels / (mancho * 25), 0, metrics.widthPixels / (mancho * 25), 0);
                    btnTag.setLayoutParams(lllp);
                    btnTag.getLayoutParams().height = metrics.widthPixels / (mancho + 1);
                    btnTag.getLayoutParams().width = metrics.widthPixels / (mancho + 1);
                    btnTag.setId(j + (i * mancho));

                    btnTag.startAnimation(animcarta1);


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
                                btnTag.startAnimation(animcarta1);
                                btnTag.setBackground(resize(getResources().getDrawable(botonesimg[finalJ + (finalI * mancho)]), anchobtn));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                carta1 = botonesimg[finalJ + (finalI * mancho)];
                                botontemp = btnTag;
                                btnTag.setEnabled(false);
                            } else {
                                for (int i = 0; i < botones.length; i++) {
                                    botones[i].setEnabled(false);
                                }
                                new Hilo().execute();
                                btnTag.setBackground(resize(getResources().getDrawable(botonesimg[finalJ + (finalI * mancho)]), anchobtn));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                // btnTag.setText(cartas[finalJ + (finalI * mancho)]);
                                carta2 = botonesimg[finalJ + (finalI * mancho)];
                                intentos++;
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
                                    txtpunt.setText("Pares " + ganador);
                                    carta1 = 0;
                                    carta2 = 0;

                                } else {
                                    botontemp.startAnimation(vibrar);
                                    btnTag.startAnimation(vibrar);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            botontemp.setBackground(resize(getResources().getDrawable(R.drawable.fondomemo), anchobtn));
                                            btnTag.setBackground(resize(getResources().getDrawable(R.drawable.fondomemo), anchobtn));
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


    private void mostrarimagenes(int total) {
        //llenar arreglo de imagenes
        if (id < 8) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenest[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenest[i - (total / 2)];
                i++;
            }

        } else {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesingles[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesingles[i - (total / 2)];
                i++;
            }

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }


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
                SharedPreferences sharedPref;
                sharedPref = Memorama.this.getSharedPreferences(
                        "record", Context.MODE_PRIVATE);

                ColorDrawable dialogColor = new ColorDrawable(Color.GRAY);
                dialogColor.setAlpha(0);
                final AlertDialog.Builder builder = new AlertDialog.Builder(Memorama.this);
                LayoutInflater inflater = getLayoutInflater();
                View vi = inflater.inflate(R.layout.layout_victoria, null);
                builder.setView(vi);
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(dialogColor);
                TextView txtintentos = vi.findViewById(R.id.txtintentos);
                txtintentos.setText("Intentos: " + intentos);
                ImageView titulo = vi.findViewById(R.id.imagentitulo);
                ImageView star1 = vi.findViewById(R.id.star1);
                ImageView star2 = vi.findViewById(R.id.star2);
                ImageView star3 = vi.findViewById(R.id.star3);
                star1.startAnimation(animstar);
                ImageView girar = vi.findViewById(R.id.backwin);
                girar.startAnimation(girari);
                Button botonretry = vi.findViewById(R.id.botonretry);
                titulo.setBackground(getResources().getDrawable(R.drawable.titulovic1));
                LinearLayout marco = vi.findViewById(R.id.layoutmarco);
                marco.startAnimation(animmarco);
                titulo.startAnimation(animstarnull);


                SharedPreferences.Editor editor = sharedPref.edit();
                //verificamos si la las estrellas obtenidas son mas a las ya registradas que por default son cero
                int numestrellas = estrellas((mancho * mlargo) / 2, titulo, star2, star3, botonretry, 2);
                if (numestrellas > sharedPref.getInt(dificultad + "record" + id, 0)) {
                    editor.putInt(dificultad + "record" + id, numestrellas);
                }
                editor.putBoolean(dificultad + "contestada" + (id + 1), true);
                editor.commit();
                Button botonok = vi.findViewById(R.id.botonok);
                botonok.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Memorama.this, Nivel.class);
                                intent.putExtra("dificultad", dificultad);
                                intent.putExtra("pagina", pagina());
                                startActivity(intent);
                                finish();
                            }
                        }
                );

                botonretry.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Memorama.this, Memorama.class);
                                intent.putExtra("dificultad", dificultad);
                                intent.putExtra("id", id);
                                startActivity(intent);
                                finish();
                            }
                        }
                );

                dialog.show();


            }

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(Memorama.this, Nivel.class);
            intent.putExtra("dificultad", dificultad);
            intent.putExtra("pagina", pagina());
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private int pagina() {
        int pag = 0;
        if (id > 8) {
            pag = 1;
            if (id > 17) {
                pag = 2;
            }
        }
        return pag;
    }

    private Drawable resize(Drawable image, int size) {
        Bitmap b = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, size, size, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }


    private int estrellas(int pares, ImageView titulo, ImageView star2, ImageView star3, Button botonretry, int segundos) {
        int estrella = 1;
        int[] titulos = {R.drawable.titulovic1, R.drawable.titulovic2, R.drawable.titulovic3};

        switch (dificultad) {
            case 0:
                if (intentos - pares < 10) {
                    estrella++;
                    star2.startAnimation(animstar);
                } else {
                    star2.setBackground(getResources().getDrawable(R.drawable.starnull));
                    star2.startAnimation(animstarnull);
                }
                if (intentos - pares < 5) {
                    estrella++;
                    botonretry.setVisibility(View.GONE);
                    star3.startAnimation(animstar);
                } else {
                    star3.setBackground(getResources().getDrawable(R.drawable.starnull));
                    star3.startAnimation(animstarnull);
                }
                break;
            case 1:
                if (intentos - pares < 7) {
                    estrella++;
                    star2.startAnimation(animstar);
                } else {
                    star2.setBackground(getResources().getDrawable(R.drawable.starnull));
                    star2.startAnimation(animstarnull);
                }
                if (intentos - pares < 4) {
                    estrella++;
                    botonretry.setVisibility(View.GONE);
                    star3.startAnimation(animstar);
                } else {
                    star3.setBackground(getResources().getDrawable(R.drawable.starnull));
                    star3.startAnimation(animstarnull);
                }
                break;
            case 2:
                if (intentos - pares < 5) {
                    estrella++;
                    star2.startAnimation(animstar);
                } else {
                    star2.setBackground(getResources().getDrawable(R.drawable.starnull));
                    star2.startAnimation(animstarnull);
                }
                if (intentos - pares < 2) {
                    estrella++;
                    botonretry.setVisibility(View.GONE);
                    star3.startAnimation(animstar);
                } else {
                    star3.setBackground(getResources().getDrawable(R.drawable.starnull));
                    star3.startAnimation(animstarnull);
                }
                break;
        }


        titulo.setBackground(getResources().getDrawable(titulos[estrella - 1]));
        return estrella;
    }

}