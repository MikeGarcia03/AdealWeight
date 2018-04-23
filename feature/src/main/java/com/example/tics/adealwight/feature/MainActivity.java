package com.example.tics.adealwight.feature;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pro = findViewById(R.id.my_progress_bar_det);

        getSupportActionBar().hide();
        ValueAnimator animator = ValueAnimator.ofInt(0, pro.getMax());
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pro.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent iwCalculator = new Intent(MainActivity.this, IdealWeightCalculatorActivity.class);
                startActivity(iwCalculator);
            }
        });
        animator.start();


    }
}
