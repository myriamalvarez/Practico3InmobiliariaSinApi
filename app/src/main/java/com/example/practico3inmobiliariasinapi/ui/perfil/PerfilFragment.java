package com.example.practico3inmobiliariasinapi.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.practico3inmobiliariasinapi.R;
import com.example.practico3inmobiliariasinapi.modelo.Propietario;

public class PerfilFragment extends Fragment {
    private PerfilViewModel perfilVm;
    private EditText etId;
    private EditText etDni;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etEmail;
    private EditText etContraseña;
    private EditText etTelefono;
    private Button btEditar;
    private Button btGuardar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializarVista(root);
        return root;
    }

    private void inicializarVista( View root) {
        etId = root.findViewById(R.id.etId);
        etDni = root.findViewById(R.id.etDNI);
        etNombre = root.findViewById(R.id.etNombre);
        etApellido = root.findViewById(R.id.etApellido);
        etEmail = root.findViewById(R.id.etEmail);
        etContraseña = root.findViewById(R.id.etContraseña);
        etTelefono = root.findViewById(R.id.etTelefono);
        btEditar = root.findViewById(R.id.btEditar);
        btEditar = root.findViewById(R.id.btEditar);
        btGuardar =root.findViewById(R.id.btGuardar);
        perfilVm =  ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        perfilVm.getPerfilMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etId.setText(propietario.getId()+"");
                etDni.setText(propietario.getDni().toString());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etContraseña.setText(propietario.getContraseña());
                etTelefono.setText(propietario.getTelefono());

            }
        });
        perfilVm.cargarDatos();
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btEditar.setVisibility(View.GONE);
                btGuardar.setVisibility(View.VISIBLE);
                etDni.setEnabled(true);
                etNombre.setEnabled(true);
                etApellido.setEnabled(true);
                etNombre.setEnabled(true);
                etEmail.setEnabled(true);
                etContraseña.setEnabled(true);
                etTelefono.setEnabled(true);

            }
        });
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario propietario = new Propietario(Integer.valueOf(etId.getText().toString()),
                        etDni.getText().toString(),
                        etNombre.getText().toString(),
                        etApellido.getText().toString(),
                        etEmail.getText().toString(),
                        etContraseña.getText().toString(),
                        etTelefono.getText().toString());
                perfilVm.guardarCambios(propietario);
                btEditar.setVisibility(View.VISIBLE);
                btGuardar.setVisibility(View.GONE);
                etDni.setEnabled(false);
                etNombre.setEnabled(false);
                etApellido.setEnabled(false);
                etTelefono.setEnabled(false);

            }
        });

    }

}