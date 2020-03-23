package cl.elmoyawy.pichangas;

import androidx.appcompat.app.AlertDialog;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
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

public class MainActivity extends Activity {

    EditText et_usuario;
    EditText et_password;
    EditText et_correo;
    Button btn_log;
    Button tv_registrar;

    List datos_user = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_registrar = findViewById(R.id.txt_registrar);
        et_usuario = findViewById(R.id.txt_user);
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

                final String username = et_usuario.getText().toString();
                final String password = et_password.getText().toString();
                final String correo = et_correo.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonOResponse = new JSONObject(response);
                            boolean success = jsonOResponse.getBoolean("success");
                            if (success){
                                String name = jsonOResponse.getString("name");
                                if(jsonOResponse.getString("username") == username) {
                                    //String correo = jsonOResponse.getString("correo");
                                }else {
                                    String username = jsonOResponse.getString("username");
                                }
                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                //intent.putExtra("correo", correo);
                                intent.putExtra("password", password);

                                datos_user.add(name);
                                datos_user.add(correo);
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
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);

            }
        });


    }
}
