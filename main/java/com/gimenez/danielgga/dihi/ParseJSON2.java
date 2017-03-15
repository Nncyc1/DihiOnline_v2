package com.gimenez.danielgga.dihi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by j3sus on 08/03/2017.
 */

public class ParseJSON2 {

    public static String[] pregunta;

    public static final String JSON_ARRAY = "result";

    public JSONArray seccion = null;


    private String json;



    public static final String KEY_ID = "pregunta";

    public ParseJSON2(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);

            seccion = jsonObject.getJSONArray(JSON_ARRAY);

            pregunta = new String[seccion.length()];


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
