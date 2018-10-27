package com.toposdeus.memorama;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ajustes extends AppCompatActivity {

    CheckBox sonido, vibrar;
    Context context;

    Button btnatras, reestablecer, botoncomparte, botoncalifica;
    public static int REQUEST_PERMISSIONS = 1;
    Boolean confirmacion;
    SharedPreferences sharedPref;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);

        btnatras = findViewById(R.id.botonatras);

        sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE);

    }

    public void atras(View view) {

        Intent intent08 = new Intent(this, Principal.class);
        startActivity(intent08);
    }

    public void Compartir(View view) {

        if (ContextCompat.checkSelfPermission(Ajustes.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Ajustes.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {

                ActivityCompat.requestPermissions(Ajustes.this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
        if (confirmacion = true) {


            Intent intento = new Intent(Intent.ACTION_SEND);
            intento.setType("*/*");
            String paramString1 = Integer.toString(R.drawable.atras4);
            Bitmap topo2 = BitmapFactory.decodeResource(getResources(), R.drawable.atras4);
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
                    intento.putExtra(Intent.EXTRA_TEXT, "En esta aplicación podrás descargar y ver tu tarjetón digital, veras sus promociones" + Html.fromHtml("<br />") +
                            "y recibirás notificaciones de cuando llegue el tarjetón , así como noticias relevantes del IMSS  " + Html.fromHtml("<br />") +
                            "https://play.google.com/store/apps/details?id=com.tarjetonimss.user.imsswebtarjeton");
                    intento.putExtra(
                            Intent.EXTRA_STREAM,
                            bmpUri);
                    startActivity(Intent.createChooser(intento,
                            "Siguenos en nuestra pagina "));
                }
            }
        } else {
            Toast.makeText(Ajustes.this, "Gracias, bonito día", Toast.LENGTH_SHORT).show();
        }
    }

    public void Calificar(View view) {
        Intent intentae4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetonjubilados/(S(lpvgwevvhy0ja2padtk4t12e))/default.aspx"));
        startActivity(intentae4);
    }


    public void Borrar(View view) {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.dialogoconfirm2, null);
        builder.setView(vi);
        final android.app.AlertDialog dialog = builder.create();
        //decidir despues si sera cancelable o no
        dialog.setCancelable(false);
        Button botonsi = vi.findViewById(R.id.botonsi1);

        botonsi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPref = getSharedPreferences("record", 0);
                        sharedPref.edit().remove("instrucciones").commit();
                    }
                }
        );
        Button botonno = vi.findViewById(R.id.botonno1);
        botonno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();

                    }
                }
        );
        dialog.show();
        //Metodos.dialogo( this, getLayoutInflater(), "¿seguro deseas salir de la aplicacion?", 0 );
    }


}
