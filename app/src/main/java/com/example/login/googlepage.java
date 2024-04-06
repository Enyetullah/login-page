package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import com.google.firebase.auth.FirebaseAuth;

public class googlepage extends  MainActivity {
    private FirebaseAuth auth1;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlepage);
        auth1 = FirebaseAuth.getInstance();
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick(View v)
          {
              logout();
              Intent intent = new Intent(googlepage.this, MainActivity.class);
              startActivity(intent);
              finish();
          }
        });

    }

}