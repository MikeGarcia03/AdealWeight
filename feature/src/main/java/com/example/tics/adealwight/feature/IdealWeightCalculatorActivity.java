package com.example.tics.adealwight.feature;

import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class IdealWeightCalculatorActivity extends AppCompatActivity {

    private int mPosition;
    private double result, iw_Result, c_Height, c_Weight, c_Result;
    private final double [] RANGE = {18.5, 24.9, 25, 26.9, 27, 29.9, 30, 34.9, 35, 39.9, 40, 49.9};
    private String message;
    private String [] gl_Message = {"Good!!! You have to keep healthy", "You have to lose ", "You have to win "};
    private String [] st_Message = {" Kg","Insufficient Weight", "Normal Weight", "Could have Overweight Level 1",
            "Overweight Level 1 (Pre-Obesity)", "Obesity Level I (Slight)", "Obesity Level II (Moderate)",
            "Obesity Level III (Morbid)", "Extreme Obesity"};
    private DecimalFormat roundDecimals;
    private FloatingActionButton btn_Calculate, btn_Refresh;
    private TextView txtV_IndexBodyMass, txtV_State, txtV_Goal;
    private EditText eTxt_Weight, eTxt_Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight_calculator);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setTitle("Ideal Weight: IMC Method");

        roundDecimals = new DecimalFormat("#,####.##");
        roundDecimals.setRoundingMode(RoundingMode.CEILING);

        btn_Calculate = findViewById(R.id.fbtnCalculate);
        btn_Refresh = findViewById(R.id.fbtnRefresh);
        txtV_IndexBodyMass = findViewById(R.id.bodyMassIndex2);
        txtV_State = findViewById(R.id.actualState2);
        txtV_Goal = findViewById(R.id.theGoal2);
        eTxt_Weight = findViewById(R.id.weightInput);
        eTxt_Height = findViewById(R.id.heightInput);

        btn_Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    txtV_State.setText(setStateMessage());
                    txtV_IndexBodyMass.setText(weightCalculator() + st_Message[0]);
                    txtV_Goal.setText(setGoalMessage());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Please, insert your Height and Weight",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eTxt_Weight.setText("");
                eTxt_Height.setText("");
                txtV_State.setText("Actual State");
                txtV_IndexBodyMass.setText("Body Mass Index");
                txtV_Goal.setText("The Goal");
            }
        });
    }

    public String weightCalculator(){

        c_Weight = Double.parseDouble(eTxt_Weight.getText().toString());
        c_Height = Double.parseDouble(eTxt_Height.getText().toString());

        c_Result = c_Weight / (c_Height * c_Height);

        return roundDecimals.format(c_Result);
    }

    public String setWeightToLoseOrWin(){

        iw_Result = Double.parseDouble(weightCalculator());

        if (!(iw_Result >= RANGE[0] && iw_Result <= RANGE[1])){

            if (iw_Result > RANGE[1]) {

                result = iw_Result - RANGE[0];
            }
            if (iw_Result < RANGE[0]){

                result = RANGE[0] - iw_Result;
            }
        }
        return roundDecimals.format(result);
    }

    public String setStateMessage(){
        String test = weightCalculator();
        iw_Result = Double.parseDouble(test);

        if (iw_Result < RANGE[0]){

            message = st_Message[1];
            mPosition = 2;

        }else if (iw_Result >= RANGE[0] && iw_Result <= RANGE[1]){

            message = st_Message[2];
            mPosition = 0;

        }else if (iw_Result >= RANGE[2] && iw_Result <= RANGE[3]){

            message = st_Message[3];
            mPosition = 1;

        }else if (iw_Result >= RANGE[4] && iw_Result <= RANGE[5]){

            message = st_Message[4];
            mPosition = 1;

        }else if (iw_Result >= RANGE[6] && iw_Result <= RANGE[7]){

            message = st_Message[5];
            mPosition = 1;

        }else if (iw_Result >= RANGE[8] && iw_Result <= RANGE[9]){

            message = st_Message[6];
            mPosition = 1;

        }else if (iw_Result >= RANGE[10] && iw_Result <= RANGE[11]){

            message = st_Message[7];
            mPosition = 1;

        }else{ message = st_Message[8]; mPosition = 1;}

        return message;
    }

    public String setGoalMessage(){

        if (mPosition == 0){
            message = gl_Message[mPosition];
        }else if (mPosition == 1){
            message = gl_Message[mPosition] + setWeightToLoseOrWin() + st_Message[0];
        }else {
            message = gl_Message[mPosition] + setWeightToLoseOrWin() + st_Message[0];
        }

        return message;
    }
}