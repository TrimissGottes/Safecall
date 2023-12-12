package com.example.safecallv8;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 5000; // Duración en milisegundos (5 segundos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura un temporizador para mostrar la actividad principal después del tiempo especificado
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                finish(); // Cierra esta actividad para evitar volver a ella con el botón "Atrás"
            }
        }, SPLASH_DURATION);
    }
}