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

public class MainActivity extends AppCompatActivity {

    // Attributes for controls
    private EditText edt_num1;
    private EditText edt_num2;

    private TextView txt_result;
    private Button btn_sumar;
    private Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Get operation
        String operationKey = getIntent().getStringExtra("operacion");
        MathOperation operation = (n1, n2) -> n1 + n2;

        switch (operationKey){
            case "suma":
                operation = (n1, n2) -> n1 + n2;
                break;
            case "resta":
                operation = (n1, n2) -> n1 - n2;
                break;
            case "multiplicacion":
                operation = (n1, n2) -> n1 * n2;
                break;
            case "division":
                operation = (n1, n2) -> n1 / n2;
                break;
            case "potenciacion":
                operation = (n1, n2) -> Math.pow(n1, n2);
                break;
            default:
                // Redirigir a la página principal
                Intent redireccion = new Intent(MainActivity.this, Menu.class);
                startActivity(redireccion);
                break;
        }

        // Catch controls
        edt_num1 = (EditText) findViewById(R.id.num1);
        edt_num2 = (EditText) findViewById(R.id.num2);
        txt_result = (TextView) findViewById(R.id.result);
        btn_sumar = (Button) findViewById(R.id.sumar);
        btn_volver = (Button) findViewById(R.id.volver);

        MathOperation finalOperation = operation;
        btn_sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int num1 = Integer.parseInt(edt_num1.getText().toString());
                    int num2 = Integer.parseInt(edt_num2.getText().toString());
                    String resultado = String.valueOf(finalOperation.calculate(num1, num2));
                    txt_result.setText(resultado);

                    edt_num1.setText("");
                    edt_num2.setText("");


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Recuerda escribir los números", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir al menu
                Intent redirectActivity = new Intent(MainActivity.this, Menu.class);
                startActivity(redirectActivity);
            }
        });




    }

}