package cl.elmoyawy.pichangas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;

public class PichangasActivity extends AppCompatActivity {
    Button nueva_pichanga;
    LinearLayout layout_pichangas;
    Array pichangas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pichangas);

        nueva_pichanga = findViewById(R.id.nuevaPichanga);
        layout_pichangas = findViewById(R.id.layout_pichangas);

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
