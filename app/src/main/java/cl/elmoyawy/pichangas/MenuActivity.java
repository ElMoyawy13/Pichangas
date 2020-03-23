package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
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

        btn_pichangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPichangas = new Intent(MenuActivity.this, PichangasActivity.class);
                MenuActivity.this.startActivity(intentPichangas);
            }
        });

        btn_amigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAmigos = new Intent(MenuActivity.this, AmigosActivity.class);
                MenuActivity.this.startActivity(intentAmigos);
            }
        });

        btn_invitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInvitaciones = new Intent(MenuActivity.this, InvitacionesActivity.class);
                MenuActivity.this.startActivity(intentInvitaciones);
            }
        });

        btn_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEstadisticas = new Intent(MenuActivity.this, EstadisticasActivity.class);
                MenuActivity.this.startActivity(intentEstadisticas);
            }
        });

        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerfil = new Intent(MenuActivity.this, PerfilActivity.class);
                MenuActivity.this.startActivity(intentPerfil);
            }
        });

    }
}
