package com.toposdeus.memorama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class Menu extends AppCompatActivity implements View.OnClickListener {

    Button start;
    boolean verificador;
    String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        start = findViewById(R.id.btnstart);
        start.setOnClickListener(this);


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
}
