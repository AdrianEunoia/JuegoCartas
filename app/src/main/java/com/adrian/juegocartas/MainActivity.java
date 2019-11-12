package com.adrian.juegocartas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button idbuttonempezar;
    private CheckBox idcheckpracticar;
    private TextView idtextviewpuntacion;
    private boolean flagRecogerResultado;
    final static int REQ_CODE = 1, RES_CODE_PRACTICANDO = 0, RES_CODE_JUGANDO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
    }

    private void instancias(){
        idbuttonempezar = findViewById(R.id.idbuttonempezar);
        idcheckpracticar = findViewById(R.id.idcheckpracticar);
        idtextviewpuntacion = findViewById(R.id.idtextviewpuntacion);
    }
    private void acciones(){
        idbuttonempezar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idbuttonempezar:
                if (idcheckpracticar.isChecked()){
                    System.out.println("Empezando juego de practica...");
                    flagRecogerResultado=false;
                    Intent actividadCarta = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivityForResult(actividadCarta, REQ_CODE);
                }else{
                    System.out.println("Empezando juego...");
                    flagRecogerResultado=true;
                    Intent actividadCarta = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivityForResult(actividadCarta, REQ_CODE);
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE && resultCode==RES_CODE_JUGANDO && !idcheckpracticar.isChecked()){
            int resultadoDevuelto = (int) data.getExtras().get(SecondActivity.TAG_RES);
            int resultadoActual =  Integer.valueOf(idtextviewpuntacion.getText().toString());
            if(resultadoDevuelto>resultadoActual){
                idtextviewpuntacion.setText(String.valueOf(resultadoDevuelto));
            } else {
                System.out.println("Record no superado");
            }
        }else if(requestCode==REQ_CODE && resultCode==RES_CODE_PRACTICANDO){

        }
    }
}
