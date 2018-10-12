package com.toposdeus.memorama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SliderAdapterNivel extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    int dificultad[];
    int[] dimensiones1 = new int[]{1, 2, 2, 2, 3, 4, 3, 4, 4, 5, 5, 6, 2, 4, 6, 2, 4, 6, 2, 4, 6};//FILAS
    int[] dimensiones2 = new int[]{2, 2, 3, 4, 4, 3, 5, 4, 5, 5, 6, 5, 2, 4, 6, 2, 4, 6, 2, 4, 6};//COLUMNAS
    Typeface font;
    int[] record = new int[]{0, 1, 0, 2, 1, 0, 0, 3, 0, 1, 0, 2, 1, 0, 0, 3, 0, 1, 0, 2, 1, 0, 0, 3};
    boolean[] contestadas = new boolean[30];

    int[] definidor = {0, 15, 30, 45};

    public int[] lista = {R.drawable.botonok, R.drawable.botonok, R.drawable.botonok, R.drawable.botonok};
    public String[] titulos = new String[]{"Facil", "Normal", "DIficil", "Extremo"};
    public int colores[] = {
            Color.rgb(255, 255, 255),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),
            Color.rgb(1, 188, 212)
    };
    SharedPreferences sharedPref;


    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpagernivel, container, false);
        LinearLayout lin = view.findViewById(R.id.linealayoutmenu);
        sharedPref = context.getSharedPreferences("record", Context.MODE_PRIVATE);
        for (int i = 0; i < 16; i++) {
            record[i] = sharedPref.getInt("record" + i, 0);
        }
        contestadas[0] = true;
        for (int i = 0; i < 16; i++) {
            contestadas[i] = sharedPref.getBoolean("contestada" + i, false);
        }
        contestadas[0] = true;

        crearaccesos(4, 4, lin, definidor[position]);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public SliderAdapterNivel(Context context, int dificultad[]) {
        this.dificultad = dificultad;
        this.context = context;
    }


    public void crearaccesos(int mancho, int mlargo, LinearLayout layout, int position) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        for (int i = 0; i <= (mlargo); i++) {
            LinearLayout row = new LinearLayout(context);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            LinearLayout.LayoutParams lll = (LinearLayout.LayoutParams) row.getLayoutParams();
            lll.gravity = Gravity.CENTER;
            lll.setMargins(0, (metrics.widthPixels / mancho) / 5, 0, 0);
            row.setLayoutParams(lll);

            for (int j = 0; j < mancho; j++) {

                if ((j + (i * mancho)) <= (mancho * mlargo) - 1) {
                    View inflar = View.inflate(context, R.layout.layout_memorama, null);
                    Button boton = inflar.findViewById(R.id.botonmemorama);
                    if (!contestadas[j + (i * mancho)]) {
                        boton.setEnabled(false);
                        boton.setBackground(context.getResources().getDrawable(R.drawable.lock));
                    } else {
                        font = Typeface.createFromAsset(context.getAssets(), "fonts/birdyame.ttf");
                        boton.setTypeface(font);
                        //boton.setText("" + position);
                        boton.setText("" + (1 + position + j + (i * mancho)));
                    }

                    ImageView estrella1 = inflar.findViewById(R.id.star01);
                    ImageView estrella2 = inflar.findViewById(R.id.star02);
                    ImageView estrella3 = inflar.findViewById(R.id.star03);
                    switch (record[j + (i * mancho)]) {
                        case 1:
                            estrella1.setBackground(context.getResources().getDrawable(R.drawable.star));
                            break;
                        case 2:
                            estrella1.setBackground(context.getResources().getDrawable(R.drawable.star));
                            estrella2.setBackground(context.getResources().getDrawable(R.drawable.star));
                            break;
                        case 3:
                            estrella1.setBackground(context.getResources().getDrawable(R.drawable.star));
                            estrella2.setBackground(context.getResources().getDrawable(R.drawable.star));
                            estrella3.setBackground(context.getResources().getDrawable(R.drawable.star));
                            break;
                    }
                    final int finalJ = j;
                    final int finalI = i;
                    final int finalM = mancho;
                    boton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, Memorama.class);
                            intent.putExtra("cadena", "" + dimensiones1[finalJ + (finalI * finalM)] + "x" + dimensiones2[finalJ + (finalI * finalM)]);
                            intent.putExtra("id", finalJ + (finalI * finalM));
                            context.startActivity(intent);
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
