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
    ImageView back_button;
    String name = "";
    String correo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitaciones);


    }
    public void onBackPressed() {
        Intent intent = new Intent(InvitacionesActivity.this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        InvitacionesActivity.this.startActivity(intent);
        super.onBackPressed();
    }
}
