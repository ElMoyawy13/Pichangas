package cl.elmoyawy.pichangas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NewPichangaActivity extends Activity {
    EditText nombre_view, descripcion_view, lugar_view, ano_view, mes_view, dia_view, hora_view, minuto_view;
    Button create_button;
    String name, correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pichanga);
        nombre_view = findViewById(R.id.nombre);
        descripcion_view = findViewById(R.id.descripcion);
        lugar_view = findViewById(R.id.lugar);
        ano_view = findViewById(R.id.ano);
        mes_view = findViewById(R.id.mes);
        dia_view = findViewById(R.id.dia);
        hora_view = findViewById(R.id.hora);
        minuto_view = findViewById(R.id.minuto);

        create_button = findViewById(R.id.btn_reg);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        correo = intent.getStringExtra("correo");

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                NewPichangaActivity.this.finish();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NewPichangaActivity.this);
                                builder.setMessage("Error de Registro")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CreatePichangaRequest registerRequest = new CreatePichangaRequest(correo, nombre_view.getText().toString(), descripcion_view.getText().toString(), lugar_view.getText().toString(), ano_view.getText().toString(), mes_view.getText().toString(), dia_view.getText().toString(), hora_view.getText().toString(), minuto_view.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(NewPichangaActivity.this);
                queue.add(registerRequest);

                NewPichangaActivity.this.finish();
            }
        });
    }


}
