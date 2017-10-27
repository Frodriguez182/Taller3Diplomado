package com.example.android.carrosmaterial;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditarCarro extends AppCompatActivity {
    private String placa_bun,id,precio_bun;

    private int col,mod,mar,fot;
    private Intent i;

    private EditText txtPlaca;
    private EditText txtPrecio;
    private TextInputLayout cajaPlaca;
    private TextInputLayout cajaPrecio;

    private Resources res;
    private Spinner marca;
    private Spinner modelo;
    private Spinner color;

    private String[] opc1,opc2,opc3;
    private Bundle b;
    private Carro c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_carro);

        txtPlaca = (EditText) findViewById(R.id.txtPlacaEdit);
        txtPrecio = (EditText) findViewById(R.id.txtPrecioEdit);
        res = this.getResources();

        cajaPlaca = (TextInputLayout) findViewById(R.id.cajaPlacaEdit);
        cajaPrecio = (TextInputLayout) findViewById(R.id.cajaPrecioEdit);

        marca = (Spinner) findViewById(R.id.spnMarcaEdit);
        opc1 = res.getStringArray(R.array.marca);
        ArrayAdapter<String> adapter_marca = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc1);
        marca.setAdapter(adapter_marca);

        modelo = (Spinner) findViewById(R.id.spnModeloEdit);
        opc2 = res.getStringArray(R.array.modelo);
        ArrayAdapter<String> adapter_modelo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc2);
        modelo.setAdapter(adapter_modelo);

        color = (Spinner) findViewById(R.id.spnColorEdit);
        opc3 = res.getStringArray(R.array.color);
        ArrayAdapter<String> adapter_color = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc3);
        color.setAdapter(adapter_color);
        i = getIntent();
        b = i.getBundleExtra("datos");

        placa_bun = b.getString("placa");
        precio_bun = b.getString("precio");
        id = b.getString("id");
        col = b.getInt("color");
        mod = b.getInt("modelo");
        mar = b.getInt("marca");
        fot = b.getInt("foto");

        txtPlaca.setText(placa_bun);
        txtPrecio.setText(precio_bun);
        color.setSelection(col);
        modelo.setSelection(mod);
        marca.setSelection(mar);

    }


    public void Editar(View v){
        if (txtPlaca.getText().length() ==0 ){
            Toast.makeText(this, res.getString(R.string.mensaje_error_placa), Toast.LENGTH_SHORT).show();
            txtPlaca.requestFocus();
        }else if (txtPrecio.getText().length() ==0){
            Toast.makeText(this, res.getString(R.string.mensaje_error_precio), Toast.LENGTH_SHORT).show();
            txtPrecio.requestFocus();
        }else {

        String plac = txtPlaca.getText().toString();
        String preci = txtPrecio.getText().toString();

        Carro c = new Carro(id,fot,plac,marca.getSelectedItemPosition(),modelo.getSelectedItemPosition(),color.getSelectedItemPosition(),preci);

        if(placa_bun.equals(plac)){
            c.Modificar();
            Toast.makeText(this, res.getString(R.string.mensaje_modificado), Toast.LENGTH_SHORT).show();
            //Snackbar.make(v, res.getString(R.string.mensaje_modificado), Snackbar.LENGTH_LONG).setAction("action", null).show();
            Cancelar();
        }else{
            if(Metodos.exitencia_carro(Datos.obteberCarros(),plac)){
                txtPlaca.setError(res.getString(R.string.existe_placa));
                txtPlaca.requestFocus();
            }else{
                c.Modificar();
                Toast.makeText(this, res.getString(R.string.mensaje_modificado), Toast.LENGTH_SHORT).show();
                Cancelar();
            }
        }
        }

    }

    public void Cancelar(View v) {
        Cancelar();
    }

    public void Cancelar(){
        String placa_caja,precio_caja;
        int modelo_caja, marca_caja, color_caja;
        placa_caja = txtPlaca.getText().toString();
        precio_caja = txtPrecio.getText().toString();
        modelo_caja = modelo.getSelectedItemPosition();
        marca_caja = marca.getSelectedItemPosition();
        color_caja = color.getSelectedItemPosition();
        Intent i = new Intent(EditarCarro.this,DetalleCarro.class);
        Bundle b = new Bundle();
        b.putInt("marca",marca_caja);
        b.putInt("modelo",modelo_caja);
        b.putString("placa",placa_caja);
        b.putString("id",id);
        b.putString("precio",precio_caja);
        b.putInt("color",color_caja);
        b.putInt("foto",fot);
        i.putExtra("datos",b);

        startActivity(i);
    }
}
