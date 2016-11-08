package com.example.unbegrenzt.fisicab;

/**
 * Created by unbegrenzt on 07/11/2016.
 */

public class Lista {
    private NodoLista primerNodo;
    private NodoLista ultimoNodo;
    private String nombre;  // cadena como �lista�, utilizada para imprimir

    // construir Lista vacia con �Lista� como nombre
    public Lista()
    {
        this( "lista" );
    }

    // construir una Lista vacia con un nombre
    public Lista( String nombreLista)
    {
        nombre = nombreLista;
        setPrimerNodo(ultimoNodo = null);
    }

    public int cantidad (){
        int cant = 0;
        NodoLista reco = primerNodo;
        while (reco != null) {
            reco = reco.siguienteNodo;
            cant++;
        }
        return cant;
    }

    public synchronized void insertar_medio (int pos, Object elementoInsertar){
        if (pos <= cantidad () + 1)    {
            NodoLista nuevo;
            if (pos == 1){
                nuevo = new NodoLista(elementoInsertar,primerNodo);
                nuevo.siguienteNodo = primerNodo;
                primerNodo = nuevo;
            } else
            if (pos == cantidad () + 1)    {
                NodoLista reco = primerNodo;
                while (reco.siguienteNodo != null) {
                    reco = reco.siguienteNodo;
                }
                nuevo = new NodoLista(elementoInsertar);
                reco.siguienteNodo = nuevo;
                nuevo.siguienteNodo = null;
            } else {
                NodoLista reco = primerNodo;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.siguienteNodo;
                NodoLista siguiente = reco.siguienteNodo;
                nuevo = new NodoLista(elementoInsertar);
                reco.siguienteNodo = nuevo;
                nuevo.siguienteNodo = siguiente;
            }
        }
    }

    public synchronized void borrar_medio (int pos){
        if (pos <= cantidad ()){
            if (pos == 1) {
                primerNodo = primerNodo.siguienteNodo;
            } else {
                NodoLista reco = primerNodo;
                for (int f = 1 ; f <= pos - 2 ; f++)
                    reco = reco.siguienteNodo;
                NodoLista prox = reco.siguienteNodo;
                reco.siguienteNodo = prox.siguienteNodo;
            }
        }
    }

    public synchronized String super_mega_impresion_mejorada_4k(int pos){
        if ( estaVacia() ) {
            String cadena ="La cola de " + nombre + " esta vacia. ";
            return cadena;
        }
        String cadena = "";
        int i = 0;
        NodoLista actual = getPrimerNodo();
		      /* mientras no se encuentre el final de la lista, imprimir los datos del nodo actual */
        while ( actual != null ) {
            if(i == pos){
                cadena = actual.datos.toString();
                break;
            }
            actual = actual.siguienteNodo;
        }
        return cadena;
    }

    public synchronized int super_mega_impresion_mejorada_4k(String pos){
        if ( estaVacia() ) {
            int num = -1;
            return num;
        }
        int i = -1;
        NodoLista actual = getPrimerNodo();
		      /* mientras no se encuentre el final de la lista, imprimir los datos del nodo actual */
        while ( actual != null ) {
            i++;
            if(pos.compareToIgnoreCase(actual.datos.toString()) == 1){
                break;
            }
            actual = actual.siguienteNodo;

        }
        return i;
    }

    // insertar objeto Object al frente de Lista
    public synchronized void insertaAlFrente( Object elementoInsertar )
    {
        if ( estaVacia() ) /* primerNodo y ultimoNodo hacen referencia al mismo bjeto Object */
            setPrimerNodo(ultimoNodo = new NodoLista( elementoInsertar));
        else // primer nodo hace referencia a un nuevo nodo
            setPrimerNodo(new NodoLista( elementoInsertar, getPrimerNodo() ));
    }

