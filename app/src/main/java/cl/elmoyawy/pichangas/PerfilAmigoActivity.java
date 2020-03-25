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

public class PerfilAmigoActivity extends Activity {
    TextView tvNombre, tvCorreo;
    String name = "";
    String correo = "";
    String amigoName = "";
    String amigoCorreo = "";
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvNombre = findViewById(R.id.texto_nombre);
        tvCorreo = findViewById(R.id.texto_correo);
        linearLayout = findViewById(R.id.layout_datos);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");
        amigoName = intent.getStringExtra("amigoName");
        amigoCorreo = intent.getStringExtra("amigoCorreo");

        tvNombre.setText(amigoName);
        tvCorreo.setText(amigoCorreo);

        try{ if(!intent.getBooleanExtra("aceptado", false)) {
            Button amigo = new Button(PerfilAmigoActivity.this);
            amigo.setText(R.string.acceptFriend);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(24, 20, 24, 0);
            amigo.setLayoutParams(params);
            amigo.setBackgroundResource(R.drawable.secondary_button);
            amigo.setPadding(10,10,10,10);
            amigo.setClickable(true);
            amigo.setFocusable(true);
            amigo.setTextColor(Color.WHITE);
            amigo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success){
                                    Toast.makeText(PerfilAmigoActivity.this, "¡Nuevo amigo añadido!",
                                            Toast.LENGTH_LONG).show();
                                    PerfilAmigoActivity.this.finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    AcceptFriendshipRequest acceptRequest = new AcceptFriendshipRequest(correo, amigoCorreo, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PerfilAmigoActivity.this);
                    queue.add(acceptRequest);
                }
            });
            linearLayout.addView(amigo);
        }} catch(Exception e){e.printStackTrace();}
    }

    /*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PerfilAmigoActivity.this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        PerfilAmigoActivity.this.startActivity(intent);
        super.onBackPressed();
    }
    */
}
