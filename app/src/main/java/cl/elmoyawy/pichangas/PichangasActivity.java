package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import android.app.Activity;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PichangasActivity extends Activity {
    Button newPichanga;
    String name = "";
    String correo = "";
    String pichangaName = "";
    String pichangaID = "";
    LinearLayout pichangasList;
    RelativeLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pichangas);

        loading = findViewById(R.id.loadingPanel);
        pichangasList = findViewById(R.id.pichangas_list);
        newPichanga = findViewById(R.id.nueva_pichanga);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");

        newPichanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PichangasActivity.this, NewPichangaActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("correo", correo);
                PichangasActivity.this.startActivity(intent);
            }
        });
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonOResponse = new JSONObject(response);
                    boolean success = jsonOResponse.getBoolean("success");
                    if (success){
                        ((ViewManager)loading.getParent()).removeView(loading);
                        for(int i = 0; i < jsonOResponse.getJSONArray("pichangas").length(); i++) {
                            Button pichanga = new Button(PichangasActivity.this);
                            pichangaName = jsonOResponse.getJSONArray("pichangas").getJSONObject(i).getString("name");
                            pichangaID = jsonOResponse.getJSONArray("pichangas").getJSONObject(i).getString("id");
                            pichanga.setText(pichangaName);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(24, 20, 24, 0);
                            pichanga.setLayoutParams(params);
                            pichanga.setBackgroundResource(R.drawable.secondary_button);
                            pichanga.setPadding(10,10,10,10);
                            pichanga.setClickable(true);
                            pichanga.setFocusable(true);
                            pichanga.setTextColor(Color.WHITE);
                            pichanga.setOnClickListener(new View.OnClickListener() {
                                String matchName = pichangaName;
                                String matchID = pichangaID;
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(PichangasActivity.this, PichangaActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("correo", correo);
                                    intent.putExtra("pichangaName", matchName);
                                    intent.putExtra("pichangaID", matchID);
                                    PichangasActivity.this.startActivity(intent);
                                }
                            });
                            pichangasList.addView(pichanga);
                        }

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PichangasActivity.this);
                        builder.setMessage("Error de Login")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        PichangasRequest pichangasRequest = new PichangasRequest(correo, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PichangasActivity.this);
        queue.add(pichangasRequest);
    }

    @Override
    protected void onRestart() {
        startActivity(getIntent());
        finish();
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PichangasActivity.this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        PichangasActivity.this.startActivity(intent);
        super.onBackPressed();
    }
}
