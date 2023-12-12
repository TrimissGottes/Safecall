package com.example.safecallv8;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {
    EditText rutedit, nombreedit, apellidoedit, emailedit, passwordedit, repasswordedit;
    Button btnEnviar, btnVolver;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        rutedit = findViewById(R.id.rutedit1);
        nombreedit = findViewById(R.id.nombreedit1);
        apellidoedit = findViewById(R.id.apellidoedit1);
        emailedit = findViewById(R.id.emailedit1);
        passwordedit = findViewById(R.id.passwordedit1);
        repasswordedit = findViewById(R.id.repasswordedit);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rut = rutedit.getText().toString();
                String nombre = nombreedit.getText().toString();
                String apellido = apellidoedit.getText().toString();
                String email = emailedit.getText().toString();
                String password = passwordedit.getText().toString();
                String repassword = repasswordedit.getText().toString();

                if (TextUtils.isEmpty(rut) || !isValidRut(rut)) {
                    Toast.makeText(RegistroActivity.this, "Ingrese un RUT válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nombre) || nombre.length() < 3) {
                    Toast.makeText(RegistroActivity.this, "Ingrese un nombre válido (mínimo 3 letras)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(apellido) || apellido.length() < 3) {
                    Toast.makeText(RegistroActivity.this, "Ingrese un apellido válido (mínimo 3 letras)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                    Toast.makeText(RegistroActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(repassword)) {
                    Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    // Redirigir a la actividad de menú
                                    Intent i = new Intent(RegistroActivity.this, MenuActivity.class);
                                    startActivity(i);
                                    finish(); // Cierra la actividad actual
                                } else {
                                    Toast.makeText(RegistroActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private boolean isValidRut(String rut) {
        // Eliminar puntos y guiones para simplificar la validación
        rut = rut.replaceAll("[.-]", "");

        // Verificar el formato del RUT
        if (!rut.matches("\\d{7,8}[0-9kK]")) {
            return false;
        }

        // Extraer el dígito verificador
        char dv = rut.charAt(rut.length() - 1);

        // Extraer la parte numérica del RUT
        int rutNumerico = Integer.parseInt(rut.substring(0, rut.length() - 1));

        // Calcular el dígito verificador esperado
        char dvEsperado = calcularDigitoVerificador(rutNumerico);

        // Comparar el dígito verificador esperado con el proporcionado
        return Character.toUpperCase(dv) == dvEsperado;
    }

    private char calcularDigitoVerificador(int rutNumerico) {
        int m = 0, s = 1;
        for (; rutNumerico != 0; rutNumerico /= 10) {
            s = (s + rutNumerico % 10 * (9 - m++ % 6)) % 11;
        }
        return (char) (s > 0 ? s + '0' : 'k');
    }

}
