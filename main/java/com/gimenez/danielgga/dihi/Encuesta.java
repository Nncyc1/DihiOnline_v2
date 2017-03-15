package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encuesta extends AppCompatActivity implements View.OnClickListener{

    public static final String URL = "http://dihi.pe.hu/test2/respaldodeencuesta.php";

    private static final String URL2 = "http://dihi.pe.hu/test2/respuestas.php";


    private Button buttonGet, buttonSet;

    private ListView listView;

    public String answers;

    private String email;

    public List<String> questions = new ArrayList<>();

    private RequestQueue requestQueue;

    private StringRequest request;

    public static String id_p = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        email = PacienteLogin.token;
        requestQueue = Volley.newRequestQueue(this);

        buttonSet = (Button) findViewById(R.id.buttonSend);

        OnClickAtras();


    }

    private void OnClickAtras() {
        buttonSet = (Button) findViewById(R.id.buttonSend);
        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> selections = new ArrayList<String>();



                for (int i = 0; i < listView.getCount(); i++) {

                    Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
                    String text = mySpinner.getSelectedItem().toString();

                    TextView MyId = (TextView) findViewById(R.id.textViewId);

                    answers = text;
                    id_p = MyId.getText().toString();

                    Log.i(answers, mySpinner.getSelectedItem().toString());
                    Log.i(id_p, MyId.getText().toString());



                    request = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {



                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {

                                   Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

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
                            hashMap.put("id_seccion",id_p);
                            hashMap.put("email", email);
                            hashMap.put("respuesta", answers);

                            Log.i("email",email);
                            Log.i("id_seccion",id_p);
                            Log.i("respuesta",answers);

                            return hashMap;
                        }
                    };

                    requestQueue.add(request);





                }


               // startActivity(new Intent(getApplicationContext(), EligeEnfermedad.class));


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
                Toast.makeText(Encuesta.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void showJSON(String json_string) {

        ParseJSON pj = new ParseJSON(json_string);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.ids,ParseJSON.pregunta);
        listView.setAdapter(cl);

    }



    @Override
    public void onClick(View view) {

        sendRequest();
    }
}
