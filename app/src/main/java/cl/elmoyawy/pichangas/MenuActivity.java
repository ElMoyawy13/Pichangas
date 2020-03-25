package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.app.Activity;

public class MenuActivity extends Activity {

    Button btn_pichangas;
    Button btn_amigos;
    Button btn_invitaciones;
    Button btn_estadisticas;
    ImageView btn_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_pichangas = findViewById(R.id.btn_pichangas);
        btn_amigos = findViewById(R.id.btn_amigos);
        btn_invitaciones = findViewById(R.id.btn_invitaciones);
        btn_estadisticas = findViewById(R.id.btn_estadisticas);
        btn_perfil = findViewById(R.id.btn_perfil);


        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String correo = intent.getStringExtra("correo");
        final String password = intent.getStringExtra("password");




        btn_pichangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPichangas = new Intent(MenuActivity.this, PichangasActivity.class);
                intentPichangas.putExtra("name", name);
                intentPichangas.putExtra("correo", correo);
                MenuActivity.this.startActivity(intentPichangas);
                MenuActivity.this.finish();
            }
        });

        btn_amigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAmigos = new Intent(MenuActivity.this, AmigosActivity.class);
                intentAmigos.putExtra("name", name);
                intentAmigos.putExtra("correo", correo);
                intentAmigos.putExtra("password", password);
                MenuActivity.this.startActivity(intentAmigos);
                MenuActivity.this.finish();
            }
        });

        btn_invitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInvitaciones = new Intent(MenuActivity.this, InvitacionesActivity.class);
                intentInvitaciones.putExtra("name", name);
                intentInvitaciones.putExtra("correo", correo);
                MenuActivity.this.startActivity(intentInvitaciones);
                MenuActivity.this.finish();
            }
        });

        btn_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEstadisticas = new Intent(MenuActivity.this, EstadisticasActivity.class);
                intentEstadisticas.putExtra("name", name);
                intentEstadisticas.putExtra("correo", correo);
                MenuActivity.this.startActivity(intentEstadisticas);
                MenuActivity.this.finish();
            }
        });

        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PerfilActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("correo", correo);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });

    }
}
