package com.example.android.carrosmaterial;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCarro extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Carro c;
    private String precio,placa, id;
    private int fot, marca,modelo,color;
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private Resources res;
    private TextView plac,marc,model,prec;
    private String [] opc1, opc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);

        plac = (TextView)findViewById(R.id.lblPlacaD);
        marc = (TextView)findViewById(R.id.lblMarcaD);
        model = (TextView)findViewById(R.id.lblModeloD);
        prec = (TextView)findViewById(R.id.lblPrecioD);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collaspsing_toolbar);
        foto = (ImageView) findViewById(R.id.fotoPersona);

        res = this.getResources();
        i = getIntent();
        bundle = i.getBundleExtra("datos");

        marca = bundle.getInt("marca");
        modelo = bundle.getInt("modelo");
        precio = bundle.getString("precio");
        id = bundle.getString("id");
        fot = bundle.getInt("foto");
        placa = bundle.getString("placa");

        opc1 = res.getStringArray(R.array.marca);
        opc2 = res.getStringArray(R.array.modelo);

        collapsingToolbarLayout.setTitle(opc1[marca]+" "+opc2[modelo]);
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,fot,null));
        marc.setText(opc1[marca]);
        model.setText(opc2[modelo]);
        prec.setText(precio);
        plac.setText(placa);

    }

    public  void eliminar(View v){
        String positivo, negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_eliminar_mensaje));
        builder.setMessage(res.getString(R.string.eliminar_mensaje));
        positivo = res.getString(R.string.si_eliminar_mensaje);
        negativo = res.getString(R.string.no_eliminar_mensaje);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Carro c = new Carro(id);
               c.Eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent i=new Intent(DetalleCarro.this,Principal.class);
        startActivity(i);
    }

    public void EditarCarro(View v) {
        Intent i = new Intent(DetalleCarro.this,EditarCarro.class);
        i.putExtra("datos",bundle);
        startActivity(i);
    }

}
