package com.example.drawshapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button undo = findViewById(R.id.buttonUndo);
        RadioGroup rg = findViewById(R.id.radioGroupShapes);
        Spinner spin = findViewById(R.id.spinner);
        undo.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
        spin.setOnItemSelectedListener(this);

    }

   // AdapterView.OnItemSelectedListener; взять position - индекс в массиве:

    @Override
    public void onClick(View v) {
        MyView my = findViewById(R.id.myView1);
        my.undo();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        MyView my = findViewById(R.id.myView1);
        my.shapeChange(checkedId);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] colors = getResources().getStringArray(R.array.colors);
        String nameColor = colors[position];
        MyView my = findViewById(R.id.myView1);
        my.colorChange(nameColor);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}