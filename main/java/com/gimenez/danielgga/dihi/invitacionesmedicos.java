package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

public class invitacionesmedicos extends AppCompatActivity  {

    private Button button_inv;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String email;
    private ListView listView;


    private static final String URL = "http://dihi.pe.hu/test2/listadeinvitaciones.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitacionesmedicos);

        button_inv = (Button) findViewById(R.id.verinv);

        email = MedicoLogin.token2;

        listView = (ListView) findViewById(R.id.listInv);



        requestQueue = Volley.newRequestQueue(this);

        button_inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("email", email);
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("result")) {


                                Log.d("result", response);
                                Log.d("email", response);
                                Log.d("preguntas", response);

                                product[] items ={

                                        new product(response)
                                };

                                showJSON(response);





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


    }

    private void showJSON(String json_string) {

        ParseJSONi pj = new ParseJSONi(json_string);
        pj.parseJSONi();

        CustomList2 cl = new CustomList2(this, ParseJSONi.invitacion);
        listView.setAdapter(cl);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                startActivity(new Intent(getApplicationContext(),usuario.class));
            }
        });




    }







}












