package cl.elmoyawy.pichangas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;

public class AmigosActivity extends Activity {
    Button newFriend;
    String name = "";
    String correo = "";
    String amigoName = "";
    String amigoCorreo = "";
    LinearLayout friendsList;
    RelativeLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);

        loading = findViewById(R.id.loadingPanel);
        friendsList = findViewById(R.id.friends_list);
        newFriend = findViewById(R.id.new_friend_button);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");

        newFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmigosActivity.this, NewAmigoActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("correo", correo);
                AmigosActivity.this.startActivity(intent);
                AmigosActivity.this.finish();
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
                        for(int i = 0; i < jsonOResponse.getJSONArray("friends").length(); i++) {
                            Button amigo = new Button(AmigosActivity.this);
                            amigoName = jsonOResponse.getJSONArray("friends").getJSONObject(i).getString("name");
                            amigoCorreo = jsonOResponse.getJSONArray("friends").getJSONObject(i).getString("correo");
                            amigo.setText(amigoName);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(0, 20, 0, 0);
                            amigo.setLayoutParams(params);
                            amigo.setBackgroundResource(R.drawable.secondary_button);
                            amigo.setPadding(10,10,10,10);
                            amigo.setClickable(true);
                            amigo.setFocusable(true);
                            amigo.setTextColor(Color.WHITE);
                            amigo.setOnClickListener(new View.OnClickListener() {
                                String friendName = amigoName;
                                String friendMail = amigoCorreo;
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(AmigosActivity.this, PerfilAmigoActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("correo", correo);
                                    intent.putExtra("amigoName", friendName);
                                    intent.putExtra("amigoCorreo", friendMail);
                                    intent.putExtra("aceptado", true);
                                    AmigosActivity.this.startActivity(intent);
                                }
                            });
                            friendsList.addView(amigo);
                        }
                        for(int i = 0; i < jsonOResponse.getJSONArray("requests").length(); i++) {
                            Button amigo = new Button(AmigosActivity.this);
                            amigoName = jsonOResponse.getJSONArray("requests").getJSONObject(i).getString("name");
                            amigoCorreo = jsonOResponse.getJSONArray("requests").getJSONObject(i).getString("correo");
                            amigo.setText(amigoName);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(24, 20, 24, 0);
                            amigo.setLayoutParams(params);
                            amigo.setBackgroundResource(R.drawable.terciary_button);
                            amigo.setPadding(10,10,10,10);
                            amigo.setClickable(true);
                            amigo.setFocusable(true);
                            amigo.setTextColor(Color.WHITE);
                            amigo.setOnClickListener(new View.OnClickListener() {
                                String friendName = amigoName;
                                String friendMail = amigoCorreo;
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(AmigosActivity.this, PerfilAmigoActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("correo", correo);
                                    intent.putExtra("amigoName", friendName);
                                    intent.putExtra("amigoCorreo", friendMail);
                                    intent.putExtra("aceptado", false);
                                    AmigosActivity.this.startActivity(intent);
                                }
                            });
                            friendsList.addView(amigo);
                        }

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(AmigosActivity.this);
                        builder.setMessage("Error de Login")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        AmigosRequest amigosRequest = new AmigosRequest(correo, responseListener);
        RequestQueue queue = Volley.newRequestQueue(AmigosActivity.this);
        queue.add(amigosRequest);
    }

    @Override
    protected void onRestart() {
        finish();
        startActivity(getIntent());
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AmigosActivity.this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        AmigosActivity.this.startActivity(intent);
        super.onBackPressed();
    }
}
