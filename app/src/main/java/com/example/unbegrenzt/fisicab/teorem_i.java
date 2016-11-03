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
 * {@link teorem_i.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link teorem_i#newInstance} factory method to
 * create an instance of this fragment.
 */
public class teorem_i extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String[]datossp = {"--Seleccione--","Impulso","Tiempo","Fuerza"};
    private EditText txt_fuerza;
    private EditText txt_tiempo;
    private EditText txt_impulso;
    private TextView solucion;
    private Button btn_calcular;
    private Spinner spiner;

    private OnFragmentInteractionListener mListener;

    public teorem_i() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teorem_i.
     */
    // TODO: Rename and change types and number of parameters
    public static teorem_i newInstance(String param1, String param2) {
        teorem_i fragment = new teorem_i();
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
        View v =  inflater.inflate(R.layout.teorem_i, container, false);
        if(v != null){
            txt_fuerza = (EditText) v.findViewById(R.id.txt_fuerza);
            txt_tiempo = (EditText) v.findViewById(R.id.txt_tiempo);
            txt_impulso = (EditText) v.findViewById(R.id.txt_impulso);
            btn_calcular = (Button) v.findViewById(R.id.btn_cal2);
            solucion = (TextView) v.findViewById(R.id.soluc2);
            spiner = (Spinner) v.findViewById(R.id.spinner_impulso);
            ArrayAdapter adaptor = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spiner_theme, datossp);
            spiner.setAdapter(adaptor);
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
                Float fuerza;
                Float tiempo;
                Float impulso;
                Float soluc;
                switch (spiner.getSelectedItemPosition())
                {

                    case 0: {
                        Snackbar.make(view, "Seleccione una opción a calcular", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    }

                    case 1: {
                        if (!(txt_fuerza.length() == 0) && !(txt_tiempo.length() == 0)) {
                            fuerza = Float.valueOf(String.valueOf(txt_fuerza.getText()));
                            tiempo = Float.valueOf(String.valueOf(txt_tiempo.getText()));
                            soluc = fuerza * tiempo;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza = " + Float.toString(fuerza) + " N\n"
                                            + "tiempo = " + Float.toString(tiempo) + " s\n"
                                            + "impulso = ?\n\n"
                                            + "impulso = " + Float.toString(fuerza) + " N * " + Float.toString(tiempo) + " s\n"
                                            + "impulso = " + Float.toString(soluc) + " N*s");
                            txt_impulso.setText(Float.toString(soluc));
                        } else {
                            Snackbar.make(view, "Faltan datos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 2: {
                        if (!(txt_impulso.length() == 0) && !(txt_fuerza.length() == 0)) {
                            impulso = Float.valueOf(String.valueOf(txt_impulso.getText()));
                            fuerza = Float.valueOf(String.valueOf(txt_fuerza.getText()));
                            soluc = impulso / fuerza;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza = " + Float.toString(fuerza) + " N\n"
                                            + "tiempo = ?\n"
                                            + "impulso =" + Float.toString(impulso) + " N*s\n\n"
                                            + "tiempo = " + Float.toString(impulso) + " N*s / " + Float.toString(fuerza) + " N\n"
                                            + "tiempo = " + Float.toString(soluc) + " s");
                            txt_tiempo.setText(Float.toString(soluc));
                        } else {
                            Snackbar.make(view, "Faltan datos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 3: {
                        if (!(txt_impulso.length() == 0) && !(txt_tiempo.length() == 0)) {
                            impulso = Float.valueOf(String.valueOf(txt_impulso.getText()));
                            tiempo = Float.valueOf(String.valueOf(txt_tiempo.getText()));
                            soluc = impulso / tiempo;
                            solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "fuerza = ?\n"
                                            + "tiempo = " + Float.toString(tiempo) + " s\n"
                                            + "impulso = " + Float.toString(impulso) + " N*s\n\n"
                                            + "fuerza = " + Float.toString(impulso) + " N*s / " + Float.toString(tiempo) + " s \n"
                                            + "fuerza = " + Float.toString(soluc) + " N");
                            txt_fuerza.setText(Float.toString(soluc));
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
