package com.example.safecallv8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtxtEmail;
    EditText edtxtPassword;
    Button enviarbtn;
    Button registrobtn;
    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxtEmail=findViewById(R.id.edtxtEmail);

        edtxtPassword=findViewById(R.id.edtxtPassword);
        enviarbtn=findViewById(R.id.enviarbtn);
        registrobtn=findViewById(R.id.registrobtn);

        registrobtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void obClick(View v){
                Intent i= new Intent(LoguinActivity.this,RegistroActivity.class);
                startActivity(i);
                finish();
            }
        });

        enviarbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void obClick(View v){
                String edtxtEmail,edtxtPassword;
                edtxtEmail=String.valueOf(edtxtEmail.getText());
                edtxtPassword=String.valueOf(edtxtPassword.getText());
                if(TextUtils.isEmpty(edtxtEmail)){
                    Toast.makeText(LoginActivity.this, "Ingrese correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtxtPassword));
                    Toast.makeText(LoginActivity.this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(edtxtEmail,edtxtPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();;
                            Intent i=new Intent(LoginActivity.this, RegistroActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Auntenticacion erronea", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

        }
    });