package com.example.sowrabhsudhir.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked (View view){

        EditText numberInput = (EditText) findViewById(R.id.numberInput);

        Random random = new Random();

        int n = random.nextInt(20)+1;

        if (Integer.valueOf(numberInput.getText().toString()) > n)
        {
            Toast.makeText(MainActivity.this,"Lower!",Toast.LENGTH_SHORT).show();
        }else if (Integer.valueOf((numberInput.getText().toString())) < n)
            Toast.makeText(this, "Higher!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"That's right! Try Again!",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
