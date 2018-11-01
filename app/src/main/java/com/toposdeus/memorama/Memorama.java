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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Memorama extends AppCompatActivity implements View.OnClickListener {
    int[][][] matriz =

            {{{1, 2, 2, 3, 2, 4, 5, 4, 5, 2, 2, 3, 2, 4, 5, 4, 5, 6, 2, 3, 2, 4, 5, 4, 5, 6, 6},
                    {1, 2, 2, 3, 2, 4, 5, 4, 5, 2, 2, 3, 2, 4, 5, 4, 5, 6, 2, 3, 2, 4, 5, 4, 5, 6, 6},
                    {1, 2, 2, 3, 2, 4, 5, 4, 5, 2, 2, 3, 2, 4, 5, 4, 5, 6, 2, 3, 2, 4, 5, 4, 5, 6, 6},
                    {1, 2, 2, 3, 2, 4, 5, 4, 5, 2, 2, 3, 2, 4, 5, 4, 5, 6, 2, 3, 2, 4, 5, 4, 5, 6, 6},
                    {3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6}},

                    //columnas
                    {{2, 2, 3, 3, 5, 3, 3, 4, 4, 2, 3, 3, 5, 3, 3, 4, 4, 4, 3, 3, 5, 3, 3, 4, 4, 4, 5},
                            {2, 2, 3, 3, 5, 3, 3, 4, 4, 2, 3, 3, 5, 3, 3, 4, 4, 4, 3, 3, 5, 3, 3, 4, 4, 4, 5},
                            {2, 2, 3, 3, 5, 3, 3, 4, 4, 2, 3, 3, 5, 3, 3, 4, 4, 4, 3, 3, 5, 3, 3, 4, 4, 4, 5},
                            {2, 2, 3, 3, 5, 3, 3, 4, 4, 2, 3, 3, 5, 3, 3, 4, 4, 4, 3, 3, 5, 3, 3, 4, 4, 4, 5},
                            {4, 4, 4, 4, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 7, 7, 8, 8}}};

    // int[][] matriz2 = {{2, 2, 3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {2, 2, 3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
    int imagenest[] = new int[]{R.drawable.uno1, R.drawable.dos2, R.drawable.tres3, R.drawable.cuatro4,
            R.drawable.cinco5, R.drawable.seis6, R.drawable.siete7, R.drawable.ocho8, R.drawable.nueve9, R.drawable.uva, R.drawable.dulces,
            R.drawable.tacos, R.drawable.sombreros, R.drawable.soles, R.drawable.sillas, R.drawable.nubes, R.drawable.naranjas, R.drawable.tazas,
            R.drawable.estrellas, R.drawable.corbatas, R.drawable.fantasmas};


    int imagenesnivel10[] = new int[]{R.drawable.unoin, R.drawable.uva};

    int imagenesnivel11[] = new int[]{R.drawable.unoin, R.drawable.dulces, R.drawable.dosin};

    int imagenesnivel12[] = new int[]{R.drawable.unoin, R.drawable.tacos, R.drawable.dosin, R.drawable.tresin, R.drawable.tambores};

    int imagenesnivel13[] = new int[]{R.drawable.unoin, R.drawable.conejo, R.drawable.dosin, R.drawable.tresin, R.drawable.cuatroin};

    int imagenesnivel14[] = new int[]{R.drawable.unoin, R.drawable.fantasmas, R.drawable.dosin, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin};

    int imagenesnivel15[] = new int[]{R.drawable.unoin, R.drawable.soles, R.drawable.dosin, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin, R.drawable.sombreros};

    int imagenesnivel16[] = new int[]{R.drawable.unoin, R.drawable.sillas, R.drawable.dosin, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin,
            R.drawable.sietein};

    int imagenesnivel17[] = new int[]{R.drawable.unoin, R.drawable.estrellas, R.drawable.dosin, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin,
            R.drawable.sietein, R.drawable.ochoin, R.drawable.ojos};

    int imagenesnivel18[] = new int[]{R.drawable.unoin, R.drawable.nubes, R.drawable.dosin, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin,
            R.drawable.sietein, R.drawable.ochoin, R.drawable.nuevein, R.drawable.naranjas, R.drawable.oso};

    int imagenesnivel19[] = new int[]{R.drawable.uno1, R.drawable.oso, R.drawable.uva};

    int imagenesnivel20[] = new int[]{R.drawable.uno1, R.drawable.dulces, R.drawable.tazas, R.drawable.dos2, R.drawable.dosin};

    int imagenesnivel21[] = new int[]{R.drawable.uno1, R.drawable.tacos, R.drawable.tambores, R.drawable.dos2, R.drawable.tres3};

    int imagenesnivel22[] = new int[]{R.drawable.uno1, R.drawable.focos, R.drawable.conejo, R.drawable.dos2, R.drawable.tres3, R.drawable.cuatro4};

    int imagenesnivel23[] = new int[]{R.drawable.uno1, R.drawable.corbatas, R.drawable.fantasmas, R.drawable.tres3, R.drawable.cuatro4, R.drawable.cinco5, R.drawable.dos2, R.drawable.cincoin};

    int imagenesnivel24[] = new int[]{R.drawable.uno1, R.drawable.sombreros, R.drawable.soles, R.drawable.tres3, R.drawable.cuatro4, R.drawable.cinco5, R.drawable.seis6,
            R.drawable.seisin};

    int imagenesnivel25[] = new int[]{R.drawable.uno1, R.drawable.sillas, R.drawable.dos2, R.drawable.tres3, R.drawable.cuatro4, R.drawable.cinco5, R.drawable.seis6,
            R.drawable.siete7, R.drawable.sandias, R.drawable.sietein};

    int imagenesnivel26[] = new int[]{R.drawable.uno1, R.drawable.ojos, R.drawable.dos2, R.drawable.tres3, R.drawable.cuatro4, R.drawable.cinco5, R.drawable.seis6,
            R.drawable.siete7, R.drawable.ocho8, R.drawable.ochoin, R.drawable.estrellas, R.drawable.oso, R.drawable.fantasmas};

    int imagenesnivel27[] = new int[]{R.drawable.uno1, R.drawable.ojos, R.drawable.dos2, R.drawable.tres3, R.drawable.cuatro4, R.drawable.cinco5, R.drawable.seis6,
            R.drawable.siete7, R.drawable.ocho8, R.drawable.fantasmas, R.drawable.estrellas, R.drawable.oso, R.drawable.nueve9, R.drawable.nuevein, R.drawable.naranjas};

    //intentos para cad nivel en especifico
    int intentospermitidos[] = {1, 2, 3, 3, 4, 5, 7, 7, 9,
            2, 2, 3, 3, 4, 4, 6, 6, 8,
            2, 2, 3, 3, 4, 4, 6, 7, 8};

    //tiempo permitido para cada nivel
    int tiempos[] = {6000, 9000, 15000, 18000, 24000, 30000, 35000, 50000, 90000,
            8000, 14000, 17000, 230000, 29000, 34000, 60000, 80000, 90000,
            13000, 16000, 22000, 280000, 33000, 70000, 70000, 80000, 100000};


    int restadorintentos;
    int mlargo, mancho;
    boolean tiempo = false;
    LinearLayout layout;
    TextView txtpunt, txttitulo;
    ImageView botontemp;
    int[] botonesimg;
    boolean[] contestados;
    int ganador = 0;
    boolean gameover = false;
    int minutos, segundos;
    ImageView[] botones;
    int intentos = 0, id, carta1 = 0, carta2 = 0;
    Animation vibrar, mover, animstar, animmarco, animcarta1, animcarta2, seacaba;

    Button atras, restart;
    //boton atras
    int dificultad;
    DisplayMetrics metrics;
    int anchobtn;
    CountDownTimer crono;
    MediaPlayer mediaPlayer, click, winner = new MediaPlayer(), wrong = new MediaPlayer();
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memorama);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1984616735532779/6185480348");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        txtpunt = findViewById(R.id.txtpunt);
        txttitulo = findViewById(R.id.txttitulome);
        layout = findViewById(R.id.layoutmemo);
        dificultad = getIntent().getExtras().getInt("dificultad");
        id = getIntent().getExtras().getInt("id");

        // Ajustes.preferenciasonido(this,R.raw.riptide,true).start();
        //Ajustes.preferenciasonido(this, R.raw.riptide, true);

        if (Ajustes.Cargarboolean(this, "sonido")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.dugget);
            mediaPlayer.start();
        }

        vibrar = AnimationUtils.loadAnimation(Memorama.this, R.anim.vibrarbotones);
        mover = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandar);
        animstar = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandarstar);
        animmarco = AnimationUtils.loadAnimation(Memorama.this, R.anim.animacionmarco);
        animcarta1 = AnimationUtils.loadAnimation(Memorama.this, R.anim.carta1);
        animcarta2 = AnimationUtils.loadAnimation(Memorama.this, R.anim.carta2);
        seacaba = AnimationUtils.loadAnimation(Memorama.this, R.anim.seterminatiempo);
        if (dificultad == 3) {

            mlargo = Integer.parseInt("" + getIntent().getStringExtra("dimensiones").charAt(0));
            mancho = Integer.parseInt("" + getIntent().getStringExtra("dimensiones").charAt(2));
        } else {
            mlargo = matriz[0][dificultad][id];
            mancho = matriz[1][dificultad][id];
        }

        metrics = getResources().getDisplayMetrics();
        anchobtn = ((metrics.widthPixels / mancho + 1));
        botonesimg = new int[mancho * mlargo];
        botones = new ImageView[mancho * mlargo];
        contestados = new boolean[mancho * mlargo];
        atras = findViewById(R.id.atras);
        mostrarimagenes(mancho * mlargo);
        restadorintentos = intentospermitidos[id];
        // verificamos la modalidad de juego para poner el titulo
        switch (dificultad) {
            case 0:
                txttitulo.setText("Intentos fallidos: ");
                txtpunt.setText("0");

                break;
            case 1:
                txttitulo.setText("Intentos restantes: ");
                txtpunt.setText("" + restadorintentos);
                break;
            case 2:
                txttitulo.setText("Tiempo restante: ");
                txtpunt.setText("00:00");
                break;
            case 3:
                txttitulo.setText("Modo libre");
                txtpunt.setText(" ");
                break;
        }

        crearbotones();
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
            botones[i].setBackground(resize(getResources().getDrawable(botonesimg[i]), anchobtn));
        }

        //presentacion de las cartas se hace un temporizador en el que se descubren las cartas y despues de  mancho * 1000 milisegundos se vuelven a esconder
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < botones.length; i++) {
                    botones[i].setEnabled(true);
                    botones[i].startAnimation(animcarta1);
                    botones[i].setBackground(resize(getResources().getDrawable(R.drawable.fondomemo), anchobtn / 2));

                    if (dificultad == 2) {
                        portiempo();
                    }
                }
            }
        }, (mancho * 200 * mlargo) + 1000);

    }

    private void portiempo() {
        crono = new CountDownTimer(tiempos[id], 1000) {

            public void onTick(long millisUntilFinished) {
                int minutosi = (int) ((millisUntilFinished / 1000) / 60);
                int segundosi = (int) ((millisUntilFinished / 1000) - (minutosi * 60));
                minutos = minutosi;
                segundos = segundosi;
                if (segundosi < 10) {
                    txtpunt.setText("0" + minutosi + ":" + "0" + segundosi);
                } else {
                    txtpunt.setText("0" + minutosi + ":" + "" + segundosi);
                }
                if (segundosi < 10 && minutosi == 0) {
                    txtpunt.startAnimation(seacaba);
                    txtpunt.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }

            }

            public void onFinish() {
                if (!gameover)
                    dialogo(false);
            }

        }.start();
    }

    @Override
    public void onClick(View v) {
        Ajustes.sonidoplay(this, click, R.raw.click);
        Ajustes.vibrar(this, 50);

        switch (v.getId()) {
            case R.id.atras:
                if (dificultad == 3) {
                    Intent i = new Intent(Memorama.this, Niveles.class);
                    i.putExtra("dificultad", dificultad);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(Memorama.this, Nivel.class);
                    i.putExtra("dificultad", dificultad);
                    i.putExtra("pagina", pagina());
                    startActivity(i);
                    finish();
                }
                break;
            case R.id.restart:
                Intent i = new Intent(Memorama.this, Memorama.class);
                i.putExtra("dificultad", dificultad);
                i.putExtra("pagina", pagina());
                i.putExtra("id", id);
                i.putExtra("dimensiones", mlargo + "x" + mancho);
                i.putExtra("ancho", mancho);
                startActivity(i);
                finish();
                break;
        }
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
                    final int finalJ = j;
                    final int finalI = i;
                    btnTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //vibrar

                            Ajustes.vibrar(Memorama.this, 50);

                            if (carta1 == 0) {
                                Ajustes.sonidoplay(Memorama.this, click, R.raw.click);
                                btnTag.startAnimation(animcarta1);
                                btnTag.setBackground(resize(getResources().getDrawable(botonesimg[finalJ + (finalI * mancho)]), anchobtn / 2));
                                // btnTag.setText("" + (finalJ + (finalI * mancho) + 1));
                                carta1 = botonesimg[finalJ + (finalI * mancho)];
                                botontemp = btnTag;
                                btnTag.setEnabled(false);
                            } else {
                                for (int i = 0; i < botones.length; i++) {
                                    botones[i].setEnabled(false);
                                }
                                btnTag.setBackground(resize(getResources().getDrawable(botonesimg[finalJ + (finalI * mancho)]), anchobtn / 2));
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
                                    carta1 = 0;
                                    carta2 = 0;
                                    //verificamos si se descubrieron todos los pares
                                    if (ganador == botones.length / 2) {
                                        dialogo(true);
                                    }

                                } else {

                                    Ajustes.sonidoplay(Memorama.this, wrong, R.raw.wrong);
                                    botontemp.startAnimation(vibrar);
                                    btnTag.startAnimation(vibrar);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            botontemp.setBackground(resize(getResources().getDrawable(R.drawable.fondomemo), anchobtn / 2));
                                            btnTag.setBackground(resize(getResources().getDrawable(R.drawable.fondomemo), anchobtn / 2));
                                            for (int i = 0; i < botones.length; i++) {
                                                if (!contestados[i]) {
                                                    botones[i].setEnabled(true);
                                                }
                                            }
                                        }
                                    }, 700);
                                    carta1 = 0;
                                    carta2 = 0;
                                    if (dificultad == 0) {
                                        txtpunt.setText("" + intentos);
                                    }

                                    //verificamos que sea la modalidad de intentos permitidos
                                    if (dificultad == 1) {
                                        //se resta 1 a los intentos permitidos
                                        restadorintentos--;
                                        //setear intentos permitidos nuevamente en el textview
                                        txtpunt.setText("" + restadorintentos);
                                        //ponemos la animacion en rojo para la ultima tercera parte de los intentos
                                        if (restadorintentos < intentospermitidos[id] / 3) {
                                            txtpunt.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                                            txtpunt.startAnimation(seacaba);
                                        }
                                        //verificamos si los intentos permitidos son 0
                                        if (restadorintentos == 0) {
                                            //mostramos pantalla de perdedor!!!!
                                            dialogo(false);
                                        }
                                    }
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


        if (id <= 8) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenest[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenest[i - (total / 2)];
                i++;
            }

        }
        if (id == 9) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel10[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel10[i - (total / 2)];
                i++;
            }

        } //bien

        if (id == 10) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel11[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel11[i - (total / 2)];
                i++;
            }

        }


        if (id == 11) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel12[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel12[i - (total / 2)];
                i++;
            }

        }


        if (id == 12) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel13[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel13[i - (total / 2)];
                i++;
            }

        }


        if (id == 13) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel14[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel14[i - (total / 2)];
                i++;
            }

        }


        if (id == 14) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel15[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel15[i - (total / 2)];
                i++;
            }

        }


        if (id == 15) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel16[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel16[i - (total / 2)];
                i++;
            }

        }


        if (id == 16) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel17[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel17[i - (total / 2)];
                i++;
            }

        }


        if (id == 17) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel18[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel18[i - (total / 2)];
                i++;
            }

        }

        if (id == 18) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel19[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel19[i - (total / 2)];
                i++;
            }

        }

        if (id == 19) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel20[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel20[i - (total / 2)];
                i++;
            }

        }

        if (id == 20) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel21[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel21[i - (total / 2)];
                i++;
            }

        }

        if (id == 21) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel22[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel22[i - (total / 2)];
                i++;
            }

        }

        if (id == 22) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel23[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel23[i - (total / 2)];
                i++;
            }

        }
        if (id == 23) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel24[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel24[i - (total / 2)];
                i++;
            }

        }

        if (id == 24) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel25[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel25[i - (total / 2)];
                i++;
            }

        }

        if (id == 25) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel26[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel26[i - (total / 2)];
                i++;
            }

        }


        if (id == 26) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel27[i];
                i++;
            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel27[i - (total / 2)];
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


    private void dialogo(boolean resultado) {
        mostrarinterstitial();
        detenermusica(mediaPlayer);
        Ajustes.sonidoplay(this, winner, R.raw.winner);
        SharedPreferences sharedPref;
        sharedPref = Memorama.this.getSharedPreferences("record", Context.MODE_PRIVATE);
        ColorDrawable dialogColor = new ColorDrawable(Color.GRAY);
        dialogColor.setAlpha(0);
        final AlertDialog.Builder builder = new AlertDialog.Builder(Memorama.this);
        LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.layout_victoria, null);
        builder.setView(vi);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(dialogColor);
        ImageView titulo = vi.findViewById(R.id.imagentitulo);
        ImageView star1 = vi.findViewById(R.id.star1);
        ImageView star2 = vi.findViewById(R.id.star2);
        ImageView star3 = vi.findViewById(R.id.star3);
        Button botonretry = vi.findViewById(R.id.botonretry);
        LinearLayout marco = vi.findViewById(R.id.layoutmarco);
        marco.startAnimation(animmarco);
        SharedPreferences.Editor editor = sharedPref.edit();

        //verificamos si el metodo se llamo cuando se completo el memorama
        if (resultado) {
            if (dificultad == 3) {
            } else {
                star1.setBackground(getResources().getDrawable(R.drawable.star));
            }
            //verificamos si la las estrellas obtenidas son mas a las ya registradas que por default son cero
            //si es menor no guardara las estrellas obtenidas
            int numestrellas = estrellas((mancho * mlargo) / 2, titulo, star2, star3, botonretry);
            if (numestrellas > sharedPref.getInt(dificultad + "record" + id, 0)) {
                editor.putInt(dificultad + "record" + id, numestrellas);

            }
            editor.putBoolean(dificultad + "contestada" + (id + 1), true);
        }
        editor.commit();
        star1.startAnimation(animstar);
        star2.startAnimation(animstar);
        star3.startAnimation(animstar);


        Button botonok = vi.findViewById(R.id.botonok);
        botonok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Ajustes.vibrar(Memorama.this, 50);
                        detenermusica(winner);

                        if (dificultad == 3) {
                            Intent i = new Intent(Memorama.this, Niveles.class);
                            i.putExtra("dificultad", dificultad);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(Memorama.this, Nivel.class);
                            i.putExtra("dificultad", dificultad);
                            i.putExtra("pagina", pagina());
                            startActivity(i);
                            finish();
                        }
                    }
                }
        );

        botonretry.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detenermusica(winner);
                        Intent i = new Intent(Memorama.this, Memorama.class);
                        i.putExtra("dificultad", dificultad);
                        i.putExtra("pagina", pagina());
                        i.putExtra("id", id);
                        i.putExtra("largo", mlargo);
                        i.putExtra("ancho", mancho);
                        startActivity(i);
                        finish();
                    }
                }
        );

        dialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        detenermusica(mediaPlayer);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (Ajustes.Cargarboolean(this, "sonido"))
            mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detenermusica(mediaPlayer);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            detenermusica(mediaPlayer);
            if (dificultad == 3) {
                Intent i = new Intent(Memorama.this, Niveles.class);
                i.putExtra("dificultad", dificultad);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(Memorama.this, Nivel.class);
                i.putExtra("dificultad", dificultad);
                i.putExtra("pagina", pagina());
                startActivity(i);
                finish();
            }
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


    private int estrellas(int pares, ImageView titulo, ImageView star2, ImageView star3, Button botonretry) {
        int estrella = 1;
        int[] titulos = {R.drawable.titulovic1, R.drawable.titulovic2, R.drawable.titulovic3};
        // diferencia lo que hara el metodo dependiendo de la dificultad en la que se este ejecutando el nivel
        switch (dificultad) {
            case 0:
                if (intentos - pares < 5) {
                    star2.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                }
                if (intentos - pares < 2) {
                    star3.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                    botonretry.setVisibility(View.GONE);
                }
                break;
            case 1:
                if (restadorintentos > intentospermitidos[id] / 3) {
                    star2.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                }
                if (restadorintentos > (intentospermitidos[id] / 3) * 2) {
                    star3.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                    botonretry.setVisibility(View.GONE);
                }
                break;
            case 2:
                gameover = true;

                if (segundos > tiempos[id] / 3000) {
                    star2.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;

                }
                if (segundos > (tiempos[id] / 3000) * 2) {
                    star3.setBackground(getResources().getDrawable(R.drawable.star));
                    botonretry.setVisibility(View.GONE);
                    estrella++;
                }
                break;

            case 3:
                botonretry.setVisibility(View.GONE);

                break;
        }

        titulo.setBackground(getResources().getDrawable(titulos[estrella - 1]));

        return estrella;
    }

    private void detenermusica(MediaPlayer media) {
        if (Ajustes.Cargarboolean(this, "sonido")) {
            if (media.isPlaying())
                media.stop();
        }
    }

    private void mostrarinterstitial() {
        SharedPreferences sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE);
        int inter = sharedPref.getInt("interstitial", 0);
        if (inter == 1) {
            //Toast.makeText(this,"ad",Toast.LENGTH_LONG).show();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }

            inter = 0;
        } else
            inter++;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("interstitial", inter);
        editor.commit();

    }


}