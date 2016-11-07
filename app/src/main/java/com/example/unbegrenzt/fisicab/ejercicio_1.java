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

    private float veloc_inic;
    public float[] pos_X;
    public float[] pos_Y;

    private XYPlot plot;

    private OnFragmentInteractionListener mListener;

    public ejercicio_1() {
        // Required empty public constructor
    }


    public static ejercicio_1 newInstance(float veloc_inic) {
        ejercicio_1 fragment = new ejercicio_1();
        Bundle args = new Bundle();
        args.putFloat(ARG_VELOC,veloc_inic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            veloc_inic = getArguments().getFloat(ARG_VELOC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.ejercicio_1, container, false);
        if(v != null){
            plot = (XYPlot) v.findViewById(R.id.plot1);
            //x=v0·cosθ·t
            //y=v0·senθ·t-gt2/2
            Number[] yVals = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};
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
    /*private  class Cohete extends View {

        private Drawable personita;
        private Paint paint = new Paint();
        private Paint paint2 = new Paint();
        private Paint paint3 = new Paint();
        private Paint paint4 = new Paint();
        private Paint paint5 = new Paint();
        private float max_x = getActivity().getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        private float max_y = getActivity().getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        private int y = (int)(((int)max_y / 2) / 2);
        private int x = 0;

        public Cohete(Context context) {
            super(context);
        }

        public void mover_objeto(int mover_en_x, int mover_en_y){
            this.x += mover_en_x;
            this.y += mover_en_y;
            invalidate();
        }

        @Override
        public void onDraw(Canvas canvas) {

            paint.setColor(Color.RED);
            paint2.setColor(Color.BLUE);
            paint3.setColor(Color.BLUE);
            paint4.setColor(Color.BLACK);
            paint5.setColor(Color.YELLOW);

            Rect cuerpo = new Rect(x, y, x + 55, y + 20);
            Rect ala_izq = new Rect(x,y - 10, x + 10,y);
            Rect ala_der = new Rect(x,y + 20, x + 10,y + 30);
            Rect niv1 = new Rect(x-10,y, x,y+20);
            Rect niv2 = new Rect(x-20, y+5, x-10,y+17);
            Rect niv3 = new Rect(x-30, y+8, x-20,y+15);

            canvas.drawCircle(x+12,y+10,50,paint5);
            canvas.drawRect(niv1, paint4);
            canvas.drawRect(niv2, paint4);
            canvas.drawRect(niv3, paint4);
            canvas.drawRect(ala_der, paint3);
            canvas.drawRect(ala_izq, paint2);
            canvas.drawRect(cuerpo, paint);
        }
    }*/

}

