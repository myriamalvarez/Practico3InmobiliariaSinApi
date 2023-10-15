package com.example.practico3inmobiliariasinapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText etUsuario, etPassword;
    private Button btLogin;
    private MainActivityViewModel viewModel;
    private SensorManager sensorManager;
    private Sensor acelerometro;
    private long ultimoRegistro = 0;
    private float ultimo_x, ultimo_y, ultimo_z;
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1100);
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);

        inicializar();
    }
    private void inicializar(){
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        Log.d("Salida", btLogin.toString());
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });
    }
    public void login(View view){
        String mail = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        viewModel.verificarDatos(mail, password);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor miSensor = event.sensor;

        if(miSensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long tiempoActual = System.currentTimeMillis();

            if ((tiempoActual - ultimoRegistro) > 100){
                long difTiempo = (tiempoActual - ultimoRegistro);
                ultimoRegistro = tiempoActual;

                float velocidad = Math.abs(x + y + z - ultimo_x - ultimo_y - ultimo_z)/difTiempo * 10000;

                if (velocidad > SHAKE_THRESHOLD){
                    llamarInmobiliaria();
                }
                ultimo_x = x;
                ultimo_y = y;
                ultimo_z = z;

            }

        }
    }
    private void llamarInmobiliaria() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: 999" ));
        startActivity(intent);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }
}