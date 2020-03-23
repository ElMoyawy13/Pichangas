package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.app.Activity;

import java.sql.Array;

public class AmigosActivity extends Activity {
    ImageView back_button, profile_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);

        back_button = findViewById(R.id.back_to_menu);
        profile_button = findViewById(R.id.profile_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(AmigosActivity.this, MenuActivity.class);
                AmigosActivity.this.startActivity(intentProfile);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(AmigosActivity.this, PerfilActivity.class);
                AmigosActivity.this.startActivity(intentProfile);
            }
        });

    }
}
