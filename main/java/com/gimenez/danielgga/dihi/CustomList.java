package com.gimenez.danielgga.dihi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by j3sus on 07/03/2017.
 */

public class CustomList extends ArrayAdapter<String>{

    private String[] pregunta;
    private String[] ids;
    private Activity context;


    public CustomList(Activity context, String[] ids, String[] pregunta){
        super(context, R.layout.list_view_layout, pregunta);
        this.context = context;
        this.ids = ids;
        this.pregunta = pregunta;

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewPregunta = (TextView) listViewItem.findViewById(R.id.textViewPregunta);

        textViewId.setText(ids[position]);
        textViewPregunta.setText(pregunta[position]);

        return listViewItem;
    }




}
