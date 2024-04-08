package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMICalculator extends AppCompatActivity {
    private EditText bmiWeight;
    private EditText bmiHeight;
    private Button bmiCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bmiWeight = findViewById(R.id.weight);
        bmiHeight = findViewById(R.id.height);

        bmiCalc = findViewById(R.id.bmi_calc);
        bmiCalc.setOnClickListener(onSave);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double bmiWeightNum = Double.parseDouble(bmiWeight.getText().toString());
            double bmiHeightNum = Double.parseDouble(bmiHeight.getText().toString());
            double BMI = bmiWeightNum / (bmiHeightNum * bmiHeightNum);
            DecimalFormat df = new DecimalFormat("#.00");
            String formattedBMI = df.format(BMI);
            TextView bmiResult = findViewById(R.id.bmi_result);
            if (BMI < 18.5) {
                bmiResult.setText("BMI - " + formattedBMI + " Underweight");
            } else
            if (BMI >= 18.5 && BMI < 24.9) {
                bmiResult.setText("BMI - " + formattedBMI + " Healthy");
            } else
            if (BMI >= 25.0 && BMI < 29.9) {
                bmiResult.setText("BMI - " + formattedBMI + " Overweight");
            } else
            if (BMI > 30.0) {
                bmiResult.setText("BMI - " + formattedBMI + " Obese");
            }
        }
    };
}