package com.example.unbegrenzt.fisicab;

/**
 * Created by unbegrenzt on 07/11/2016.
 */

public class NodoLista {
    // miembros de acceso del paquete, Lista puede utilizarlos directamente
    Object datos;
    NodoLista siguienteNodo;

    // crear un objeto NodoLista que haga referencia a objeto
    NodoLista( Object objeto )
    {
        this( objeto, null );
    }

    // crear objeto NodoLista que haga referencia a objeto
    NodoLista( Object objeto, NodoLista nodo )
    {
        datos = objeto;
        siguienteNodo = nodo;
    }

    // devolver referencia a datos en nodo
    Object obtenerObjeto()
    {
        return datos; // devolver objeto Object en este nodo
    }

    // devolver referencia al siguiente nodo en la lista
    NodoLista obtenerSiguiente()
    {
        return siguienteNodo; // obtener el siguiente nodo
    }
}
