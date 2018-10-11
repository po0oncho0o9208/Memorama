package com.toposdeus.memorama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


public class Menu extends AppCompatActivity implements View.OnClickListener {

    Button start;
    boolean verificador;
    String cadena;
    int[] dimensiones1 = new int[]{2, 4, 2, 2, 4, 6, 4, 4, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6};
    int[] dimensiones2 = new int[]{2, 4, 3, 2, 4, 6, 4, 4, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6};
    int[] record = new int[]{0, 1, 0, 2, 1, 0, 0, 3, 0, 1, 0, 2, 1, 0, 0, 3, 0, 1, 0, 2, 1, 0, 0, 3};
    boolean[] contestadas = new boolean[30];
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE);
        start = findViewById(R.id.btnstart);
        start.setOnClickListener(this);
        LinearLayout lay = findViewById(R.id.linealayoutmenu);
        for (int i = 0; i < 16; i++) {
            record[i] = sharedPref.getInt("record" + i, 0);
        }
        contestadas[0] = true;
        for (int i = 0; i < 16; i++) {
            contestadas[i] = sharedPref.getBoolean("contestada" + i, false);
        }
        contestadas[0] = true;
        crearaccesos(4, 4, lay);

        Spinner spinnerm = findViewById(R.id.spiner);
        ArrayAdapter<CharSequence> adaptadorm = ArrayAdapter.createFromResource(this, R.array.combinaciones, android.R.layout.simple_spinner_item);
        spinnerm.setAdapter(adaptadorm);
        spinnerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    verificador = false;
                } else {
                    verificador = true;
                }
                cadena = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (verificador) {
            Intent intent = new Intent(this, Memorama.class);
            intent.putExtra("cadena", cadena);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Selecciona una dificultad", Toast.LENGTH_LONG).show();
        }
    }

    public void crearaccesos(int mancho, int mlargo, LinearLayout layout) {
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
                    final LinearLayout btnTag = new LinearLayout(this);
                    //btnTag.setId(j + (i * mancho));
                    View inflar = View.inflate(Menu.this, R.layout.layout_memorama, null);
                    Button boton = inflar.findViewById(R.id.botonmemorama);
                    if (!contestadas[j + (i * mancho)]) {
                        boton.setEnabled(false);
                        boton.setBackground(getResources().getDrawable(R.drawable.lock));
                    }
                    boton.setText("" + (1 + j + (i * mancho)));
                    ImageView estrella1 = inflar.findViewById(R.id.star01);
                    ImageView estrella2 = inflar.findViewById(R.id.star02);
                    ImageView estrella3 = inflar.findViewById(R.id.star03);
                    switch (record[j + (i * mancho)]) {
                        case 1:
                            estrella1.setBackground(getResources().getDrawable(R.drawable.star));
                            break;
                        case 2:
                            estrella1.setBackground(getResources().getDrawable(R.drawable.star));
                            estrella2.setBackground(getResources().getDrawable(R.drawable.star));
                            break;
                        case 3:
                            estrella1.setBackground(getResources().getDrawable(R.drawable.star));
                            estrella2.setBackground(getResources().getDrawable(R.drawable.star));
                            estrella3.setBackground(getResources().getDrawable(R.drawable.star));
                            break;
                    }
                    //View inflar = getLayoutInflater().inflate(R.layout.layout_memorama, null);

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
                    final int finalM = mancho;
                    boton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Menu.this, Memorama.class);
                            intent.putExtra("cadena", "" + dimensiones1[finalJ + (finalI * finalM)] + "x" + dimensiones2[finalJ + (finalI * finalM)]);
                            intent.putExtra("id", finalJ + (finalI * finalM));
                            startActivity(intent);
                            finish();
                        }
                    });

                    row.addView(inflar);
                    row.setGravity(Gravity.CENTER);
                }
            }
            layout.addView(row);
        }
        layout.setGravity(Gravity.CENTER);

    }

}
