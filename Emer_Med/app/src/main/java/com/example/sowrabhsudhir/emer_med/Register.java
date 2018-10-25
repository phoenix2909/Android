package com.example.sowrabhsudhir.emer_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sowrabhsudhir.emer_med.API.models.responses.RegisterRes;
import com.example.sowrabhsudhir.emer_med.API.services.ApiClient;
import com.example.sowrabhsudhir.emer_med.API.services.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private Button register, signin;
    private EditText name, email, emergency, age, bloodgroup, bloodpressure, disease, password;
    private ApiInterface api;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        api = ApiClient.getClient().create(ApiInterface.class);
        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        emergency = findViewById(R.id.emergency);
        age = findViewById(R.id.age);
        bloodgroup = findViewById(R.id.bloodgroup);
        bloodpressure = findViewById(R.id.bloodpressure);
        disease = findViewById(R.id.diseases);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        signin = findViewById(R.id.tologin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(name.getText().toString(), email.getText().toString(), emergency.getText().toString(), age.getText().toString(), bloodgroup.getText().toString(), bloodpressure.getText().toString(), disease.getText().toString(), password.getText().toString());
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Register.this, MainActivity.class);
                startActivity(go);
                finishAffinity();
            }
        });
    }

    public void register(String name, String email, String emergency, String age, String bloodgroup, String bloodpressure, String disease, String password) {
        loading.setVisibility(View.VISIBLE);
        register.setEnabled(false);
        api.registerUser(name,email,emergency,age,bloodgroup,bloodpressure,disease,password).enqueue(new Callback<RegisterRes>() {
            @Override
            public void onResponse(Call<RegisterRes> call, Response<RegisterRes> response) {
                loading.setVisibility(View.GONE);
                register.setEnabled(true);
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        Intent go = new Intent(Register.this, MainActivity.class);
                        startActivity(go);
                        finishAffinity();
                    }
                    else {
                        Toast.makeText(Register.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (response.code() == 409) {
                    Toast.makeText(Register.this, "User already exists!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterRes> call, Throwable t) {
                register.setEnabled(true);
                loading.setVisibility(View.GONE);
                Toast.makeText(Register.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
