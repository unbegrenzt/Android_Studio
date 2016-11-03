package com.example.unbegrenzt.fisicab;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link lista_items.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link lista_items#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lista_items extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Fragment estado;
    private boolean activo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private ListView lista;
    private items[] args;

    private OnFragmentInteractionListener mListener;

    public lista_items() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lista_items.
     */
    // TODO: Rename and change types and number of parameters
    public static lista_items newInstance(String param1, String param2) {
        lista_items fragment = new lista_items();
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
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_lista_items, container, false);
        if(v != null){
            //lista = (ListView) v.findViewById(R.id.listaitems);
            args = new items[]{
                    new items("Cantidad de movimiento","P = m * v",R.drawable.r1),
                    new items("Impulso","I = F * t",R.drawable.r2),
                    new items("Fuerza promedio (F)","F = m(v_f) - m(v_i)/(t_f - t_i)",R.drawable.r3),
            };/*
            AdapterItems adap = new AdapterItems(getActivity(),args);
            lista.setAdapter(adap);*/
            AdapterItems adap = new AdapterItems(getActivity(),args);
            setListAdapter(adap);
        }
        return v;
        //return inflater.inflate(R.layout.fragment_lista_items, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos,long id){
        super.onListItemClick(l, v, pos, id);
        if(pos == 0) {
            estado = new momentum_1();
            activo = true;
        }else if(pos == 1){
            estado = new teorem_i();
            activo = true;
        }else if(pos == 2){
            estado = new f_prom();
            activo = true;
        }

        if (activo) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, estado).addToBackStack(null)
                    .commit();
        }
        //Toast.makeText(getActivity(),"este es pruba mia",Toast.LENGTH_LONG).show();
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
