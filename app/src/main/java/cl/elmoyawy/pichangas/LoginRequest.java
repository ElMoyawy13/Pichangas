package cl.elmoyawy.pichangas;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = Database.URL + "/Login.php";
    private Map<String,String> params;
    public LoginRequest(String correo, String password, Response.Listener<String> listener){
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("correo",correo);
        params.put("password",password);
    }

    public LoginRequest(String user, String pass) {
        super(Request.Method.POST,LOGIN_REQUEST_URL,null,null);
        params = new HashMap<>();
        params.put("correo",user);
        params.put("password",pass);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
