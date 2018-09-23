package com.example.sowrabhsudhir.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int n;

    public void buttonClicked (View view){

        EditText numberInput = (EditText) findViewById(R.id.numberInput);



        if (Integer.valueOf(numberInput.getText().toString()) > n)
        {
            Toast.makeText(MainActivity.this,"Lower!",Toast.LENGTH_SHORT).show();
        }else if (Integer.valueOf((numberInput.getText().toString())) < n)
            Toast.makeText(this, "Higher!", Toast.LENGTH_SHORT).show();
        else
        {Toast.makeText(MainActivity.this,"That's right! Try Again!",Toast.LENGTH_SHORT).show();
            Random random = new Random();

            n = random.nextInt(20)+1;}

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random random = new Random();

         n = random.nextInt(20)+1;
    }
}
