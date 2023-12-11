package com.example.safecallv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetPersona extends AppCompatActivity {
    TextView Titulo, detNombre, detRut, detApellido,detEdad,detGen,detEnf,detMed;
    FloatingActionButton bBorrar, bEditar;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deta_paciente);
        Titulo = findViewById(R.id.Titulo);
        detNombre = findViewById(R.id.detNombre);
        detRut = findViewById(R.id.detRut);
        detApellido = findViewById(R.id.detApellido);
        detEdad = findViewById(R.id.detEdad);
        detGen = findViewById(R.id.detGen);
        detEnf = findViewById(R.id.detEnf);
        detMed = findViewById(R.id.detMed);
        bBorrar = findViewById(R.id.bBorrar);
        bEditar = findViewById(R.id.bEditar);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            detNombre.setText(bundle.getString("Nombre"));
            detRut.setText(bundle.getString("Rut"));
            detApellido.setText(bundle.getString("Apellido"));
            detEdad.setText(bundle.getString("Edad"));
            detGen.setText(bundle.getString("Genero"));
            detEnf.setText(bundle.getString("Enfermedad"));
            detMed.setText(bundle.getString("Medicamentos"));
            id = bundle.getString("Id");
        }
        bBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Paciente");
                        reference.child(id).removeValue();
                        Toast.makeText(DetPersona.this, "Paciente eliminado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

            }
        });
        bEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetPersona.this, ActualizarP.class)
                        .putExtra("Nombre", detNombre.getText().toString())
                        .putExtra("Rut", detRut.getText().toString())
                        .putExtra("Apellido", detApellido.getText().toString())
                        .putExtra("Edad", detEdad.getText().toString())
                        .putExtra("Genero", detGen.getText().toString())
                        .putExtra("Enfermedad", detEnf.getText().toString())
                        .putExtra("Id", id.toString())
                        .putExtra("Medicamentos", detMed.getText().toString());
                startActivity(intent);
            }
        });
    }
}
