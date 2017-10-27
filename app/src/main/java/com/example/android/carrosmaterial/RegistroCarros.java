package com.example.android.carrosmaterial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroCarros extends AppCompatActivity {

    private EditText txtPlaca,txtPrecio;
    private TextInputLayout cajaPlaca,cajaPrecio;
    private ArrayList<Integer> fotos;
    private Resources res;
    private Spinner marca,modelo,color;
    private ArrayAdapter<String> adapter;
    private String [] opc;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_carros);
        context = this;

        txtPlaca = (EditText)findViewById(R.id.txtPlaca);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);

        res = this.getResources();
        cajaPlaca = (TextInputLayout) findViewById(R.id.cajaPlaca);
        cajaPrecio = (TextInputLayout) findViewById(R.id.cajaPrecio);

        marca = (Spinner) findViewById(R.id.spnMarca);
        opc = res.getStringArray(R.array.marca);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        marca.setAdapter(adapter);

        modelo = (Spinner) findViewById(R.id.spnModelo);
        opc = res.getStringArray(R.array.modelo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        modelo.setAdapter(adapter);


        color = (Spinner) findViewById(R.id.spnColor);
        opc = res.getStringArray(R.array.color);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc);
        color.setAdapter(adapter);


        iniciar_fotos();


    }

    public void iniciar_fotos() {

        fotos = new ArrayList<>();
        fotos.add(R.drawable.renault);
        fotos.add(R.drawable.chevrolet);
        fotos.add(R.drawable.kia);

    }

    public void guardar (View v){
        if (validar()) {
            String placa_caja;
            placa_caja = txtPlaca.getText().toString();



            if (Datos.ExistePlaca1(placa_caja)) {
                Toast.makeText(this, res.getString(R.string.existe_placa), Toast.LENGTH_SHORT).show();
                txtPlaca.setText("");
                txtPlaca.requestFocus();
            } else {

                Carro c = new Carro(Metodos.fotoAleatoria(fotos), txtPlaca.getText().toString(), marca.getSelectedItemPosition(), modelo.getSelectedItemPosition(), color.getSelectedItemPosition(),
                        txtPrecio.getText().toString());


                c.guardar();


                Snackbar.make(v, res.getString(R.string.registro_guardado), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                limpiar();
            }
        }
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        txtPlaca.setText("");
        txtPrecio.setText("");
        marca.setSelection(0);
        color.setSelection(0);
        modelo.setSelection(0);
    }

    public void onBackPressed(){
        finish();
        Intent i=new Intent(RegistroCarros.this,Principal.class);
        startActivity(i);
    }

    public boolean validar(){
        if (validar_aux(txtPlaca,cajaPlaca))return false;
        else if (validar_aux(txtPrecio,cajaPrecio))return false;

        return true;
    }

    public boolean validar_aux(TextView t, TextInputLayout ct){
        if (t.getText().toString().isEmpty()){
            t.requestFocus();
            t.setError("No puede ser vacio");
            return  true;
        }
        return false;
    }
}
