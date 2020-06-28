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
    private Button btn_multiplicacion;
    private Button btn_division;
    private Button btn_total;
    private TextView _operacion;
    private TextView text_respuesta;
    private EditText edit_num1;
    private Double operando1 = null;
    private Double operando2 = null;
    private double resultado = 0;
    private String lastOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_respuesta = findViewById(R.id.Respuesta);
        edit_num1 = findViewById(R.id.num1);
        _operacion = findViewById(R.id._operacion);
        addListenerSuma();
        btn_resta = findViewById(R.id.button_resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroIngresado = edit_num1.getText().toString();

                if (numeroIngresado.length() > 0 && operando1 == null) {
                    // Apenas es la primera operación a realizar, le restamos 0, para que
                    // se mantenga el miso número que ingresó.
                    operando1 = Double.parseDouble(numeroIngresado);
                    operando2 = 0.0;
                } else if (numeroIngresado.length() > 0) {
                    // Se realiza la operación normal, el último resultado menos el ingresado.
                    operando1 = resultado;
                    operando2 = Double.parseDouble(numeroIngresado);
                } else { // El usuario solo presionó el operador.
                    lastOperation = "Resta";
                    operando1 = resultado;
                    operando2 = 0.0;
                }

                if (lastOperation.isEmpty()) {
                    lastOperation = "Resta";
                }

                calcular(operando1, operando2, lastOperation);
                lastOperation = "Resta";
                asignarOperacion(lastOperation);
                clearInput();
            }
        });

        btn_multiplicacion = findViewById(R.id.button_multiplicacion);
        btn_multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroIngresado = edit_num1.getText().toString();

                if (numeroIngresado.length() > 0 && operando1 == null) {
                    // Apenas es la primera operación a realizar, lo multiplicamos por 1,
                    // para que se mantenga el miso número que ingresó.
                    operando1 = 1.0;
                    operando2 = Double.parseDouble(numeroIngresado);
                } else if (numeroIngresado.length() > 0) {
                    // Se realiza la operación normal, el último resultado por el ingresado.
                    operando1 = resultado;
                    operando2 = Double.parseDouble(numeroIngresado);
                } else { // El usuario solo presionó el operador.
                    lastOperation = "Multiplicación";
                    operando1 = resultado;
                    operando2 = 1.0;
                }

                if (lastOperation.isEmpty()) {
                    lastOperation = "Multiplicación";
                }

                calcular(operando1, operando2, lastOperation);
                lastOperation = "Multiplicación";
                asignarOperacion(lastOperation);
                clearInput();
            }
        });

        btn_division = findViewById(R.id.button_division);
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_respuesta.setText(String.valueOf(resultado));
                lastOperation = "División";
                asignarOperacion(lastOperation);

                String numeroIngresado = edit_num1.getText().toString();

                if (numeroIngresado.length() > 0 && operando1 == null) {
                    // Apenas es la primera operación a realizar, lo multiplicamos por 1,
                    // para que se mantenga el miso número que ingresó.
                    operando1 = Double.parseDouble(numeroIngresado);
                    operando2 = 1.0;
                } else if (numeroIngresado.length() > 0) {
                    // Se realiza la operación normal, el último resultado por el ingresado.
                    operando1 = resultado;
                    operando2 = Double.parseDouble(numeroIngresado);
                } else { // El usuario solo presionó el operador.
                    lastOperation = "División";
                    operando1 = resultado;
                    operando2 = 1.0;
                }

                if (lastOperation.isEmpty()) {
                    lastOperation = "División";
                }

                calcular(operando1, operando2, lastOperation);
                lastOperation = "División";
                asignarOperacion(lastOperation);
                clearInput();
            }
        });

        btn_total = findViewById(R.id.button_total);
        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Se realizará la " + lastOperation + " de los siguientes números");
                System.out.println("operando 1: [" + operando1 + "]");
                System.out.println("operando 2: [" + operando2 + "]");
                clearInput();
            }
        });
    }

    public void addListenerSuma(){
        btn_suma = findViewById(R.id.button_suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operando1 = resultado;
                operando2 = 0.0;
                String numeroIngresado = edit_num1.getText().toString();
                if (numeroIngresado.length() > 0) {
                    operando2 = Double.parseDouble(numeroIngresado);
                } else {
                    lastOperation = "Suma";
                }

                if (lastOperation.isEmpty()) {
                    lastOperation = "Suma";
                }

                calcular(operando1, operando2, lastOperation);
                lastOperation = "Suma";
                asignarOperacion(lastOperation);
                clearInput();
            }
        });
    }

    public void calcular(double operando1, double operando2, String operacion) {
        System.out.println("Se realizará la " + operacion + " de los siguientes números");
        System.out.println("operando 1: [" + operando1 + "]");
        System.out.println("operando 2: [" + operando2 + "]");

        switch (operacion) {
            case "Suma" :
                resultado = operando1 + operando2;

                break;
            case "Resta" :
                resultado = operando1 - operando2;

                break;
            case "Multiplicación" :
                resultado = operando1 * operando2;

                break;
            case "División" :
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

    private void asignarOperacion(String operacion){
        _operacion.setText(operacion);
    }
}