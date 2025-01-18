package com.example.zakatcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class CalculatorActivity extends AppCompatActivity {

    EditText txtWeight, txtCurrentValue, txtZakatPayable, txtFinalTotal;
    RadioButton radioKeep, radioWear;
    Button btnCalculate, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        txtWeight = findViewById(R.id.txtAnswerWeight);
        txtCurrentValue = findViewById(R.id.txtAnswerCurrent);
        txtZakatPayable = findViewById(R.id.txtAnswerZakatPayable); // Matching the correct ID
        txtFinalTotal = findViewById(R.id.txtAnswerFinalTotal);
        radioKeep = findViewById(R.id.radioKeep);
        radioWear = findViewById(R.id.radioWear);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double weight = Double.parseDouble(txtWeight.getText().toString());
                    double currentValue = Double.parseDouble(txtCurrentValue.getText().toString());

                    double exemptionWeight = 0;
                    if (radioKeep.isChecked()) {
                        exemptionWeight = 85;
                    } else if (radioWear.isChecked()) {
                        exemptionWeight = 200;
                    } else {
                        Toast.makeText(CalculatorActivity.this, "Please select the type of gold.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double totalValue = weight * currentValue;
                    double zakatPayableValue = Math.max(0, (weight - exemptionWeight) * currentValue);
                    double totalZakat = 0.025 * zakatPayableValue; // 2.5% zakat

                    txtZakatPayable.setText(String.format("%.2f", zakatPayableValue));
                    txtFinalTotal.setText(String.format("%.2f", totalZakat));

                } catch (NumberFormatException e) {
                    Toast.makeText(CalculatorActivity.this, "Please enter valid input.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWeight.setText("");
                txtCurrentValue.setText("");
                txtZakatPayable.setText("");
                txtFinalTotal.setText("");
                radioKeep.setChecked(false);
                radioWear.setChecked(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
