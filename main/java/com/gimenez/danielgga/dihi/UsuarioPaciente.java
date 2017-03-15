package com.gimenez.danielgga.dihi;

                import android.content.DialogInterface;
                import android.support.v7.app.AlertDialog;
                import android.support.v7.app.AppCompatActivity;
                import android.os.Bundle;
                import android.util.Log;
                import android.view.View;
                import android.widget.Button;
                import android.widget.EditText;
                import android.widget.Toast;

                import com.android.volley.AuthFailureError;
                import com.android.volley.Request;
                import com.android.volley.RequestQueue;
                import com.android.volley.Response;
                import com.android.volley.VolleyError;
                import com.android.volley.toolbox.StringRequest;
                import com.android.volley.toolbox.Volley;


                import org.json.JSONException;
                import org.json.JSONObject;

                import java.util.HashMap;
                import java.util.Map;

public class UsuarioPaciente extends AppCompatActivity {


    private Button datos;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String email;
    private EditText caso;
    private EditText nuevo;
    private Button button_act;

    private static final String URL = "http://dihi.pe.hu/test2/search.php";
    private static final String URL2 = "http://dihi.pe.hu/test2/actualizar.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_paciente);

        datos = (Button) findViewById(R.id.button29);
        button_act = (Button) findViewById(R.id.button28);
        email = PacienteLogin.token;
        caso = (EditText)  findViewById(R.id.editText17);
        nuevo =  (EditText)  findViewById(R.id.editText18);

        requestQueue = Volley.newRequestQueue(this);

        datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.i("email",email);
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {

                               AlertDialog.Builder Alertconsulta = new AlertDialog.Builder(UsuarioPaciente.this);
                                Alertconsulta.setTitle("Consulta de usuario");
                                Alertconsulta.setMessage(jsonObject.getString("success"));
                                Alertconsulta.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog Alertcon = Alertconsulta.create();
                                Alertcon.show();
                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("email", email);
                        return hashMap;
                    }

                };
                requestQueue.add(request);
            }
        });

        button_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {

                                AlertDialog.Builder Alertconsulta = new AlertDialog.Builder(UsuarioPaciente.this);
                                Alertconsulta.setTitle("Actualización");
                                Alertconsulta.setMessage(jsonObject.getString("success"));
                                Alertconsulta.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog Alertcon = Alertconsulta.create();
                                Alertcon.show();
                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("email", email);
                        hashMap.put("nuevo", nuevo.getText().toString());
                        hashMap.put("caso", caso.getText().toString());
                        return hashMap;
                    }

                };
                requestQueue.add(request);
            }
        });




    }

}
