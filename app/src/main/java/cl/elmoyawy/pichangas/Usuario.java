package cl.elmoyawy.pichangas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Usuario extends AppCompatActivity {
    TextView tvNombre, tvEdad, tvUser, tvCorreo, tvPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        tvNombre = findViewById(R.id.txt_nombre);
        tvUser = findViewById(R.id.txt_usuario);
        tvPass = findViewById(R.id.txt_password);
        tvEdad = findViewById(R.id.txt_edad);
        tvCorreo = findViewById(R.id.txt_correo);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        int edad = intent.getIntExtra("age",-1);
        String correo = intent.getStringExtra("correo");

        tvNombre.setText(name);
        tvUser.setText(username);
        tvPass.setText(password);
        tvEdad.setText(edad+"");
        tvCorreo.setText(correo);

    }
}
