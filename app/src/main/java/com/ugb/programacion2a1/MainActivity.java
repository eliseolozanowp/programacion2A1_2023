package com.ugb.programacion2a1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    Button button;
    TextView textView;
    Spinner spnDe, spnA;
    conversores miConversor = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tbhConversores);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("Longitud").setContent(R.id.tbhLongitud).setIndicator("", getDrawable(R.drawable.longitud)));
        tabHost.addTab(tabHost.newTabSpec("Almacenamiento").setContent(R.id.tbhAlmcenamiento).setIndicator("", getDrawable(R.drawable.almacenamiento)));
        tabHost.addTab(tabHost.newTabSpec("Monedas").setContent(R.id.tbhMonedas).setIndicator("", getDrawable(R.drawable.payment_icon)));
        tabHost.addTab(tabHost.newTabSpec("Masa").setContent(R.id.tbhMasa).setIndicator("", getDrawable(R.drawable.volume_icon)));

        button = findViewById(R.id.btnConvertir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int de = 0, a = 0;
                    textView = (TextView) findViewById(R.id.txtcantidad);
                    double cantidad = Double.parseDouble(textView.getText().toString());
                    int opcion = 0;
                    switch (tabHost.getCurrentTab()) {
                        case 0:
                            spnDe = findViewById(R.id.spnDeLongitud);
                            spnA = findViewById(R.id.spnALongitud);
                            opcion = 1;
                            break;
                        case 1:
                            spnDe = findViewById(R.id.spnDeAlmacenamiento);
                            spnA = findViewById(R.id.spnAlmacenamiento);
                            opcion = 3;
                            break;
                        case 2:
                            spnDe = findViewById(R.id.spnDeMonedas);
                            spnA = findViewById(R.id.spnAMonedas);
                            opcion = 0;
                            break;
                        case 3:
                            spnDe = findViewById(R.id.spnDeMasa);
                            spnA = findViewById(R.id.spnAMasa);
                            opcion = 2;
                            break;
                    }
                    de = spnDe.getSelectedItemPosition();
                    a = spnA.getSelectedItemPosition();
                    textView = findViewById(R.id.lblrespuesta);
                    textView.setText("Respuesta: " + miConversor.convertir(opcion, de, a, cantidad));
                }catch (NumberFormatException e){
                    textView = findViewById(R.id.lblrespuesta);
                    textView.setText("Por favor ingrese una cantidad a convertir");
                }catch (Exception e){
                    textView = findViewById(R.id.lblrespuesta);
                    textView.setText("ERROR: "+ e.getMessage().toString());
                }
            }
        });
    }
}

class conversores{
    double[][] valores = {
            {1, 7.84, 24.63, 36.51, 581.78, 8.75, 0.93, 130.54, 82.52, 0.82},//monedas
            {1, 100, 3.28084, 39.37008, 1.1963081929167, 1.093613, 0.001, 0.000621},//Longitud
            {1, 453592.000004704, 453.592, 16, 0.453592, 0.000453592},//Masa
            {1, 8388608, 1048576,1024, 0.0009765625, 0.00000095367431640625},//Almacenamiento
    };

    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}