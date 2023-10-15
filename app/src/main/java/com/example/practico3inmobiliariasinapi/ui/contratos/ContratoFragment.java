package com.example.practico3inmobiliariasinapi.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.practico3inmobiliariasinapi.R;
import com.example.practico3inmobiliariasinapi.modelo.Contrato;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private TextView tvCodigoContrato;
    private TextView tvFechaInicio;
    private TextView tvFechaFin;
    private TextView tvMontoAlquiler;
    private TextView tvInquilino;
    private TextView tvInmueble;
    private Button btPagosC;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_contrato, container, false);

        inicializar(root);
        return root;
    }

    private void inicializar(View view)
    {
        tvCodigoContrato = view.findViewById(R.id.tvCodigoContrato);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFin = view.findViewById(R.id.tvFechaFin);
        tvMontoAlquiler = view.findViewById(R.id.tvMontoAqluiler);
        tvInquilino = view.findViewById(R.id.tvInquilino);
        tvInmueble = view.findViewById(R.id.tvInmueble);
        btPagosC = view.findViewById(R.id.btPagosC);

        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        contratoViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>()
        {
            @Override
            public void onChanged(Contrato contrato)
            {
                tvCodigoContrato.setText(contrato.getIdContrato() + "");
                tvFechaInicio.setText(contrato.getFechaInicio());
                tvFechaFin.setText(contrato.getFechaFin());
                tvMontoAlquiler.setText("$" + contrato.getMontoAlquiler());
                tvInquilino.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
                tvInmueble.setText(contrato.getInmueble().getDireccion());
                btPagosC.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(view).navigate(R.id.pagosFragment, bundle);
                    }
                });
            }
        });

        contratoViewModel.cargarContrato(getArguments());
    }

}