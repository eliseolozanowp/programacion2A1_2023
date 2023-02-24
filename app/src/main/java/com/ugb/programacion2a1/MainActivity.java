package com.ugb.programacion2a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    RadioGroup radioGroup;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnCalcular);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView = (TextView) findViewById(R.id.txtNum1);
                double num1 = Double.parseDouble(textView.getText().toString());
                double num2 = 0;
                textView = (TextView) findViewById(R.id.txtNum2);
                try {
                    num2 = Double.parseDouble(textView.getText().toString());
                } catch (Exception e){
                    // Manejador de Error
                }
                double resp = 0;
                String msg = "Operación Invalida";

                spinner = (Spinner) findViewById(R.id.spnOpciones);
                switch (spinner.getSelectedItemPosition()){
                    case 0: // Suma
                        resp = num1 + num2;
                        msg = "La suma es: "+ resp;
                        break;
                    case 1: // Resta
                        resp = num1 - num2;
                        msg = "La resta es: "+ resp;
                        break;
                    case 2: // Multiplicación
                        resp = num1 * num2;
                        msg = "La multiplicación es: "+ resp;
                        break;
                    case 3: // División
                        resp = num1 / num2;
                        msg = "La división es: "+ resp;
                        break;
                    case 4: // Exponente
                        resp = Math.pow(num1, num2);
                        msg = "La exponenciación es: "+ resp;
                        break;
                    case 5: // Factorial
                        resp = 1;
                        for (int i=2; i<=num1; i++){
                            resp *= i; // resp = resp * i;
                        }
                        msg = "Factorial!: "+ resp;
                        break;
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}