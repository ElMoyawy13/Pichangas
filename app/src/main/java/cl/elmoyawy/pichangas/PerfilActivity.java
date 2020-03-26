package cl.elmoyawy.pichangas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.app.Activity;

import java.sql.Array;

public class PerfilActivity extends Activity {
    TextView tvNombre, tvCorreo;
    String name = "";
    String correo = "";
    ImageView btn_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre = findViewById(R.id.texto_nombre);
        tvCorreo = findViewById(R.id.texto_correo);
        btn_perfil = findViewById(R.id.btn_perfil);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");

        tvNombre.setText(name);
        tvCorreo.setText(correo);

        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("correo", correo);
                SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("user", null);
                editor.putString("pass", null);
                editor.putString("name", null);

                editor.commit();
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PerfilActivity.this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        PerfilActivity.this.startActivity(intent);
        super.onBackPressed();
    }
}

