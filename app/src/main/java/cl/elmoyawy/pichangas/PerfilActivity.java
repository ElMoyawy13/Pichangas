package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {
    EditText tvNombre, tvEdad, tvCorreo;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre = findViewById(R.id.txt_nombre);
        tvEdad = findViewById(R.id.txt_edad);
        tvCorreo = findViewById(R.id.txt_correo);
        back_button = findViewById(R.id.back_to_menu);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(PerfilActivity.this, MenuActivity.class);
                PerfilActivity.this.startActivity(intentMenu);
            }
        });


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Integer edad = intent.getIntExtra("age",-1);
        String correo = intent.getStringExtra("correo");

        tvNombre.setText(name);
        tvEdad.setText(edad.toString());
        tvCorreo.setText(correo);
        }
    }

