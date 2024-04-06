package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    Button btnreset, btnback;
    EditText editEmail;
    FirebaseAuth nAuth;
    String strEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnback = findViewById(R.id.backbtn);
        btnreset = findViewById(R.id.resetpass);
        editEmail = findViewById(R.id.forgotemail);

        nAuth = FirebaseAuth.getInstance();

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = editEmail.getText().toString().trim();
                if(!TextUtils.isEmpty(strEmail))
                {
                    ResetPassword();
                }
                else
                {
                    editEmail.setError("Email Field is Empty");
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ForgotPassword.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void ResetPassword()
    {
        btnreset.setVisibility(View.INVISIBLE);

        nAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ForgotPassword.this, "Reset Password link has been sent to your email", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPassword.this, "Password link failed to sent", Toast.LENGTH_SHORT).show();
                        btnreset.setVisibility(View.VISIBLE);
                    }
                });
    }
}