    public synchronized void insertarAlMedio(Object elementoInsertar, int posicion)
    {
        NodoLista temporal = getPrimerNodo();
        int i = 0;

        while ((temporal != null) && (i != posicion)){
            temporal = temporal.siguienteNodo;
            i++;
        }

        if(i == posicion){
            setPrimerNodo(new NodoLista(elementoInsertar,ultimoNodo));
        }else{
            setPrimerNodo(new NodoLista("ser op"));
        }

    }

    // insertar objeto Object al final de Lista
    public synchronized void insertarAlFinal( Object elementoInsertar )
    {
        if ( estaVacia() ) /* primerNodo y ultimoNodo hacen referencia al mismo objeto Object */
            setPrimerNodo(ultimoNodo = new NodoLista( elementoInsertar ));
        else // el objeto siguienteNodo que va despu�s de ultimoNodo Hace referencia a un nuevo nodo */
            ultimoNodo = ultimoNodo.siguienteNodo = new NodoLista(elementoInsertar);
    }

    // eliminar primer nodo de la Lista
    public synchronized Object eliminarDelFrente() throws ExcepcionListaVacia
    {
        if ( estaVacia() ) // lanzar excepcion si lista esta vacia
            throw new ExcepcionListaVacia( nombre );

     	  /* recuperar los datos que se van a eliminar */
        Object elementoEliminado = getPrimerNodo().datos;
        // actualizar las referencias primerNodo y ultimoNodo
        if ( getPrimerNodo() == ultimoNodo )
            setPrimerNodo(ultimoNodo = null);
        else
            setPrimerNodo(getPrimerNodo().siguienteNodo);
        return elementoEliminado; // devolver datos del nodo eliminado
    } // fin del metodo eliminarDelFrente

    // eliminar ultimo nodo de la Lista
    public synchronized Object eliminarDelFinal() throws ExcepcionListaVacia
    {
        if (estaVacia() ) // lanzar excepcion de Lista esta vacia
            throw new ExcepcionListaVacia( nombre );
	      /* recuperar los datos que se van a eliminar */
        Object elementoEliminado = ultimoNodo.datos;

        // actualizar las referencias primerno y ultimoNodo
        if ( getPrimerNodo() == ultimoNodo )
            setPrimerNodo(ultimoNodo = null);
        else { // localizar nuevo ultimo nodo
            NodoLista actual = getPrimerNodo();
            // iterar mientras nodo actual no haga referencia al ultimo nodo
            while ( actual.siguienteNodo != ultimoNodo )
                actual = actual.siguienteNodo;

            ultimoNodo = actual; // actual es el nuevo ultimoNodo
            actual.siguienteNodo = null;
        }
        return elementoEliminado; // devolver datos del nodo eliminado
    } // fin del metodo eliminarDelFinal

    // determinar si la Lista esta vacia
    public synchronized boolean estaVacia()
    {
        return getPrimerNodo() == null; // devolver trae si la Lista esta vacia
    }


    // mostrar el contenido de la Lista
    public synchronized String imprimir()
    {
        if ( estaVacia() ) {
            String cadena ="vacia";
            return cadena;
        }
        String cadena = "";
        NodoLista actual = getPrimerNodo();
		      /* mientras no se encuentre el final de la lista, imprimir los datos del nodo actual */
        while ( actual != null ) {
            cadena = cadena.concat( actual.datos.toString() + "¡" );
            actual = actual.siguienteNodo;
        }
        return cadena;
    }
    public synchronized String buscar(String cadena){
        NodoLista actual = getPrimerNodo();
        int i = 1;
        while ( actual != null ) {
            if(actual.datos.toString().compareTo(cadena) == 1){
                String nuv_cad = actual.datos.toString() + " se encuentra en la posicion "+ i;
                return nuv_cad;
            }
            actual = actual.siguienteNodo;
            i = i + 1;
        }
        return "no se encontró";
    }

    public NodoLista getPrimerNodo() {
        return primerNodo;
    }

    public void setPrimerNodo(NodoLista primerNodo) {
        this.primerNodo = primerNodo;
    }
}
