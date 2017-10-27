package com.example.android.carrosmaterial;

/**
 * Created by android on 21/10/2017.
 */

public class Carro {

    private String id;
    private int foto;
    private String placa;
    private String precio;

    private int marca;
    private int modelo;
    private int color;

    public Carro(){

    }

    public Carro (String id){
        this.id=id;
    }

    public Carro(String id,int foto, String placa, int marca, int modelo, int color, String precio) {
        this.foto = foto;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.id=id;
    }

    public Carro(int foto, String placa, int marca, int modelo, int color, String precio) {
        this.foto = foto;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.id=id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void guardar(){
        Datos.guardarCarro(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void Eliminar(){
        Datos.eliminarCarros(this);
    }

    public void Modificar(){
        Datos.EditarCarro(this);
    }
}
