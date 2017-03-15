package com.gimenez.danielgga.dihi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.TextView;


public class EligeEnfermedad extends AppCompatActivity {

    private static Button button_diabetico;
    private static Button button_hipertenso;
    private static Button button_ambos;
    private Button log_out;
    private Button button_usuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elige_enfermedad);

        /* TextView nameView =(TextView) findViewById(R.id.textView69);
        nameView.setText(getIntent().getExtras().getString("userName")); */

        OnClickDiabetico();
        OnClickHipertenso();
        OnClickAmbos();
        OnClickLogOut();
        OnClickUsuario();



    }


    private void OnClickUsuario() {

        button_usuario = (Button)findViewById(R.id.button27);
        button_usuario.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.UsuarioPaciente");
                        startActivity(intent);
                    }
                }
        );

    }

    public void OnClickDiabetico() {

        button_diabetico = (Button)findViewById(R.id.button6);
        button_diabetico.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.EligeAccion");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickHipertenso() {

        button_hipertenso = (Button)findViewById(R.id.button7);
        button_hipertenso.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.Encuesta");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickAmbos() {

        button_ambos = (Button)findViewById(R.id.button8);
        button_ambos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.gimenez.danielgga.dihi.EligeAccion");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickLogOut() {
        log_out = (Button) findViewById(R.id.button13);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PacienteLogin.class));
            }
        });
    }
}
