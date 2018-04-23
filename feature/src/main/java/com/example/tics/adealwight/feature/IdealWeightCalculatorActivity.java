package com.example.tics.adealwight.feature;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class IdealWeightCalculatorActivity extends AppCompatActivity {

    private final double FTTOMT = 3.28;
    private final double LBTOKG = 0.45;
    private final double KGTOLB = 2.21;
    private double c_Height, c_Weight, kg_Result, mt_Result, iwKg_Result, iwLb_Result;
    private FloatingActionButton iw_Calculate, iw_Refresh;
    private TextView bmi_Result;
    private EditText weight, height;
    private Switch sch_Measure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight_calculator);

        iw_Calculate = findViewById(R.id.fbtnCalculate);
        iw_Refresh = findViewById(R.id.fbtnRefresh);
        bmi_Result = findViewById(R.id.bodyMassIndex2);
        weight = findViewById(R.id.weightInput);
        height = findViewById(R.id.heightInput);
        sch_Measure = findViewById(R.id.schMeasure);

        iw_Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kg_Result = Calculator();
                if(sch_Measure.isChecked()){
                    bmi_Result.setText(String.valueOf(kg_Result)+" "+"Lb");
                }else{
                    bmi_Result.setText(String.valueOf(kg_Result)+" "+"Kg");
                }
            }
        });
    }

    public Double Calculator(){

        c_Height = Double.parseDouble(height.getText().toString());
        c_Weight = Double.parseDouble(weight.getText().toString());

        if (sch_Measure.isChecked()){

            mt_Result = (c_Height / FTTOMT);
            kg_Result = (c_Weight / LBTOKG);

            iwKg_Result = kg_Result/(mt_Result * mt_Result);
            iwLb_Result = iwKg_Result * KGTOLB;

            return ((double)Math.round(iwLb_Result));

        }else{

            iwKg_Result = c_Weight/(c_Height * c_Height);

            return ((double)Math.round(iwKg_Result));
        }

    }
}
