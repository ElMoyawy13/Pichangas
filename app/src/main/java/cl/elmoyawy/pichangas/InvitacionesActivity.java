package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.app.Activity;

import java.sql.Array;

public class InvitacionesActivity extends Activity {
    ImageView back_button, profile_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitaciones);

        back_button = findViewById(R.id.back_to_menu);
        profile_button = findViewById(R.id.profile_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(InvitacionesActivity.this, MenuActivity.class);
                InvitacionesActivity.this.startActivity(intentProfile);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(InvitacionesActivity.this, PerfilActivity.class);
                InvitacionesActivity.this.startActivity(intentProfile);
            }
        });
    }
}
