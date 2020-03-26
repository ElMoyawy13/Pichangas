package cl.elmoyawy.pichangas;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = Database.URL + "/register.php";
    private Map<String,String> params;
    RegisterRequest(String name, String password, String correo, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("password",password);
        params.put("correo",correo);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
