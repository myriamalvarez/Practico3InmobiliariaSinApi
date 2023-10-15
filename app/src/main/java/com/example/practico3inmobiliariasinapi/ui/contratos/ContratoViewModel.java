package com.example.practico3inmobiliariasinapi.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practico3inmobiliariasinapi.modelo.Contrato;
import com.example.practico3inmobiliariasinapi.modelo.Inmueble;
import com.example.practico3inmobiliariasinapi.request.ApiClient;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contratoMutable;
    private Context context;

    public ContratoViewModel(@NonNull Application application)
    {
        super(application);
        context= application.getApplicationContext();

    }

    public LiveData<Contrato> getContrato()
    {
        if (contratoMutable == null)
        {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;

    }

    //aca recibimos un inmueble y se busca el contrato vigente
    public void cargarContrato(Bundle bundle)
    {
        Inmueble inmueble = (Inmueble) bundle.get("contrato");
        ApiClient apiClient= ApiClient.getApi();

        //obtenemos el contrato de ese Inmueble que viene en bundle...
        Contrato contrato = apiClient.obtenerContratoVigente(inmueble);
        contratoMutable.setValue(contrato);

    }
}