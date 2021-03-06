package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class prueba extends AppCompatActivity {

    private Button atras;
    private Button button_registro;
    private EditText email,password,edad, telefono, sexo, enfermedad, direccion, sangre, CI;
    private RequestQueue requestQueue;
    private static final String URL = "http://dihi.pe.hu/test2/prueba.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        OnClickAtras();


        email = (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.editText10);
        edad = (EditText) findViewById(R.id.editText8);
        telefono = (EditText) findViewById(R.id.editText9);
        sexo = (EditText) findViewById(R.id.editText11);
        enfermedad = (EditText) findViewById(R.id.editText12);
        direccion = (EditText) findViewById(R.id.editText13);
        sangre = (EditText) findViewById(R.id.editText15);
        CI = (EditText) findViewById(R.id.editText14);

        button_registro = (Button) findViewById(R.id.button16);

        requestQueue = Volley.newRequestQueue(this);

        button_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(),"SUCCESS "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),PacienteLogin.class));
                            }else {
                                Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("email",email.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        hashMap.put("edad",edad.getText().toString());
                        hashMap.put("telefono",telefono.getText().toString());
                        hashMap.put("sexo",sexo.getText().toString());
                        hashMap.put("id_enfermedad",enfermedad.getText().toString());
                        hashMap.put("direccion",direccion.getText().toString());
                        hashMap.put("sangre",sangre.getText().toString());
                        hashMap.put("CI",CI.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }

    private void OnClickAtras() {
        atras = (Button) findViewById(R.id.button17);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PacienteLogin.class));
            }
        });

    }
}
