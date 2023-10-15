package com.example.practico3inmobiliariasinapi.ui.inquilinos;

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

import java.util.ArrayList;

public class InquilinosFragment extends Fragment {
    private InquilinosViewModel inquilinosViewModel;
    private RecyclerView rvInquilinos;
    private InmuebleConInquilinoAdapter adapter;
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inquilinos, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }



    private void inicializar(View view) {
        inquilinosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(InquilinosViewModel.class);
        rvInquilinos = view.findViewById(R.id.rvInquilinos);
        inquilinosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                adapter = new InmuebleConInquilinoAdapter(context, inmuebles, getLayoutInflater());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
                rvInquilinos.setAdapter(adapter);
                rvInquilinos.setLayoutManager(gridLayoutManager);

            }
        });
        inquilinosViewModel.cargarInmueblesConInquilino();
    }


}