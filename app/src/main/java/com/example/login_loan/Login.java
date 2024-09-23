package com.example.login_loan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.Login_Loan.R;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);

        btnLogin.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();


            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }


            HashMap<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);


            String url = "http://192.168.1.10/Login/Login.php";


            VolleyRequest.post(Login.this, url, params, new VolleyRequest.VolleyCallback() {
                @Override
                public void onSuccess(String response) {
                    if (response.contains("Login successful")) {
                        // Navigate to DashboardActivity
                        Intent intent = new Intent(Login.this, Dashboard.class);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(String error) {
                    Log.e("LoginError", "Error: " + error); // Log the error
                    Toast.makeText(Login.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        });

        tvSignUp.setOnClickListener(view -> {
            // Navigate to Sign Up Activity
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });
    }
}
