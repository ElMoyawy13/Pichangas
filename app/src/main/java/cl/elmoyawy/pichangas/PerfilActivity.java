package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;

public class PerfilActivity extends AppCompatActivity {
    TextView tvNombre, tvEdad, tvCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre = findViewById(R.id.texto_nombre);
        tvEdad = findViewById(R.id.texto_edad);
        tvCorreo = findViewById(R.id.texto_correo);





    }
    }

