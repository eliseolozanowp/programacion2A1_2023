package com.ugb.programacion2a1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    Button button;
    TextView textView;
    Spinner spnDe, spnA;
    Spinner spinner;
    conversores miConversor = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tbhParcial);
        tabHost.setup();

        // Primera pestaña
        TabHost.TabSpec spec = tabHost.newTabSpec("Tab1");
        spec.setIndicator("Tab 1");
        spec.setContent(R.id.tbhArea);
        tabHost.addTab(spec);

        button = findViewById(R.id.btnConvertir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView = (TextView) findViewById(R.id.txtcantidad);
                double cantidad = Double.parseDouble(textView.getText().toString());

                spinner = findViewById(R.id.spnDeArea);
                int de = spinner.getSelectedItemPosition();

                spinner = findViewById(R.id.spnAArea);
                int a = spinner.getSelectedItemPosition();

                textView = findViewById(R.id.lblrespuesta);
                textView.setText("Respuesta: "+ miConversor.convertir(0, de, a, cantidad));
            }
        });

        // Segunda pestaña
        spec = tabHost.newTabSpec("Tab2");
        spec.setIndicator("Tab 2");
        spec.setContent(R.id.tbhCajero);
        tabHost.addTab(spec);

        EditText editTextCantidad = findViewById(R.id.edit_text_cantidad);
        Button buttonCalcular = findViewById(R.id.button_calcular);
        TextView textViewBilletes50 = findViewById(R.id.text_view_billetes_50);
        TextView textViewBilletes20 = findViewById(R.id.text_view_billetes_20);
        TextView textViewBilletes10 = findViewById(R.id.text_view_billetes_10);
        TextView textViewBilletes5 = findViewById(R.id.text_view_billetes_5);
        TextView textViewBilletes1 = findViewById(R.id.text_view_billetes_1);
        TextView textViewMonedas5 = findViewById(R.id.text_view_monedas_5);

        // Manejo de eventos del botón de cálculo
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la cantidad a retirar
                int cantidad = Integer.parseInt(editTextCantidad.getText().toString());

                // Calcular la cantidad de billetes y monedas de cada denominación
                int billetes50 = cantidad / 50;
                cantidad %= 50;
                int billetes20 = cantidad / 20;
                cantidad %= 20;
                int billetes10 = cantidad / 10;
                cantidad %= 10;
                int billetes5 = cantidad / 5;
                cantidad %= 10;
                int billetes1 = cantidad / 5;
                cantidad %= 5;
                int monedas5 = cantidad;

                // Mostrar los resultados
                textViewBilletes50.setText("Billetes de 50: " + billetes50);
                textViewBilletes20.setText("Billetes de 20: " + billetes20);
                textViewBilletes10.setText("Billetes de 10: " + billetes10);
                textViewBilletes5.setText("Monedas de 5: " + billetes5);
                textViewBilletes1.setText("Monedas de 1: " + billetes1);
                textViewMonedas5.setText("Monedas de 5: " + monedas5);
            }
        });

    }
}

class conversores{
    double[][] valores = {
            {1, 0.6988, 0.001, 0.0001, 0.0000698, 0.0001726},// Areas
    };

    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}