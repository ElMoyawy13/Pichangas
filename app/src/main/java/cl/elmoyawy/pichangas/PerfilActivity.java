package cl.elmoyawy.pichangas;

import android.content.Intent;
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

        tvNombre = findViewById(R.id.txt_nombre);
        tvEdad = findViewById(R.id.txt_edad);
        tvCorreo = findViewById(R.id.txt_correo);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int edad = intent.getIntExtra("age",-1);
        String correo = intent.getStringExtra("correo");

        tvNombre.setText(name);
        tvEdad.setText(edad+"");
        tvCorreo.setText(correo);
        }
    }

