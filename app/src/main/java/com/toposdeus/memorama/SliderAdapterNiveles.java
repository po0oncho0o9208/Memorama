package com.toposdeus.memorama;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SliderAdapterNiveles extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    int dificultad[];
    Activity activity;
    public boolean sfin = false;
    public int[] lista = {R.drawable.btnjugar1, R.drawable.btnjugar2, R.drawable.btnjugar3, R.drawable.btnjugar4};
    public int[] titulosimagen = {R.drawable.titulonormal, R.drawable.titulopreciso, R.drawable.titulocontrareloj, R.drawable.titulolibre};
    public String[] titulos = new String[]{"", "", "", ""};
    boolean[] contestadas = new boolean[27];
    int paginas[] = {R.layout.viewpager, R.layout.viewpager, R.layout.viewpager, R.layout.dialogolibre};
    SharedPreferences sharedPref;
    int anchovar, largovar;


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

        View view = inflater.inflate(paginas[position], container, false);
        Button boton = view.findViewById(R.id.botonslider);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/birdyame.ttf");
        TextView titulo = view.findViewById(R.id.slidertitulo);
        titulo.setTypeface(font);
        titulo.setText(titulos[position]);
        titulo.setBackground(context.getResources().getDrawable(titulosimagen[position]));
        int contador = 0;
        int contadorestrellas = 0;
        TextView txvwresueltas = view.findViewById(R.id.resueltas);
        TextView txtestrellas = view.findViewById(R.id.estrellas);


        if (position == 3) {
            Spinner spinnera = view.findViewById(R.id.spinnera);
            ArrayAdapter<CharSequence> adaptadora = ArrayAdapter.createFromResource(context, R.array.ancho, R.layout.spinner_itemview);
            spinnera.setAdapter(adaptadora);
            spinnera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    anchovar = Integer.parseInt(parent.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            Spinner spinnerl = view.findViewById(R.id.spinnerl);
            ArrayAdapter<CharSequence> adaptadorl = ArrayAdapter.createFromResource(context, R.array.largo, R.layout.spinner_itemview);
            spinnerl.setAdapter(adaptadorl);
            spinnerl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    largovar = Integer.parseInt(parent.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        }
        for (int i = 0; i < 27; i++) {
            if (contestadas[i] = sharedPref.getBoolean(position + "contestada" + i, false)) {
                contador++;
            }

        }
        for (int i = 0; i < 27; i++) {
            contadorestrellas += sharedPref.getInt(position + "record" + i, 0);
        }
        if (position < 3) {
            txvwresueltas.setTypeface(font);
            txtestrellas.setTypeface(font);
            txvwresueltas.setTextSize(45);
            txtestrellas.setTextSize(35);
            txvwresueltas.setTextColor(context.getResources().getColor(R.color.white));
            txtestrellas.setTextColor(context.getResources().getColor(R.color.white));
            txtestrellas.setText(contadorestrellas + " X ");
            txvwresueltas.setText("" + contador + "/27");
        } else {


            boton.setEnabled(false);
        }
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sfin = true;
                if (position == 3) {
                    Intent intent = new Intent(context, Memorama.class);
                    intent.putExtra("dificultad", position);
                    intent.putExtra("ancho", anchovar);
                    intent.putExtra("largo", largovar);
                    intent.putExtra("id", 0);
                    context.startActivity(intent);
                    activity.finish();
                } else {
                    Intent intent = new Intent(context, Nivel.class);
                    intent.putExtra("dificultad", position);
                    context.startActivity(intent);
                    activity.finish();
                }
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
