package cl.elmoyawy.pichangas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NewAmigoActivity extends Activity implements View.OnClickListener {
    Button newFriend;
    EditText friendEmail;
    String name = "";
    String correo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_amigo);

        newFriend = findViewById(R.id.new_friend_button);
        friendEmail = findViewById(R.id.correo);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");

        newFriend.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        final String correo_amigo = friendEmail.getText().toString();
        if (correo_amigo.equals("")) {
            Toast.makeText(this, "Debes ingresar un correo",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        Toast.makeText(NewAmigoActivity.this, "¡Nuevo amigo añadido!",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NewAmigoActivity.this, AmigosActivity.class);
                        NewAmigoActivity.this.startActivity(intent);
                        NewAmigoActivity.this.finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        NewFriendRequest friendRequest = new NewFriendRequest(correo, correo_amigo, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NewAmigoActivity.this);
        queue.add(friendRequest);

        Toast.makeText(this, "Enviando solicitud...",
                Toast.LENGTH_SHORT).show();
    }

        @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewAmigoActivity.this, AmigosActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("correo", correo);
        NewAmigoActivity.this.startActivity(intent);
        super.onBackPressed();
    }
}
