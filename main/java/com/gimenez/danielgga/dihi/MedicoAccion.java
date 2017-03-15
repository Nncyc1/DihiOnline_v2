package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicoAccion extends AppCompatActivity {

    private Button button_invitar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_accion);


        Oninvitaciones();


    }

    private void Oninvitaciones() {

        button_invitar = (Button) findViewById(R.id.invitacion);
        button_invitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),invitacionesmedicos.class));
            }
        });

    }
}
