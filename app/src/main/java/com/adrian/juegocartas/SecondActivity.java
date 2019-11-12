package com.adrian.juegocartas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView idcarta;
    private ImageButton idbuttonsubir, idbuttonbajar;
    private ArrayList Cartas;
    private  ArrayList numeroAnterior;
    final static String TAG_RES= "Resultado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Cartas = new ArrayList<>();
        numeroAnterior = new ArrayList<>();
        instancias();
        acciones();
    }
    private void instancias(){
        idcarta = findViewById(R.id.idcarta);
        idbuttonsubir = findViewById(R.id.idbuttonsubir);
        idbuttonbajar = findViewById(R.id.idbuttonbajar);
        List<Integer> listaCartas = Arrays.asList(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5, R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,
                R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13);
        Cartas.addAll(listaCartas);
        numeroAnterior.clear();
        numeroAnterior.add(-1);
    }
    private void acciones(){
        idbuttonsubir.setOnClickListener(this);
        idbuttonbajar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idbuttonsubir:
                Random numeroRandom = new Random();
                int cartaAleatoria = (numeroRandom.nextInt(12 - 0) + 0);
                if(cartaAleatoria > (int) numeroAnterior.get(numeroAnterior.size() - 1)){
                    System.out.println("Suponiendo una carta mas alta");
                    idcarta.setImageDrawable(getDrawable((Integer) Cartas.get(cartaAleatoria)));
                    numeroAnterior.add(cartaAleatoria);
                }else{
                    System.out.println("Carta mayor no encontrada");
                    int recordObtenido = numeroAnterior.size()-1;
                    Intent inputDevuelta = new Intent();
                    inputDevuelta.putExtra(TAG_RES,recordObtenido);
                    setResult(MainActivity.RES_CODE_JUGANDO,inputDevuelta);
                    finish();
                }
                break;
            case R.id.idbuttonbajar:
                Random numeroRandomSubida = new Random();
                int cartaAleatoriaBajada = (numeroRandomSubida.nextInt(12 - 0) + 0);
                if(cartaAleatoriaBajada < (int) numeroAnterior.get(numeroAnterior.size() - 1)){
                    System.out.println("Suponiendo una carta mas alta");
                    idcarta.setImageDrawable(getDrawable((Integer) Cartas.get(cartaAleatoriaBajada)));
                    numeroAnterior.add(cartaAleatoriaBajada);
                }else{
                    System.out.println("Carta mayor no encontrada");
                    int recordObtenido = numeroAnterior.size()-1;
                    Intent inputDevuelta = new Intent();
                    inputDevuelta.putExtra(TAG_RES,recordObtenido);
                    setResult(MainActivity.RES_CODE_JUGANDO,inputDevuelta);
                    finish();
                }
                break;

            default:
        }
    }
}
