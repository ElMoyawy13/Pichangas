package cl.elmoyawy.pichangas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PichangaActivity extends Activity {
    TextView title_text_view, description_text_view, place_text_view, date_text_view, time_text_view;
    LinearLayout friendsList;
    String name = "";
    String correo = "";
    String pichangaName = "";
    String pichangaID = "";
    LinearLayout linearLayout;
    JSONObject player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pichanga);

        title_text_view = findViewById(R.id.pichanga_name);
        description_text_view = findViewById(R.id.description);
        place_text_view = findViewById(R.id.place);
        date_text_view = findViewById(R.id.date);
        time_text_view = findViewById(R.id.time);
        linearLayout = findViewById(R.id.layout_datos);
        friendsList = findViewById(R.id.friends_list);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");
        pichangaName = intent.getStringExtra("pichangaName");
        pichangaID = intent.getStringExtra("pichangaID");

        title_text_view.setText(pichangaName);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        title_text_view.setText(jsonResponse.getString("name"));
                        description_text_view.setText(jsonResponse.getString("description"));
                        place_text_view.setText(jsonResponse.getString("place"));
                        date_text_view.setText(jsonResponse.getString("day") + "/" + jsonResponse.getString("month") + "/" + jsonResponse.getString("year"));
                        time_text_view.setText(jsonResponse.getString("hour") + ":" + jsonResponse.getString("minute"));
                        for(int i = 0; i < jsonResponse.getJSONArray("participants").length(); i++) {
                            player = jsonResponse.getJSONArray("participants").getJSONObject(i);
                            Button amigo = new Button(PichangaActivity.this);
                            amigo.setText(player.getString("name"));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(24, 20, 24, 0);
                            amigo.setLayoutParams(params);
                            amigo.setBackgroundResource(R.drawable.secondary_button);
                            amigo.setPadding(10,10,10,10);
                            amigo.setClickable(true);
                            amigo.setFocusable(true);
                            amigo.setTextColor(Color.WHITE);
                            amigo.setOnClickListener(new View.OnClickListener() {
                                String friendName = player.getString("name");
                                String friendMail = player.getString("email");
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(PichangaActivity.this, PerfilAmigoActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("correo", correo);
                                    intent.putExtra("amigoName", friendName);
                                    intent.putExtra("amigoCorreo", friendMail);
                                    intent.putExtra("aceptado", true);
                                    PichangaActivity.this.startActivity(intent);
                                }
                            });
                            friendsList.addView(amigo);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        PichangaRequest pichangaRequest = new PichangaRequest(pichangaID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PichangaActivity.this);
        queue.add(pichangaRequest);
    }

}
