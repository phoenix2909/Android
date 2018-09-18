package com.example.sowrabhsudhir.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked(View view){

        EditText currencyInput = (EditText) findViewById(R.id.currencyInput);
        Button convert = (Button) findViewById(R.id.convert);
        double dollar= (Double.valueOf(currencyInput.getText().toString()))/72;
        Toast.makeText(this, "$"+dollar, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
