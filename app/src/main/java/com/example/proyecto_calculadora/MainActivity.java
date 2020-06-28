package com.example.proyecto_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_suma;
    private Button btn_resta;
    private Button btn_division;
    private Button btn_multiplicacion;
    private Button btn_total;
    private TextView text_respuesta;
    private EditText edit_num1;
    private Double operando1;
    private Double operando2;
    private double resultado = 0;
    private String lastOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_respuesta = findViewById(R.id.Respuesta);
        edit_num1 = findViewById(R.id.num1);

        btn_suma = findViewById(R.id.button_suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroIngresado = edit_num1.getText().toString();
                operando1 = resultado;
                operando2 = 0.0;

                if (numeroIngresado.length() > 0) {
                    operando2 = Double.parseDouble(numeroIngresado);
                }

                if (lastOperation.isEmpty()) {
                    lastOperation = "suma";
                }

                calcular(operando1, operando2, lastOperation);
                lastOperation = "suma";
                clearInput();
            }
        });

        btn_resta = findViewById(R.id.button_resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroIngresado = edit_num1.getText().toString();

                if (numeroIngresado.length() > 0 && operando1 == null) {
                    operando1 = Double.parseDouble(numeroIngresado);
                    operando2 = 0.0;
                } else if (numeroIngresado.length() > 0) {
                    operando1 = resultado;
                    operando2 = Double.parseDouble(numeroIngresado);
                } else {
                    operando1 = resultado;
                    operando2 = 0.0;
                }

                if (lastOperation.isEmpty()) {
                    lastOperation = "resta";
                }

                calcular(operando1, operando2, lastOperation);
                lastOperation = "resta";
                clearInput();
            }
        });

        btn_division = findViewById(R.id.button_division);
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_respuesta.setText(String.valueOf(resultado));
                lastOperation = "division";
                clearInput();
            }
        });

        btn_multiplicacion = findViewById(R.id.button_multiplicacion);
        btn_multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_respuesta.setText(String.valueOf(resultado));
                lastOperation = "multiplicacion";
                clearInput();
            }
        });

        btn_total = findViewById(R.id.button_total);
        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (lastOperation) {
                    case "suma" :
                        resultado += Double.parseDouble(edit_num1.getText().toString());

                        break;
                    case "resta" :
                        resultado -= Double.parseDouble(edit_num1.getText().toString());

                        break;
                    case "division" :
                        break;
                    case "multiplicacion" :
                        break;
                }

                text_respuesta.setText(String.valueOf(resultado));
                clearInput();
            }
        });
    }

    public void calcular(double operando1, double operando2, String operacion) {
        System.out.println("Se realizará la " + operacion + " de los siguientes números");
        System.out.println("operando 1: [" + operando1 + "]");
        System.out.println("operando 2: [" + operando2 + "]");

        switch (operacion) {
            case "suma" :
                resultado = operando1 + operando2;

                break;
            case "resta" :
                resultado = operando1 - operando2;

                break;
            case "multiplicacion" :
                resultado = operando1 * operando2;

                break;
            case "division" :
                if (operando2 != 0) {
                    resultado = operando1 / operando2;
                }

                break;
        }

        if (resultado % 1 == 0) {
            // Si el resultado se puede representar como entero los mostramos de esa forma.
            text_respuesta.setText(String.valueOf((int) resultado));
        } else {
            // Si no, mostramos el numero tal cual con sus decimales.
            text_respuesta.setText(String.valueOf(resultado));
        }
    }

    private void clearInput() {
        edit_num1.setText("");
    }
}