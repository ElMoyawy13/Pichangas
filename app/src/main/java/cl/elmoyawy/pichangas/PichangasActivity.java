package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;

public class PichangasActivity extends AppCompatActivity {
    Button nueva_pichanga;
    LinearLayout layout_pichangas;
    Array pichangas;
    ImageView back_button, profile_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pichangas);

        nueva_pichanga = findViewById(R.id.nuevaPichanga);
        layout_pichangas = findViewById(R.id.layout_pichangas);
        back_button = findViewById(R.id.back_to_menu);
        profile_button = findViewById(R.id.profile_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(PichangasActivity.this, MenuActivity.class);
                PichangasActivity.this.startActivity(intentMenu);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(PichangasActivity.this, PerfilActivity.class);
                PichangasActivity.this.startActivity(intentProfile);
            }
        });

        nueva_pichanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistrar = new Intent(PichangasActivity.this, NewPichangaActivity.class);
                PichangasActivity.this.startActivity(intentRegistrar);
            }
        });

        /*Obtener las pichangas desde el servidor.*/
        /*pichangas = new Array;*/
        for(Integer i = 0; i < 3; i++) {
            Button myButton = new Button(this);
            myButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            myButton.setText("Pichanga"+i.toString());
            layout_pichangas.addView(myButton);
        }
    }
}
