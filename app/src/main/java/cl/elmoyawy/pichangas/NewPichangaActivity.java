package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.app.Activity;

public class NewPichangaActivity extends Activity {
    ImageView back_button, profile_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pichanga);

        back_button = findViewById(R.id.back_to_menu);
        profile_button = findViewById(R.id.profile_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(NewPichangaActivity.this, MenuActivity.class);
                NewPichangaActivity.this.startActivity(intentProfile);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(NewPichangaActivity.this, PerfilActivity.class);
                NewPichangaActivity.this.startActivity(intentProfile);
            }
        });
    }
}
