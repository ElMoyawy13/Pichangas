package cl.elmoyawy.pichangas;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NewFriendRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = Database.URL + "/Friend_request.php";
    private Map<String,String> params;
    NewFriendRequest(String correo, String correo_amigo, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("sujeto",correo);
        params.put("amigo",correo_amigo);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
