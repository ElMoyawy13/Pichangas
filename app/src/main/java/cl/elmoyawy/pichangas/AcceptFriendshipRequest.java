package cl.elmoyawy.pichangas;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AcceptFriendshipRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = Database.URL + "/Friend_accept.php";
    private Map<String,String> params;
    AcceptFriendshipRequest(String correo, String correoAmigo, Response.Listener<String> listener){
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("correo_sujeto",correo);
        params.put("correo_amigo",correoAmigo);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
