package com.example.unbegrenzt.fisicab;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link f_prom.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link f_prom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f_prom extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String[] datossp = {"--Seleccione--","fuerza","masa","tiempo","velocidad"};
    private EditText txt_fuerza;
    private EditText txt_veloc;
    private EditText txt_tiempo;
    private EditText txt_masa;
    private Spinner spprom;
    private TextView solucion;
    private Button btn_calcular;

    private OnFragmentInteractionListener mListener;

    public f_prom() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f_prom.
     */
    // TODO: Rename and change types and number of parameters
    public static f_prom newInstance(String param1, String param2) {
        f_prom fragment = new f_prom();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.f_prom, container, false);
        if(v != null){
            txt_fuerza = (EditText) v.findViewById(R.id.txt_fuerzaprom);
            txt_veloc = (EditText) v.findViewById(R.id.txt_delta_v);
            txt_tiempo = (EditText) v.findViewById(R.id.txt_delta_t);
            txt_masa = (EditText) v.findViewById(R.id.txt_masa2);
            solucion = (TextView) v.findViewById(R.id.soluc3);
            btn_calcular = (Button) v.findViewById(R.id.btn_calc_f_prom);
            spprom = (Spinner) v.findViewById(R.id.spinner_fprom);
            ArrayAdapter adaptor = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spiner_theme, datossp);
            spprom.setAdapter(adaptor);
        }
        return v;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_calcular.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Float f_prom;
                Float delta_t;
                Float delta_v;
                Float masa;
                Float soluc;
                switch (spprom.getSelectedItemPosition())
                {

                    case 0: {
                        Snackbar.make(view, "Seleccione una opción a calcular", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    }

                    case 1: {
                        if (!(txt_masa.length() == 0) && !(txt_veloc.length() == 0) && !(txt_tiempo.length() == 0)) {
                            masa = Float.valueOf(String.valueOf(txt_masa.getText()));
                            delta_v = Float.valueOf(String.valueOf(txt_veloc.getText()));
                            delta_t = Float.valueOf(String.valueOf(txt_tiempo.getText()));
                            Float temp = delta_v / delta_t;
                            soluc = masa * temp;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza promedio = ?\n"
                                            + "masa = " + Float.toString(masa) + " Kg\n"
                                            + "velocidad = " + Float.toString(delta_v) + " m/s\n"
                                            + "tiempo = " + Float.toString(delta_t) + " s\n\n"
                                            + "fuerza promedio = \n"
                                            + "    " + Float.toString(masa) + " Kg (" + Float.toString(delta_v) + " m/s / " + Float.toString(delta_t) + " s)\n"
                                            + "fuerza promedio = " + Float.toString(soluc) + " N");
                            txt_fuerza.setText(Float.toString(soluc));
                        } else {
                            Snackbar.make(view, "Faltan datos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 2: {
                        if (!(txt_fuerza.length() == 0) && !(txt_tiempo.length() == 0) && !(txt_veloc.length() == 0)) {
                            f_prom = Float.valueOf(String.valueOf(txt_fuerza.getText()));
                            delta_v = Float.valueOf(String.valueOf(txt_veloc.getText()));
                            delta_t = Float.valueOf(String.valueOf(txt_tiempo.getText()));
                            Float temp = f_prom * delta_t;
                            soluc = temp / delta_v;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza promedio = " + Float.toString(f_prom) + " N\n"
                                            + "masa = ?\n"
                                            + "velocidad = " + Float.toString(delta_v) + " m/s\n"
                                            + "tiempo = " + Float.toString(delta_t) + " s\n\n"
                                            + "masa = \n"
                                            + "     (" + Float.toString(f_prom) + " N * " + Float.toString(delta_t) + " s) / " + Float.toString(delta_v) + " m/s)\n"
                                            + "masa = " + Float.toString(soluc) + " Kg");
                            txt_masa.setText(Float.toString(soluc));
                        } else {
                            Snackbar.make(view, "Faltan datos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 3: {
                        if (!(txt_masa.length() == 0) && !(txt_veloc.length() == 0) && !(txt_fuerza.length() == 0)) {
                            f_prom = Float.valueOf(String.valueOf(txt_fuerza.getText()));
                            delta_v = Float.valueOf(String.valueOf(txt_veloc.getText()));
                            masa = Float.valueOf(String.valueOf(txt_masa.getText()));
                            Float temp = masa * delta_v;
                            soluc = temp / f_prom;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza promedio = " + Float.toString(f_prom) + " N\n"
                                            + "masa = " + Float.toString(f_prom) + " Kg\n"
                                            + "velocidad = " + Float.toString(delta_v) + " m/s\n"
                                            + "tiempo = ?\n\n"
                                            + "tiempo = \n"
                                            + "     (" + Float.toString(masa) + " Kg * " + Float.toString(delta_v) + " m/s) / " + Float.toString(f_prom) + " N)\n"
                                            + "tiempo = " + Float.toString(soluc) + " s");
                            txt_tiempo.setText(Float.toString(soluc));
                        } else {
                            Snackbar.make(view, "Faltan datos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 4:{
                        if (!(txt_fuerza.length() == 0) && !(txt_tiempo.length() == 0) && !(txt_masa.length() == 0)) {
                            f_prom = Float.valueOf(String.valueOf(txt_fuerza.getText()));
                            delta_t = Float.valueOf(String.valueOf(txt_tiempo.getText()));
                            masa = Float.valueOf(String.valueOf(txt_masa.getText()));
                            Float temp = f_prom * delta_t;
                            soluc = temp / masa;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza promedio = " + Float.toString(f_prom) + " N\n"
                                            + "masa = " + Float.toString(f_prom) + " Kg\n"
                                            + "velocidad = ?\n"
                                            + "tiempo = " + Float.toString(delta_t) + " s\n\n"
                                            + "velocidad = \n"
                                            + "     (" + Float.toString(f_prom) + " N * " + Float.toString(delta_t) + " s) / " + Float.toString(masa) + " Kg)\n"
                                            + "velocidad = " + Float.toString(soluc) + " m/s");
                            txt_veloc.setText(Float.toString(soluc));
                        } else {
                            Snackbar.make(view, "Faltan datos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    default:{
                        Snackbar.make(view, "Error no conocido", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
