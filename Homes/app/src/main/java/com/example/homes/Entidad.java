package com.example.homes;

import java.io.Serializable;

public class Entidad implements Serializable {
    private String nombre ;
    private String ciudad ;

    public Entidad(String nombre,String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }
}
