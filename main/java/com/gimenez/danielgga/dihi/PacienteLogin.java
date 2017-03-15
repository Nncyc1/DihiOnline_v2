package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static com.gimenez.danielgga.dihi.R.id.editText;



public class PacienteLogin extends AppCompatActivity {

    private Button button_ingresar;
    private Button button_reg;
    private EditText email, password;
    private RequestQueue requestQueue;
    private static final String URL = "http://192.168.1.6/test2/encuesta.php";
    private static final String URL2 = "http://dihi.pe.hu/test2/user_control2.php";
    private StringRequest request;

    public static String token = null;
    public static String[] id_s = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_login);

        email = (EditText) findViewById(editText);
        password = (EditText) findViewById(R.id.editText2);
        button_ingresar = (Button) findViewById(R.id.button3);
        button_reg = (Button) findViewById(R.id.button4);

        requestQueue = Volley.newRequestQueue(this);

        OnClickRegistrar();


        button_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(token, email.getText().toString());

                token = email.getText().toString();

                request = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {

                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();



                                sendRequest();


                               startActivity(new Intent(getApplicationContext(), EligeEnfermedad.class)); // Revisar clase
                            } else {
                                Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("email", email.getText().toString());
                        hashMap.put("password", password.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });




    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response) {
                showJSON(Response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PacienteLogin.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void showJSON(String json_string) {

        ParseJSON2 pj = new ParseJSON2(json_string);
        pj.parseJSON();
        id_s = ParseJSON2.pregunta;


    }





    public void OnClickRegistrar() {


        button_reg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.prueba");
                        startActivity(intent);
                    }
                }
        );
    }
}
