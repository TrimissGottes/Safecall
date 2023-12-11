package com.example.safecallv2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;
public class AgregarPacienteActivity extends AppCompatActivity {

     EditText editTextRut;
     EditText editTextNombre;
     EditText editTextApellido;
     EditText editTextEnfermedad;
     EditText editTextEdad;

    EditText editTextGenero;
     EditText editTextMedicamentos;
     EditText editTextRitmoCardiaco;
     EditText editTextTemperatura;
     EditText editTextSaturacion;
     ListView listPaciente;
    private final ArrayList<Paciente> listaPaciente = new ArrayList<>();


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);

        editTextRut = findViewById(R.id.editTextRut);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextEnfermedad = findViewById(R.id.editTextEnfermedad);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextGenero = findViewById(R.id.editTextGenero);
        editTextMedicamentos = findViewById(R.id.editTextMedicamentos);
        editTextRitmoCardiaco = findViewById(R.id.editTextRitmoCardiaco);
        editTextTemperatura = findViewById(R.id.editTextTemperatura);
        editTextSaturacion = findViewById(R.id.editTextSaturacion);

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                subirDatos();
            }
        });
    }

    public void subirDatos(){
        UUID uniqueID = UUID.randomUUID();
        String idu = uniqueID.toString();
        String rpaciente = editTextRut.getText().toString();
        String npaciente = editTextNombre.getText().toString();
        String apaciente = editTextApellido.getText().toString();
        String epaciente = editTextEnfermedad.getText().toString();
        String edpaciente = editTextEdad.getText().toString();
        String genpaciente = editTextGenero.getText().toString();
        String medpaciente = editTextMedicamentos.getText().toString();
        String ritmpaciente = editTextRitmoCardiaco.getText().toString();
        String temppaciente = editTextTemperatura.getText().toString();
        String satpaciente = editTextSaturacion.getText().toString();

        Paciente PacienteClass = new Paciente(idu, rpaciente, npaciente, apaciente,
                epaciente, edpaciente,genpaciente, medpaciente, ritmpaciente,
                temppaciente, satpaciente);
        FirebaseDatabase.getInstance().getReference("Paciente").child(idu)
                .setValue(PacienteClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AgregarPacienteActivity.this,"Paciente Agregado", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AgregarPacienteActivity.this, e.getMessage().toString(),Toast.LENGTH_SHORT);
                    }
                });
    }
}
