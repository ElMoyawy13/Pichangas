package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.app.Activity;

import java.sql.Array;

public class PerfilActivity extends Activity {
    TextView tvNombre, tvEdad, tvCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre = findViewById(R.id.texto_nombre);
        tvCorreo = findViewById(R.id.texto_correo);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String correo = intent.getStringExtra("correo");
        final String password = intent.getStringExtra("password");

        tvNombre.setText(name);
        tvCorreo.setText(correo);


    }
    }

