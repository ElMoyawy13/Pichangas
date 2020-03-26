package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.app.Activity;

import java.sql.Array;

public class EstadisticasActivity extends Activity {
    String name = "";
    String correo = "";
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

    }
    public void onBackPressed() {
        Intent intent = new Intent(EstadisticasActivity.this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        EstadisticasActivity.this.startActivity(intent);
        super.onBackPressed();
    }
}
