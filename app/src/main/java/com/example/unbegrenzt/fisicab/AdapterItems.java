package com.example.unbegrenzt.fisicab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by unbegrenzt on 17/10/2016.
 */

public class AdapterItems extends ArrayAdapter {
    Activity context;
    items[] datos;

    public AdapterItems(Activity context, items[] datos){
        super(context,R.layout.adaptadorlista,datos);
        this.datos = datos;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptadorlista,null);

        TextView titulo = (TextView) item.findViewById(R.id.txt_titulo);
        titulo.setText(datos[position].getTitulo());
        TextView desc = (TextView) item.findViewById(R.id.txt_descrip);
        desc.setText(datos[position].getDescripcion());
        ImageView imagen = (ImageView) item.findViewById(R.id.img_lista);
        imagen.setImageResource(datos[position].getImg());

        return item;
    }
}
