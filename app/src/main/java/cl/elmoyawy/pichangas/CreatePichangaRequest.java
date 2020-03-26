package cl.elmoyawy.pichangas;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreatePichangaRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = Database.URL + "/Create_pichanga.php";
    private Map<String,String> params;
    CreatePichangaRequest(String correo, String name, String description, String place, String year, String month, String day, String hour, String minute, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("correo", correo);
        params.put("name", name);
        params.put("description", description);
        params.put("place", place);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);
        params.put("hour", hour);
        params.put("minute", minute);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
