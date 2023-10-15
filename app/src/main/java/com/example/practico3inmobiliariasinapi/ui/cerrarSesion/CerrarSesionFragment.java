package com.example.practico3inmobiliariasinapi.ui.cerrarSesion;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practico3inmobiliariasinapi.R;

public class CerrarSesionFragment extends Fragment {

    private CerrarSesionViewModel mViewModel;

    /*public static CerrarSesionFragment newInstance() {
        return new CerrarSesionFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
        mViewModel = new ViewModelProvider(this).get(CerrarSesionViewModel.class);
        ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(CerrarSesionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
        cerrarSesion();
        return root;
    }

    private void cerrarSesion(){
        new AlertDialog.Builder(getContext())
                .setTitle("Cerrar Sesion")
                .setMessage("Esta seguro que desea salir de la aplicacion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();

    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CerrarSesionViewModel.class);
        // TODO: Use the ViewModel
    }*/

}