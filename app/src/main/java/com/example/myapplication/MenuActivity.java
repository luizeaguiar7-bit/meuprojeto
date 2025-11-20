package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button currencyConverterButton;
    private Button temperatureConverterButton;
    private Button motivationalPhrasesButton;
    private Button cepConsultationButton;
    private Button shoppingListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        currencyConverterButton = findViewById(R.id.currencyConverterButton);
        temperatureConverterButton = findViewById(R.id.temperatureConverterButton);
        motivationalPhrasesButton = findViewById(R.id.motivationalPhrasesButton);
        cepConsultationButton = findViewById(R.id.cepConsultationButton);
        shoppingListButton = findViewById(R.id.shoppingListButton);

        currencyConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CurrencyConverterActivity.class);
                startActivity(intent);
            }
        });

        temperatureConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, TemperatureConverterActivity.class);
                startActivity(intent);
            }
        });

        motivationalPhrasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cepConsultationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CepConsultationActivity.class);
                startActivity(intent);
            }
        });

        shoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ShoppingListActivity.class);
                startActivity(intent);
            }
        });
    }
}
