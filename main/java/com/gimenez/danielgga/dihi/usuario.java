package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class usuario extends AppCompatActivity {

    public String[] send;
    TextView tx_invitacion;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Intent i = getIntent();
        position = i.getExtras().getInt("position");

        send = i.getStringArrayExtra("email");

        tx_invitacion = (TextView) findViewById(R.id.invitacion);

        tx_invitacion.setText(send[position]);

    }
}
