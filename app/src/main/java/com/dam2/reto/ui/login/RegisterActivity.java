package com.dam2.reto.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dam2.reto.R;
import com.dam2.reto.ui.retrofit.API;
import com.dam2.reto.ui.retrofit.RetrofitInstance;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText emailEditText;
    private EditText nombreEditText;
    private EditText apellido1EditText;
    private EditText apellido2EditText;
    private EditText direccionEditText;
    private Button registerButton;
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        apellido1EditText = findViewById(R.id.apellido1EditText);
        apellido2EditText = findViewById(R.id.apellido2EditText);
        direccionEditText = findViewById(R.id.direccionEditText);
        registerButton = findViewById(R.id.registerButton);

        api = RetrofitInstance.getAPI(this);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String nombre = nombreEditText.getText().toString();
            String apellido1 = apellido1EditText.getText().toString();
            String apellido2 = apellido2EditText.getText().toString();
            String direccion = direccionEditText.getText().toString();

            if (password.equals(confirmPassword)) {
                registerUser(username, password, email, nombre, apellido1, apellido2, direccion);
            } else {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(String username, String password, String email, String nombre, String apellido1, String apellido2, String direccion) {
        Map<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("email", email);
        user.put("nombre", nombre);
        user.put("apellido1", apellido1);
        user.put("apellido2", apellido2);
        user.put("direccion", direccion);

        api.registerUser(user).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}