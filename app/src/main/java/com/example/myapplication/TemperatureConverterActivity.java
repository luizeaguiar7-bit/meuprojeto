package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureConverterActivity extends AppCompatActivity {

    private EditText temperatureEditText;
    private Spinner fromTemperatureSpinner;
    private Spinner toTemperatureSpinner;
    private Button convertTemperatureButton;
    private TextView convertedTemperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        temperatureEditText = findViewById(R.id.temperatureEditText);
        fromTemperatureSpinner = findViewById(R.id.fromTemperatureSpinner);
        toTemperatureSpinner = findViewById(R.id.toTemperatureSpinner);
        convertTemperatureButton = findViewById(R.id.convertTemperatureButton);
        convertedTemperatureTextView = findViewById(R.id.convertedTemperatureTextView);

        String[] temperatureUnits = {"Celsius", "Fahrenheit", "Kelvin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperatureUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromTemperatureSpinner.setAdapter(adapter);
        toTemperatureSpinner.setAdapter(adapter);

        convertTemperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String temperatureString = temperatureEditText.getText().toString();
        if (temperatureString.isEmpty()) {
            convertedTemperatureTextView.setText("Por favor, insira um valor.");
            return;
        }

        double temperature = Double.parseDouble(temperatureString);
        String fromUnit = fromTemperatureSpinner.getSelectedItem().toString();
        String toUnit = toTemperatureSpinner.getSelectedItem().toString();

        double convertedTemperature = convert(temperature, fromUnit, toUnit);
        convertedTemperatureTextView.setText(String.format("%.2f", convertedTemperature));
    }

    private double convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        double valueInCelsius = 0;

        // Convert to Celsius first
        switch (fromUnit) {
            case "Fahrenheit":
                valueInCelsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                valueInCelsius = value - 273.15;
                break;
            default: // Celsius
                valueInCelsius = value;
                break;
        }

        // Convert from Celsius to the target unit
        switch (toUnit) {
            case "Fahrenheit":
                return (valueInCelsius * 9 / 5) + 32;
            case "Kelvin":
                return valueInCelsius + 273.15;
            default: // Celsius
                return valueInCelsius;
        }
    }
}
