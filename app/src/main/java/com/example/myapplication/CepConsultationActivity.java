package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CepConsultationActivity extends AppCompatActivity {

    private EditText cepEditText;
    private Button consultCepButton;
    private TextView cepResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep_consultation);

        cepEditText = findViewById(R.id.cepEditText);
        consultCepButton = findViewById(R.id.consultCepButton);
        cepResultTextView = findViewById(R.id.cepResultTextView);

        consultCepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultCep();
            }
        });
    }

    private void consultCep() {
        String cep = cepEditText.getText().toString();
        if (cep.isEmpty()) {
            Toast.makeText(this, "Por favor, digite um CEP.", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    String json = stringBuilder.toString();
                    JSONObject jsonObject = new JSONObject(json);

                    if (jsonObject.has("erro")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cepResultTextView.setText("CEP não encontrado.");
                            }
                        });
                    } else {
                        String logradouro = jsonObject.getString("logradouro");
                        String bairro = jsonObject.getString("bairro");
                        String localidade = jsonObject.getString("localidade");
                        String uf = jsonObject.getString("uf");

                        String result = "Logradouro: " + logradouro + "\n" +
                                "Bairro: " + bairro + "\n" +
                                "Cidade: " + localidade + "\n" +
                                "Estado: " + uf;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cepResultTextView.setText(result);
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cepResultTextView.setText("Erro ao consultar o CEP.");
                        }
                    });
                }
            }
        }).start();
    }
}
