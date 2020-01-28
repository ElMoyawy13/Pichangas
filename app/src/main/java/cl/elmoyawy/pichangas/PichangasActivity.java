package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonOResponse = new JSONObject(response);
                    boolean success = jsonOResponse.getBoolean("success");
                    if (success){
                        int pichangasQuantity = jsonOResponse.getJSONArray("pichangas").length();
                        for(Integer i = 0; i < pichangasQuantity; i++) {
                            JSONObject pichanga = (JSONObject) jsonOResponse.getJSONArray("pichangas").get(i);
                            Button myButton = new Button(PichangasActivity.this);
                            myButton.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            myButton.setText(pichanga.get("name").toString());
                            layout_pichangas.addView(myButton);
                        }

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PichangasActivity.this);
                        builder.setMessage("Error del servidor")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        PichangasRequest pichangasRequest = new PichangasRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(PichangasActivity.this);
        queue.add(pichangasRequest);
    }
}
