package cl.elmoyawy.pichangas;


import android.app.Activity;
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

import android.widget.ImageView;
import android.widget.Toast;

public class Registro extends Activity implements View.OnClickListener {
    EditText etnombre, etusuario, etpassword, etpassword2, etedad, etcorreo;
    Button btn_registrar;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = findViewById(R.id.reg_nombre);
        etusuario = findViewById(R.id.reg_user);
        etpassword = findViewById(R.id.reg_pass);
        etpassword2 = findViewById(R.id.reg_pass_confirmation);
        etedad = findViewById(R.id.reg_edad);
        etcorreo = findViewById(R.id.reg_correo);

        btn_registrar = findViewById(R.id.btn_reg);

        btn_registrar.setOnClickListener(this);

        back_button = findViewById(R.id.back_to_menu);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(Registro.this, MainActivity.class);
                Registro.this.startActivity(intentMenu);
            }
        });
    }

    @Override
    public void onClick(View v) {

        final String name = etnombre.getText().toString();
        if(name.equals("")) {
            Toast.makeText(this, (String)"Debes ingresar un nombre",
                    Toast.LENGTH_LONG).show();
            return;
        }
        final String username = etusuario.getText().toString();
        if(username.equals("")) {
            Toast.makeText(this, (String)"Debes ingresar un nombre de usuario",
                    Toast.LENGTH_LONG).show();
            return;
        }
        final String password = etpassword.getText().toString();
        if(password.equals("")) {
            Toast.makeText(this, (String)"Debes ingresar una contraseña",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(etpassword2.getText().toString())) {
            Toast.makeText(this, (String)"Las contraseñas no coinciden",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(etedad.getText().toString().equals("")) {
            Toast.makeText(this, (String)"Debes ingresar una edad",
                    Toast.LENGTH_LONG).show();
            return;
        }
        final int age = Integer.parseInt(etedad.getText().toString());
        final String correo = etcorreo.getText().toString();
        if(correo.equals("")) {
            Toast.makeText(this, (String)"Debes ingresar un correo",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        Intent intent = new Intent(Registro.this, MenuActivity.class);
                        Registro.this.startActivity(intent);
                        Registro.this.finish();
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

        Toast.makeText(this, (String)"Registrándote...",
                Toast.LENGTH_LONG).show();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Registro.this, MainActivity.class);
        Registro.this.startActivity(intent);
        super.onBackPressed();
    }
}
