package com.toposdeus.memorama;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Detalle extends AppCompatActivity {
    TextView txtview;
    ImageView imagenview;
    int movil;
    Bitmap topo;
    AdView mAdView3;


    static Integer densidadpantalla;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imagenview = (ImageView) findViewById(R.id.iamgendetalle);

        Datos obj = (Datos) getIntent().getExtras().getSerializable("objeto");
        imagenview.setImageResource(obj.getImagen());
        movil = obj.getImagen();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(Detalle.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public void Compartir1(View view) {

        if (ContextCompat.checkSelfPermission(Detalle.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Detalle.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {

                ActivityCompat.requestPermissions(Detalle.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        Intent intento = new Intent(Intent.ACTION_SEND);
        intento.setType("*/*");
        String paramString1 = Integer.toString(movil);
        Bitmap topo2 = BitmapFactory.decodeStream(getResources().openRawResource(movil));
        String fileName = paramString1 + "" + ".png";
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        topo2.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        File ExternalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(ExternalStorageDirectory + File.separator + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes.toByteArray());
        } catch (IOException e) {

        } finally {
            if (fileOutputStream != null) {
                Uri bmpUri = Uri.parse(file.getPath());
                intento.putExtra(Intent.EXTRA_TEXT, "Comparte tú pasión futbolera con tú persona favorita" + Html.fromHtml("<br />") +
                        "solo descargar nuestra aplicacion y llevate los mejores protectores de pantalla para tu celular " + Html.fromHtml("<br />") +
                        "https://play.google.com/store/apps/details?id=com.games.user.americahd");
                intento.putExtra(
                        Intent.EXTRA_STREAM,
                        bmpUri);
                startActivity(Intent.createChooser(intento,
                        "Siguenos en nuestra pagina "));
            }
        }
    }

    public void Seguir(View view) {
        Intent intenta7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Desarrollador-Topos-Deus-423579291439302/"));
        startActivity(intenta7);


    }


}


