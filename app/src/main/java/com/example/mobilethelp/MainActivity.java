package com.example.mobilethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

// Por favor, renomeie este arquivo para LoginActivity.java na estrutura do seu projeto
public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Acessando os TextInputEditText diretamente
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificação para evitar NullPointerException, embora o problema principal seja o findViewById
                if (editTextUsername == null || editTextPassword == null) {
                    Toast.makeText(MainActivity.this, "Error initializing views.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.equals("admin") && password.equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica de registro mock
                Toast.makeText(MainActivity.this, "Registration not implemented", Toast.LENGTH_SHORT).show();
            }
        });
    }
}