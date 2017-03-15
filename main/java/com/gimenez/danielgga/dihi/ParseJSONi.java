package com.gimenez.danielgga.dihi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by j3sus on 12/03/2017.
 */

public class ParseJSONi {

   // public static String[] ids;

   // public static String[] pregunta;

    public static String[] invitacion;

    // public static String[] interaciones;

    public static final String JSON_ARRAY = "result";

    public JSONArray seccion = null;


    private String json;

    // public static final String KEY_ID = "id";

    // public static final String KEY_PREGUNTA = "pregunta";

    public static final String KEY_INVITACION = "email";

    // public static final String KEY_INTERACIONES = "interaciones";

    public ParseJSONi(String json){
        this.json = json;
    }

    protected void parseJSONi(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);

            seccion = jsonObject.getJSONArray(JSON_ARRAY);

           // ids = new String[seccion.length()];
           // pregunta = new String[seccion.length()];
              invitacion = new String[seccion.length()];
          //    interaciones = new String[seccion.length()];


            for(int i=0;i<seccion.length();i++){

                JSONObject jo = seccion.getJSONObject(i);

             //   ids[i] = jo.getString(KEY_ID);
             //   pregunta[i] = jo.getString(KEY_PREGUNTA);
                invitacion[i] = jo.getString(KEY_INVITACION);
             //   interaciones[i] = jo.getString(KEY_INTERACIONES);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}



