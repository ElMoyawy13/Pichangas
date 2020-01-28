package cl.elmoyawy.pichangas;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PichangasRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.1.19/PichangasList.php";
    private Map<String,String> params;
    public PichangasRequest(Response.Listener<String> listener){
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("username", "Hola");  // TODO: get the username as global variable
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
