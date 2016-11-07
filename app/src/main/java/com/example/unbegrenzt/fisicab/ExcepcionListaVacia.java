package com.example.unbegrenzt.fisicab;

/**
 * Created by unbegrenzt on 07/11/2016.
 */

public class ExcepcionListaVacia extends RuntimeException {
    // constructor sin argumentos
    public ExcepcionListaVacia ()
    {
        this( "Lista" );
    }

    // constructor
    public ExcepcionListaVacia ( String nombre )
    {
        super( nombre + " esta vacia" );
    }
}
