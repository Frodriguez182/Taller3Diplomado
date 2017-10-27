package com.example.android.carrosmaterial;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by android on 21/10/2017.
 */

public class Datos {

    private static String db = "Carros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static ArrayList<Carro> carros = new ArrayList();

    public static void guardarCarro (Carro c){
        c.setId(databaseReference.push().getKey());
        databaseReference.child(db).child(c.getId()).setValue(c);

    }

    public static void setCarros(ArrayList<Carro> carros){
        Datos.carros = carros;
    }

    public static  ArrayList<Carro>obteberCarros(){
        return carros;
    }



    public static void eliminarCarros(Carro c){
        databaseReference.child(db).child(c.getId()).removeValue();
    }

    public static int ExistePlaca(String placa){
        Log.i("TEST",""+carros.size());
        for (int i = 0; i < carros.size(); i++) {
            if (carros.get(i).getPlaca().equalsIgnoreCase(placa)){
                return i;
            }
        }
        return -1;

    }

    public static boolean ExistePlaca1(String placa){

        for (int i = 0; i < carros.size(); i++) {
            if (carros.get(i).getPlaca().equalsIgnoreCase(placa)){
                return true;
            }
        }
        return false;

    }

    public static void EditarCarro(Carro c) {
        databaseReference.child(db).child(c.getId()).setValue(c);

    }


}
