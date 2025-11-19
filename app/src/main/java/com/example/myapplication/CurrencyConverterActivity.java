package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CurrencyConverterActivity extends AppCompatActivity {

    private EditText valueInRealEditText;
    private Spinner currencySpinner;
    private Button convertCurrencyButton;
    private TextView convertedValueTextView;

    private final double DOLLAR_RATE = 5.30;
    private final double EURO_RATE = 6.20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        valueInRealEditText = findViewById(R.id.valueInRealEditText);
        currencySpinner = findViewById(R.id.currencySpinner);
        convertCurrencyButton = findViewById(R.id.convertCurrencyButton);
        convertedValueTextView = findViewById(R.id.convertedValueTextView);

        String[] currencies = {"Dólar", "Euro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);

        convertCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String valueInRealString = valueInRealEditText.getText().toString();
        if (valueInRealString.isEmpty()) {
            convertedValueTextView.setText("Por favor, insira um valor.");
            return;
        }

        double valueInReal = Double.parseDouble(valueInRealString);
        String selectedCurrency = currencySpinner.getSelectedItem().toString();
        double convertedValue;

        if (selectedCurrency.equals("Dólar")) {
            convertedValue = valueInReal / DOLLAR_RATE;
        } else {
            convertedValue = valueInReal / EURO_RATE;
        }

        convertedValueTextView.setText(String.format("%.2f", convertedValue));
    }
}
