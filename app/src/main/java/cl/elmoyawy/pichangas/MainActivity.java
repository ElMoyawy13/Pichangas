package cl.elmoyawy.pichangas;

import androidx.appcompat.app.AlertDialog;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.midi.MidiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Response.*;

public class MainActivity extends Activity {

    EditText et_correo;
    EditText et_password;
    Button btn_log;
    Button tv_registrar;
    private Object Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] nombre = {" "};

        cargarPreferencias();

        tv_registrar = findViewById(R.id.txt_registrar);
        et_password = findViewById(R.id.txt_pass);
        et_correo = findViewById(R.id.txt_user);

        btn_log = findViewById(R.id.btn_iniciar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistrar = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentRegistrar);
                MainActivity.this.finish();
            }
        });
        btn_log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String correo = et_correo.getText().toString();
                final String password = et_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonOResponse = new JSONObject(response);
                            boolean success = jsonOResponse.getBoolean("success");
                            if (success){
                                String name = jsonOResponse.getString("name");
                                nombre[0] = name;
                                guardarPreferencias();

                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("correo", correo);
                                intent.putExtra("password", password);
                                MainActivity.this.startActivity(intent);
                                MainActivity.this.finish();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error de Login")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LoginRequest(correo, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);

            }

            private void guardarPreferencias() {
                SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                String correo = et_correo.getText().toString();
                String pass = et_password.getText().toString();
                String name = nombre[0];

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("user", correo);
                editor.putString("pass", pass);
                editor.putString("name", name);


                editor.commit();


            }
        });


    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        if (preferences.getString("user", null) != null){
            String user = preferences.getString("user",null);
            String pass = preferences.getString("pass",null);
            String name = preferences.getString("name",null);


            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("correo", user);
            intent.putExtra("password", pass);


            LoginRequest loginRequest = new LoginRequest(user, pass);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();
        }


    }
}
