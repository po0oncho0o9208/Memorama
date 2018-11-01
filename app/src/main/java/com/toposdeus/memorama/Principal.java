package com.toposdeus.memorama;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Principal extends AppCompatActivity implements View.OnClickListener {


    ImageView play, ajustes, trofeos;
    LinearLayout layout;
    ProgressBar progresbar;
    MediaPlayer click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        play = findViewById(R.id.btnplay);
        play.setOnClickListener(this);
        ajustes = findViewById(R.id.btnajustes);
        ajustes.setOnClickListener(this);
        trofeos = findViewById(R.id.btntrofeos);
        trofeos.setOnClickListener(this);
        layout = findViewById(R.id.layoutprincipal);
        progresbar = findViewById(R.id.pgbr);


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Ajustes.vibrar(this, 50);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View vi = inflater.inflate(R.layout.dialogoconfirm, null);
            builder.setView(vi);
            final AlertDialog dialog = builder.create();
            //decidir despues si sera cancelable o no
            dialog.setCancelable(false);
            Button botonsi = vi.findViewById(R.id.botonsi);

            botonsi.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Ajustes.vibrar(Principal.this, 50);
                            dialog.cancel();
                            Principal.super.onDestroy();
                            System.exit(0);
                        }
                    }
            );
            Button botonno = vi.findViewById(R.id.botonno);
            botonno.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Ajustes.vibrar(Principal.this, 50);
                            dialog.cancel();

                        }
                    }
            );
            dialog.show();
            //Metodos.dialogo( this, getLayoutInflater(), "¿seguro deseas salir de la aplicacion?", 0 );
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.setVisibility(View.GONE);
        progresbar.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        layout.setVisibility(View.VISIBLE);
        progresbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        layout.setVisibility(View.VISIBLE);
        progresbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Ajustes.sonidoplay(Principal.this, click, R.raw.click);
        Ajustes.vibrar(this, 50);
        switch (v.getId()) {
            case R.id.btnajustes:
                intent = new Intent(this, Ajustes.class);
                break;
            case R.id.btnplay:
                intent = new Intent(this, Niveles.class);
                break;
            case R.id.btntrofeos:
                intent = new Intent(this, MainActivity.class);
                break;
        }
        startActivity(intent);


    }
}
