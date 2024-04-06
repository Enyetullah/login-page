package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Class declaration for the registration page
public class registerpage extends AppCompatActivity {

    // Declare UI components and Firebase authentication variables
    EditText editTextEmail, editTextPassword;
    Button btn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage); // Set the layout for the registration page
        mAuth = FirebaseAuth.getInstance(); // Get Firebase authentication instance
        editTextEmail = findViewById(R.id.username); // Initialize email EditText field
        editTextPassword = findViewById(R.id.pass); // Initialize password EditText field
        btn = findViewById(R.id.registerbtn); // Initialize registration button
        progressBar = findViewById(R.id.progressbar); // Initialize progress bar
        btnback = findViewById(R.id.backbtn);

        // Set click listener for the registration button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE); // Show progress bar while registering
                String email, password;
                email = String.valueOf(editTextEmail.getText()); // Get entered email
                password = String.valueOf(editTextPassword.getText()); // Get entered password

                // Validate email and password fields
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(registerpage.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(registerpage.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create user account with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE); // Hide progress bar
                                if (task.isSuccessful()) {
                                    // Account creation successful, show success message
                                    Toast.makeText(registerpage.this, "Account Created.", Toast.LENGTH_SHORT).show();
                                    // Redirect to the main activity after successful registration
                                    Intent intent = new Intent(registerpage.this, MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    // Account creation failed, display error message
                                    Toast.makeText(registerpage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(registerpage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //1
    }
}
