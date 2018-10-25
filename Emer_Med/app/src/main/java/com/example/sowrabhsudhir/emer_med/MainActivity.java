package com.example.sowrabhsudhir.emer_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sowrabhsudhir.emer_med.API.models.responses.LoginRes;
import com.example.sowrabhsudhir.emer_med.API.services.ApiClient;
import com.example.sowrabhsudhir.emer_med.API.services.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private Button login, signup;
    private EditText email, password;
    private ApiInterface api;
    private ProgressBar loading;
    private TinyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = ApiClient.getClient().create(ApiInterface.class);
        db = new TinyDB(getApplicationContext());
        if (db.getBoolean("login")) {
            Intent go = new Intent(MainActivity.this, infopage.class);
            startActivity(go);
            finishAffinity();
        }
        else {
            loading = findViewById(R.id.loading);
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            login = findViewById(R.id.login);
            signup = findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent go = new Intent(MainActivity.this, Register.class);
                    startActivity(go);
                    finishAffinity();
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    valid(email.getText().toString(), password.getText().toString());
                }
            });
        }
    }

    public void valid(String Email, String Password) {
        loading.setVisibility(View.VISIBLE);
        login.setEnabled(false);
        api.attemptLogin(Email,Password).enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                loading.setVisibility(View.GONE);
                login.setEnabled(true);
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {
                        db.putBoolean("login", true);
                        db.putString("name", response.body().getDetails().getName());
                        db.putString("email", response.body().getDetails().getEmail());
                        db.putString("emergency", response.body().getDetails().getEmergency());
                        db.putString("age", response.body().getDetails().getAge());
                        db.putString("bloodgroup", response.body().getDetails().getBloodgroup());
                        db.putString("bloodpressure", response.body().getDetails().getBloodpressure());
                        db.putString("diseases", response.body().getDetails().getDiseases());
                        Intent go = new Intent(MainActivity.this, infopage.class);
                        startActivity(go);
                        finishAffinity();
                    }
                }
                else if (response.code() == 404) {
                    Toast.makeText(MainActivity.this, "Invalid Email/Password", Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 401) {
                    Toast.makeText(MainActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                login.setEnabled(true);
                loading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
