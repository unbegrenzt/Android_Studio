package com.example.unbegrenzt.fisicab;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.FastLineAndPointRenderer;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.Arrays;


public class ejercicio_1 extends Fragment {
    private static final String ARG_VELOC = "veloc_inic";
    private static final String ARG_ANG = "angulo";
    private static final String ARG_GRAV = "gravedad";

    private double veloc_inic;
    private Lista valores_x = new Lista();
    private Lista valores_y = new Lista();
    private double angulo;
    private double t = 0;
    private double gravedad;
    private XYPlot plot;

    private OnFragmentInteractionListener mListener;

    public ejercicio_1() {
        // Required empty public constructor
    }


    public static ejercicio_1 newInstance(double veloc_inic,double angulo,double gravedad) {
        ejercicio_1 fragment = new ejercicio_1();
        Bundle args = new Bundle();
        args.putDouble(ARG_VELOC,veloc_inic);
        args.putDouble(ARG_ANG,angulo);
        args.putDouble(ARG_GRAV,gravedad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            veloc_inic = getArguments().getDouble(ARG_VELOC);
            angulo = getArguments().getDouble(ARG_ANG);
            gravedad = getArguments().getDouble(ARG_GRAV);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.ejercicio_1, container, false);
        if(v != null){
            plot = (XYPlot) v.findViewById(R.id.plot1);
            int count = 0;
            do {
                //x=v0·cosθ·t
                valores_x.insertarAlFinal((veloc_inic * Math.cos(angulo)) * t);
                //y=v0·senθ·t-gt2/2
                valores_y.insertarAlFinal(((veloc_inic * Math.sin(angulo)) * t) - ((gravedad * (t * t)) / 2));
                if (((veloc_inic * Math.sin(angulo)) * t) - ((gravedad * (t * t)) / 2) <= 0){
                    count ++;
                }
                t = t + 0.1;
            }while (count != 2);

            String x = valores_x.imprimir();
            String[] cad = x.split("¡");
            String y = valores_y.imprimir();
            String[] cad2 = y.split("¡");
            int value = cad2.length * 2;
            boolean band = true;
            Number[] yVals = new Number[value];
            try {
                for (int i = 0; i < ((cad2.length * 2) + 2); i++) {
                    if (band) {
                        if(i == 0) {
                            yVals[i] = 0;
                        }else{
                            yVals[i] = Double.valueOf(cad[i]);
                        }
                        band = false;
                    } else {
                        if(i == 1) {
                            yVals[i] = 0;
                        }else{
                            yVals[i] = Double.valueOf(cad2[i]);
                        }
                        band = true;
                    }
                }
            }catch (Exception e){

            }
            XYSeries sl = new SimpleXYSeries(
                    Arrays.asList(yVals), SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "Trayectoria");
            plot.addSeries(sl, new FastLineAndPointRenderer.Formatter(Color.GREEN, Color.RED, null, null));
            PanZoom.attach(plot);
        }
        return v;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

