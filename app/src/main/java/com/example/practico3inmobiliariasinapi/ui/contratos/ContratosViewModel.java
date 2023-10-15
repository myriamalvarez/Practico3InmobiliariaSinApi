package com.example.practico3inmobiliariasinapi.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practico3inmobiliariasinapi.modelo.Inmueble;
import com.example.practico3inmobiliariasinapi.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    ArrayList<Inmueble> inmueble;
    private Context context;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void cargarInmueblesConContrato() {

        inmueble = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        inmueblesMutable.setValue(inmueble);

    }
}