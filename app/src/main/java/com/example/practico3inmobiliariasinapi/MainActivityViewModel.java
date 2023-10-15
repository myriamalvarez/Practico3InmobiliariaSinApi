package com.example.practico3inmobiliariasinapi;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practico3inmobiliariasinapi.modelo.Propietario;
import com.example.practico3inmobiliariasinapi.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> error;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<String> getError(){
        if (error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void verificarDatos(String mail, String password){
        ApiClient apiClient = ApiClient.getApi();
        Propietario propietario = apiClient.login(mail, password);

        if (propietario != null){
            Intent intent = new Intent(context, MenuNavegableActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            error.setValue("Usuario y/o contraseña invalidos");
        }

    }




   /* public void iniciarSesion(String u, String c){
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(u, c);
        if(p != null){
            visible.setValue(api.login(u, c));
            Intent intent = new Intent(context, MenuNavegableActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("propietario", p);
            intent.putExtra("propietario", bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            visible.setValue(View.VISIBLE);
        }
    }**/
}
