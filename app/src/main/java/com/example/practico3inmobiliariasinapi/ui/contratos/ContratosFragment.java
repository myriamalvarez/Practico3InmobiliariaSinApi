package com.example.practico3inmobiliariasinapi.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practico3inmobiliariasinapi.R;
import com.example.practico3inmobiliariasinapi.modelo.Inmueble;

import java.util.List;

public class ContratosFragment extends Fragment {

    private RecyclerView rvInmuebles;
    private ContratosViewModel contratosViewModel;
    private InmuebleConContratoAdapter adapter;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.fragment_contratos, container, false);
        context = root.getContext();

        inicializar(root);

        return root;
    }

    private void inicializar(View view)
    {

        rvInmuebles = view.findViewById(R.id.rvInmuebles);

        contratosViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);

        contratosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>()
        {
            @Override
            public void onChanged(List<Inmueble> inmueble)
            {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleConContratoAdapter(context, inmueble, getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
        });

        contratosViewModel.cargarInmueblesConContrato();
    }

}