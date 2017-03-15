package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static Button button_paciente;
    private static Button button_medico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickAPaciente();
        OnClickAMedico();
    }

    public void OnClickAPaciente() {

        button_paciente = (Button)findViewById(R.id.button);
        button_paciente.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.PacienteLogin");
                        // Intent intent = new Intent("com.gimenez.danielgga.dihi.Main2Activity");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickAMedico() {

        button_medico = (Button)findViewById(R.id.button2);
        button_medico.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.MedicoLogin");
                        startActivity(intent);
                    }
                }
        );
    }

}
