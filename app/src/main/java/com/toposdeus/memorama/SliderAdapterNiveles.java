package com.toposdeus.memorama;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SliderAdapterNiveles extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    int dificultad[];
    Activity activity;
    public boolean sfin = false;
    public int[] lista = {R.drawable.btnjugar1, R.drawable.btnjugar2, R.drawable.btnjugar3, R.drawable.btnjugar4};
    public int[] titulosimagen = {R.drawable.titulofacil, R.drawable.titulonormal, R.drawable.titulodificil, R.drawable.tituloextremo};
    public String[] titulos = new String[]{"", "", "", ""};
    boolean[] contestadas = new boolean[27];
    SharedPreferences sharedPref;


    public int colores[] = {
            Color.rgb(237, 204, 144),
            Color.rgb(160, 196, 154),

            Color.rgb(152, 174, 215),
            Color.rgb(184, 151, 192)
    };


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
        sharedPref = context.getSharedPreferences("record", Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.viewpager, container, false);
        LinearLayout layoutslide = view.findViewById(R.id.sliderlayout);
        Button boton = view.findViewById(R.id.botonslider);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/birdyame.ttf");
        TextView titulo = view.findViewById(R.id.slidertitulo);
        titulo.setTypeface(font);
        titulo.setText(titulos[position]);
        titulo.setBackground(context.getResources().getDrawable(titulosimagen[position]));
        layoutslide.setBackgroundColor(colores[position]);
        int contador = 0;
        int contadorestrellas = 0;
        TextView txvwresueltas = view.findViewById(R.id.resueltas);
        TextView txtestrellas = view.findViewById(R.id.estrellas);

        txvwresueltas.setTypeface(font);
        txtestrellas.setTypeface(font);

        txvwresueltas.setTextSize(60);
        txtestrellas.setTextSize(40);
        txvwresueltas.setTextColor(context.getResources().getColor(R.color.white));
        txtestrellas.setTextColor(context.getResources().getColor(R.color.white));

        for (int i = 0; i < 27; i++) {
            if (contestadas[i] = sharedPref.getBoolean(position + "contestada" + i, false)) {
                contador++;
            }

        }
        for (int i = 0; i < 27; i++) {
            contadorestrellas += sharedPref.getInt(position + "record" + i, 0);
        }
        txtestrellas.setText(contadorestrellas + " X ");
        txvwresueltas.setText("" + contador + "/27");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Nivel.class);
                intent.putExtra("dificultad", position);
                context.startActivity(intent);
                activity.finish();
                sfin = true;

            }
        });
        boton.setBackground(context.getResources().getDrawable(lista[position]));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public SliderAdapterNiveles(Context context, int dificultad[], Activity activity) {
        this.dificultad = dificultad;
        this.context = context;
        this.activity = activity;
    }

}
