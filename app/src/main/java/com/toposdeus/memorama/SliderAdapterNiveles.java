package com.toposdeus.memorama;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    public int[] lista = {R.drawable.botonok, R.drawable.botonok, R.drawable.botonok, R.drawable.botonok};
    public String[] titulos = new String[]{"Facil", "Normal", "DIficil", "Extremo"};
    public int colores[] = {
            Color.rgb(255, 255, 255),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),
            Color.rgb(1, 188, 212)
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
        View view = inflater.inflate(R.layout.viewpager, container, false);
        LinearLayout layoutslide = view.findViewById(R.id.sliderlayout);
        Button boton = view.findViewById(R.id.botonslider);
        TextView titulo = view.findViewById(R.id.slidertitulo);
        titulo.setText(titulos[position]);
        layoutslide.setBackgroundColor(colores[position]);
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
