package cl.elmoyawy.pichangas;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PichangaRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL =  Database.URL + "/Get_pichanga_data.php";
    private Map<String,String> params;
    PichangaRequest(String id, Response.Listener<String> listener){
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("ID", id);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
