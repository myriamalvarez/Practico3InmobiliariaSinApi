package com.example.practico3inmobiliariasinapi.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practico3inmobiliariasinapi.modelo.Inmueble;
import com.example.practico3inmobiliariasinapi.modelo.Inquilino;
import com.example.practico3inmobiliariasinapi.request.ApiClient;

public class InquilinoViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilinoMutable;

    public InquilinoViewModel() {
        super();
    }

    public LiveData<Inquilino> getInquilino() {
        if (inquilinoMutable == null) {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }

    public void cargarInquilino(Bundle bundle) {

        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient apiClient= ApiClient.getApi();
        Inquilino inquilino = apiClient.obtenerInquilino(inmueble);
        this.inquilinoMutable.setValue(inquilino);



    }
}