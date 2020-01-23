package cl.elmoyawy.pichangas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre, etusuario, etpassword, etedad, etcorreo;
    Button btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = findViewById(R.id.reg_nombre);
        etusuario = findViewById(R.id.reg_user);
        etpassword = findViewById(R.id.reg_pass);
        etedad = findViewById(R.id.reg_edad);
        etcorreo = findViewById(R.id.reg_correo);

        btn_registrar = findViewById(R.id.btn_reg);

        btn_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final String name = etnombre.getText().toString();
        final String username = etusuario.getText().toString();
        final String password = etpassword.getText().toString();
        final int age = Integer.parseInt(etedad.getText().toString());
        final String correo = etcorreo.getText().toString();
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        Registro.this.startActivity(intent);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("Error de Registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, correo, respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);

    }
}
