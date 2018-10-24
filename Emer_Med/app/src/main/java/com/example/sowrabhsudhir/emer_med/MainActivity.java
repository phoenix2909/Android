package com.example.sowrabhsudhir.emer_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button login;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid(email.getText().toString(), password.getText().toString());
            }
        });
    }

    public void valid(String Email, String Password) {
        if (Email.equals("admin@email.com") && Password.equals("admin")) {
            Intent intent = new Intent(MainActivity.this, infopage.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter a valid email address", Toast.LENGTH_LONG).show();
        }
    }
}
