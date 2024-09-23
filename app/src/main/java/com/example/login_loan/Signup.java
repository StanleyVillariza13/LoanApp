package com.example.login_loan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.example.Login_Loan.R;
import java.util.HashMap;

public class Signup extends AppCompatActivity {

    private EditText etUsername, etPassword, etMobileNumber, etAddress;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup); // Correct layout reference

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etAddress = findViewById(R.id.etAddress);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String mobileNumber = etMobileNumber.getText().toString();
            String address = etAddress.getText().toString();

            if (username.isEmpty() || password.isEmpty() || mobileNumber.isEmpty() || address.isEmpty()) {
                Toast.makeText(Signup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            HashMap<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);
            params.put("mobile_number", mobileNumber);
            params.put("address", address);

            String url = "http://192.168.1.10/Login/Signup.php";
            VolleyRequest.post(Signup.this, url, params, new VolleyRequest.VolleyCallback() {
                @Override
                public void onSuccess(String response) {
                    Toast.makeText(Signup.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Login.class);
                    startActivity(intent);
                    finish(); // Close the Signup activity
                }

                @Override
                public void onError(String error) {
                    Log.e("SignupError", "Error: " + error);
                    Toast.makeText(Signup.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
