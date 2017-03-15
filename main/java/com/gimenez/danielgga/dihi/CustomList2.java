package com.gimenez.danielgga.dihi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by j3sus on 12/03/2017.
 */

public class CustomList2 extends ArrayAdapter<String> {

    public static String[] invitacion;

    // public static String[] interaciones;

    private Activity context;


    public CustomList2(Activity context, String[] invitacion){
        super(context, R.layout.list_view_layout2, invitacion);
        this.context = context;
        this.invitacion = invitacion;
       // this.interaciones = interaciones;

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout2, null, true);
        TextView textViewInv = (TextView) listViewItem.findViewById(R.id.textViewInv);

        // TextView textViewInt = (TextView) listViewItem.findViewById(R.id.textViewInt);

        textViewInv.setText(invitacion[position]);

        // textViewInt.setText(interaciones[position]);


        return listViewItem;
    }



}
