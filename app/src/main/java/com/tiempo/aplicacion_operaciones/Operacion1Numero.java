package com.tiempo.aplicacion_operaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Operacion1Numero extends AppCompatActivity {

    private EditText num1;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_operacion1_numero);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get operation
        String operationKey = getIntent().getStringExtra("operacion");
        MathSingularOperation operation = (n1) -> n1;

        assert operationKey != null;
        if (operationKey.equals("raiz cuadrada")) {
            operation = (n1) -> Math.sqrt(n1);
        } else {
            Intent intent = new Intent(Operacion1Numero.this, Menu.class);
            startActivity(intent);
        }

        this.num1 = findViewById(R.id.num1);
        result = findViewById(R.id.result);
        Button btn_operar = findViewById(R.id.calcular);
        Button btn_volver = findViewById(R.id.volver);

        MathSingularOperation finalOperation = operation;
        btn_operar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int value = Integer.parseInt(num1.getText().toString());
                    String resultado = String.valueOf(finalOperation.calculate(value));
                    result.setText(resultado);



                }catch (Exception e){
                    Toast.makeText(Operacion1Numero.this, "Recuerda escribir los números", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir al menu
                Intent redirectActivity = new Intent(Operacion1Numero.this, Menu.class);
                startActivity(redirectActivity);
            }
        });


    }
}