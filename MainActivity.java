package com.example.zakatcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalculate = findViewById(R.id.btnCalculator);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

        Button btnAboutMe = findViewById(R.id.btnAboutMe);
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AboutMeActivity
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareMessage = "Check out this awesome Zakat Gold Calculator app: [https://github.com/AthirahSyafiqa/zakat-calculator]";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
}
