package com.example.safecallv2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActualizarP extends AppCompatActivity {

    EditText editRut, editNombre, editApellido, editGenero, editEnfermedad,editEdad, editMedicamentos;
    Button bActualizar;
    String id;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_p);
        bActualizar = findViewById(R.id.bActualizar);
        editRut = findViewById(R.id.editRut);
        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editEnfermedad = findViewById(R.id.editEnfermedad);
        editGenero = findViewById(R.id.editGenero);
        editEdad = findViewById(R.id.editEdad);
        editMedicamentos = findViewById(R.id.editMedicamentos);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            editRut.setText(bundle.getString("Rut"));
            editNombre.setText(bundle.getString("Nombre"));
            editApellido.setText(bundle.getString("Apellido"));
            editEnfermedad.setText(bundle.getString("Enfermedad"));
            editGenero.setText(bundle.getString("Genero"));
            editEdad.setText(bundle.getString("Edad"));
            id = bundle.getString("Id");
            editMedicamentos.setText(bundle.getString("Medicamentos"));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Paciente").child(id);

        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatosA();
                Intent intent = new Intent(ActualizarP.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void guardarDatosA(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarP.this);
        builder.setCancelable(false);
        builder.setView(R.layout.barra_progreso);
        AlertDialog dialog = builder.create();
        dialog.show();
        aPaciente();
    }
    public void aPaciente(){
        String rut = editRut.getText().toString().trim();
        String nombre = editNombre.getText().toString().trim();
        String apellido = editApellido.getText().toString().trim();
        String genero = editGenero.getText().toString().trim();
        String enfermedad = editEnfermedad.getText().toString().trim();
        String edad = editEdad.getText().toString().trim();
        String medicamentos = editMedicamentos.getText().toString().trim();

        Paciente dP = new Paciente(id,rut, nombre,apellido, enfermedad,edad,genero, medicamentos, "0", "0", "0");
        databaseReference.setValue(dP).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ActualizarP.this,"Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActualizarP.this, e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}