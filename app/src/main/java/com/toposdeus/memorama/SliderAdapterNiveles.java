package com.toposdeus.memorama;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    public int[] lista = {R.drawable.btnjugar1, R.drawable.jugar2, R.drawable.jugar3, R.drawable.jugar4};
    public int[] titulosimagen = {R.drawable.titulofacil, R.drawable.titulonormal, R.drawable.titulodificil, R.drawable.tituloextremo};
    public String[] titulos = new String[]{"", "", "", ""};
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
        View view = inflater.inflate(R.layout.viewpager, container, false);
        LinearLayout layoutslide = view.findViewById(R.id.sliderlayout);
        Button boton = view.findViewById(R.id.botonslider);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/birdyame.ttf");
        TextView titulo = view.findViewById(R.id.slidertitulo);
        titulo.setTypeface(font);
        titulo.setText(titulos[position]);
        titulo.setBackground(context.getResources().getDrawable(titulosimagen[position]));
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
