package com.example.unbegrenzt.fisicab;

/**
 * Created by unbegrenzt on 17/10/2016.
 */

public class items {
    private String titulo, descripcion;
    private int img;

    public items(){
    }

    public items(String titulo,String descripcion, int imagen){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
