package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;

public class EstadisticasActivity extends AppCompatActivity {
    ImageView back_button, profile_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        back_button = findViewById(R.id.back_to_menu);
        profile_button = findViewById(R.id.profile_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(EstadisticasActivity.this, MenuActivity.class);
                EstadisticasActivity.this.startActivity(intentProfile);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(EstadisticasActivity.this, PerfilActivity.class);
                EstadisticasActivity.this.startActivity(intentProfile);
            }
        });
    }
}
