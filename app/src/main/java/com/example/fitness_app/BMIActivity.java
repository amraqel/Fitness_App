package com.example.fitness_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    EditText nameTxt;
    EditText heightTxt;
    EditText weightTxt;
    Spinner spinner;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameTxt = findViewById(R.id.nameInput);
        heightTxt = findViewById(R.id.heightInput);
        weightTxt = findViewById(R.id.weightInput);
        spinner = findViewById(R.id.spinner);
        result = findViewById(R.id.resultField);

        setSpinnerValues();


    }

    @Override
    protected void onResume(){

        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("Data",MODE_PRIVATE);

        nameTxt.setText(sharedPreferences.getString("name",""));
        heightTxt.setText(sharedPreferences.getString("height",""));
        weightTxt.setText(sharedPreferences.getString("weight",""));
        spinner.setSelection(sharedPreferences.getInt("gender",0));
        result.setText(sharedPreferences.getString("result",""));

    }

    public void saveOnClick(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString("name", nameTxt.getText().toString());
        edit.putString("height", heightTxt.getText().toString());
        edit.putString("weight", weightTxt.getText().toString());
        edit.putInt("gender", spinner.getSelectedItemPosition());
        edit.putString("result", result.getText().toString());

        edit.commit();

    }

    public void calculateOnClick(View v){

        double  height = Double.valueOf(heightTxt.getText().toString());
        double weight = Double.valueOf(weightTxt.getText().toString());

        double val = weight / (height * height);
        val = Math.round(val * 10) / 10.0;

        result.setText("Your BMI is : "+ String.valueOf(val));

    }

    private void setSpinnerValues() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

}