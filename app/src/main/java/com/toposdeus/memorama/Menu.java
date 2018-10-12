package com.toposdeus.memorama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Menu extends AppCompatActivity {

    //aqui se define la cantidad de cartas
    int[] dimensiones1 = new int[]{1, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6};
    int[] dimensiones2 = new int[]{2, 2, 3, 4, 4, 5, 5, 6, 6, 7, 7, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6};
    Typeface font;
    int[] record = new int[]{0, 1, 0, 2, 1, 0, 0, 3, 0, 1, 0, 2, 1, 0, 0, 3, 0, 1, 0, 2, 1, 0, 0, 3};
    boolean[] contestadas = new boolean[30];
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        font = Typeface.createFromAsset(getAssets(), "fonts/birdyame.ttf");
        sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE);
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

    }


    public void crearaccesos(int mancho, int mlargo, LinearLayout layout) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
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
                    View inflar = View.inflate(Menu.this, R.layout.layout_memorama, null);
                    Button boton = inflar.findViewById(R.id.botonmemorama);
                    if (!contestadas[j + (i * mancho)]) {
                        boton.setEnabled(false);
                        boton.setBackground(getResources().getDrawable(R.drawable.lock));
                    } else {
                        boton.setTypeface(font);
                        boton.setText("" + (1 + j + (i * mancho)));
                    }

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
