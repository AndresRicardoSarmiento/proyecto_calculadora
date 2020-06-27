package com.example.proyecto_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_suma;
    private Button btn_resta;



    private Button btn_division;
    private Button btn_multiplicacion;

    private TextView text_respuesta;
    private EditText edit_num1;
    private EditText edit_num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_respuesta = findViewById(R.id.Respuesta);
        edit_num1 = findViewById(R.id.num1);
        edit_num2 = findViewById(R.id.num2);
        
        
        btn_suma = findViewById(R.id.button_suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_respuesta.setText(suma(Double.parseDouble(edit_num1.getText().toString()), Double.parseDouble(edit_num2.getText().toString()))+"");

            }
        });


        btn_resta = findViewById(R.id.button_resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_respuesta.setText(resta (Double.parseDouble(edit_num1.getText().toString()), Double.parseDouble(edit_num2.getText().toString()))+"");
            }
        });

    }
}