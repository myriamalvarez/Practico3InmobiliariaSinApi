package com.example.practico3inmobiliariasinapi;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practico3inmobiliariasinapi.modelo.Propietario;
import com.example.practico3inmobiliariasinapi.request.ApiClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practico3inmobiliariasinapi.databinding.ActivityMenuNavegableBinding;

public class MenuNavegableActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuNavegableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuNavegableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenuNavegable.toolbar);
        binding.appBarMenuNavegable.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        inicializarHeader(navigationView);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_perfil, R.id.nav_inmuebles, R.id.nav_inquilinos, R.id.nav_contratos, R.id.nav_cerrar_sesion)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_navegable);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void inicializarHeader(NavigationView navigationView) {

        View header = navigationView.getHeaderView(0);
        ImageView avatar = header.findViewById(R.id.imageView);
        TextView nombrePropietario = header.findViewById(R.id.tvNombre);
        TextView mailPropietario = header.findViewById(R.id.tvMail);
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();
        //Propietario p = (Propietario) getIntent().getBundleExtra("propietario").getSerializable("propietario");
        nombrePropietario.setText(p.getNombre()+ " "+ p.getApellido());
        mailPropietario.setText(p.getEmail());
        avatar.setImageResource(p.getAvatar());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navegable, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_navegable);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}