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

public class Memorama extends AppCompatActivity implements View.OnClickListener {
    int[][][] matriz =

            //filas 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
            {{{1, 2, 2, 3, 2, 4, 5, 4, 5,    2, 2, 3, 2, 4, 5, 4, 5, 6, 6, 6, 7, 6, 6, 7, 6, 9, 8},
                    {1, 2, 2, 3, 2, 4, 5, 4, 5, 2, 2, 3, 2, 4, 5, 4, 5, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {2, 3, 2, 4, 5, 4, 5, 6, 6, 5, 6, 7, 8, 9, 10, 6, 2, 4, 6, 7, 7},
                    {2, 2, 3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {3, 3, 5, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6}},


                    //columnas                    {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},

                    {{2, 2, 3, 3, 5, 3, 3, 4, 4,    2, 3, 3, 5, 3, 3, 4, 4, 4, 5, 6, 5, 7, 8, 7, 9, 9, 8},
                            {2, 2, 3, 3, 5, 3, 3, 4, 4, 2, 3, 3, 5, 3, 3, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2},
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


    int imagenesnivel10[] = new int[]{R.drawable.unoin, R.drawable.uva};

    int imagenesnivel11[] = new int[]{R.drawable.unoin, R.drawable.dulces, R.drawable.dosin};

    int imagenesnivel12[] = new int[]{R.drawable.uno1, R.drawable.tacos, R.drawable.dos2, R.drawable.tresin, R.drawable.tambores};

    int imagenesnivel13[] = new int[]{R.drawable.uno1, R.drawable.conejo, R.drawable.dos2, R.drawable.tresin, R.drawable.cuatroin};

    int imagenesnivel14[] = new int[]{R.drawable.uno1, R.drawable.fantasmas, R.drawable.dos2, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin};

    int imagenesnivel15[] = new int[]{R.drawable.uno1, R.drawable.soles, R.drawable.dos2, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin, R.drawable.sombreros};

    int imagenesnivel16[] = new int[]{R.drawable.uno1, R.drawable.sillas, R.drawable.dos2, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin,
    R.drawable.sietein};

    int imagenesnivel17[] = new int[]{R.drawable.uno1, R.drawable.estrellas, R.drawable.dos2, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin,
            R.drawable.sietein, R.drawable.ochoin, R.drawable.ojos};

    int imagenesnivel18[] = new int[]{R.drawable.uno1, R.drawable.nubes, R.drawable.dos2, R.drawable.tresin, R.drawable.cuatroin, R.drawable.cincoin, R.drawable.seisin,
            R.drawable.sietein, R.drawable.ochoin, R.drawable.nuevein, R.drawable.naranjas,R.drawable.oso};

    //intentos para cad nivel en especifico
    int intentospermitidos[] = {3, 6, 6, 6, 6, 2, 5, 6, 8, 5, 6, 4, 5, 6, 5, 4, 6, 6, 4, 5, 4, 5, 5, 5, 5, 5, 8, 5, 8, 5, 2, 21,};

    //tiempo permitido para cada nivel
    int tiempos[] = {30000, 30000, 50000, 30000, 30000, 50000, 30000, 30000, 50000, 30000, 30000, 50000,
            30000, 30000, 50000, 30000, 30000, 50000, 30000, 30000, 50000, 30000, 30000, 50000, 30000, 30000, 50000, 30000, 30000, 50000};


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

    Button atras;
    //boton atras
    int dificultad;
    DisplayMetrics metrics;
    int anchobtn;
    CountDownTimer crono;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memorama);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtpunt = findViewById(R.id.txtpunt);
        txttitulo = findViewById(R.id.txttitulome);
        layout = findViewById(R.id.layoutmemo);
        dificultad = getIntent().getExtras().getInt("dificultad");
        id = getIntent().getExtras().getInt("id");

        vibrar = AnimationUtils.loadAnimation(Memorama.this, R.anim.vibrarbotones);
        mover = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandar);
        animstar = AnimationUtils.loadAnimation(Memorama.this, R.anim.agrandarstar);
        animmarco = AnimationUtils.loadAnimation(Memorama.this, R.anim.animacionmarco);
        animcarta1 = AnimationUtils.loadAnimation(Memorama.this, R.anim.carta1);
        animcarta2 = AnimationUtils.loadAnimation(Memorama.this, R.anim.carta2);
        seacaba = AnimationUtils.loadAnimation(Memorama.this, R.anim.seterminatiempo);

        mlargo = matriz[0][dificultad][id];
        mancho = matriz[1][dificultad][id];
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
                txttitulo.setText("Intentos: ");
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
                    final int finalJ = j;
                    final int finalI = i;
                    btnTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (carta1 == 0) {
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

        }  if (   id == 9  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel10[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel10[i - (total / 2)];
                i++;
            }

        } //bien

        if (id == 10  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel11[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel11[i - (total / 2)];
                i++;
            }

        }


        if (id ==11 ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel12[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel12[i - (total / 2)];
                i++;
            }

        }


        if (id ==12  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel13[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel13[i - (total / 2)];
                i++;
            }

        }



        if (id ==13  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel14[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel14[i - (total / 2)];
                i++;
            }

        }


        if (id ==14  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel15[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel15[i - (total / 2)];
                i++;
            }

        }


        if (id ==15  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel16[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel16[i - (total / 2)];
                i++;
            }

        }


        if (id ==16  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel17[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel17[i - (total / 2)];
                i++;
            }

        }


        if (id ==17  ) {
            for (int i = 0; i < total / 2; ) {
                botonesimg[i] = imagenesnivel18[i];
                i++;

            }
            for (int i = (total / 2); i < total; ) {
                botonesimg[i] = imagenesnivel18[i - (total / 2)];
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
        TextView txtintentos = vi.findViewById(R.id.txtintentos);
        txtintentos.setText("Intentos: " + intentos);
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
            star1.setBackground(getResources().getDrawable(R.drawable.star));
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


    private int estrellas(int pares, ImageView titulo, ImageView star2, ImageView star3, Button botonretry) {
        int estrella = 1;
        int[] titulos = {R.drawable.titulovic1, R.drawable.titulovic2, R.drawable.titulovic3};
        // diferencia lo que hara el metodo dependiendo de la dificultad en la que se este ejecutando el nivel
        switch (dificultad) {
            case 0:
                if (intentos - pares < 10) {
                    star2.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                }
                if (intentos - pares < 5) {
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
                if (segundos > (tiempos[id] / 3000) * 2) {
                    star2.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                }
                if (segundos > tiempos[id] / 3000) {
                    star3.setBackground(getResources().getDrawable(R.drawable.star));
                    estrella++;
                    botonretry.setVisibility(View.GONE);
                }
                break;
        }

        titulo.setBackground(getResources().getDrawable(titulos[estrella - 1]));

        return estrella;
    }

}