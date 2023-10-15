package com.example.practico3inmobiliariasinapi.ui.cerrarSesion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CerrarSesionViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CerrarSesionViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Cierre de sesion");
    }
    public LiveData<String> getText(){
        return mText;
    }
}