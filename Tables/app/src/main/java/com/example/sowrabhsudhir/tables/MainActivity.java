package com.example.sowrabhsudhir.tables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView numberList;

    public void generateTable(int number){

        ArrayList <String> num = new ArrayList<String>();

        for(int i =1; i <= 10 ; i++)
        {
            num.add(Integer.toString(i * number));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, num);

        numberList.setAdapter(arrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar tableSeekBar = (SeekBar) findViewById(R.id.tableSeekBar);
        numberList = findViewById(R.id.numberList);

        tableSeekBar.setMax(20);
        tableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int min = 1, number;

                if(i < min){
                    number = min;
                    tableSeekBar.setProgress(min);
                }
                else {
                    number = i;
                }
                generateTable(number);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTable(10);

    }
}
