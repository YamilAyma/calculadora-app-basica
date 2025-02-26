package com.tiempo.aplicacion_operaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    private ImageView addPortal;
    private ImageView minusPortal;
    private ImageView dividePortal;
    private ImageView mulPortal;
    private ImageView powPortal;
    private  ImageView sqrtPortal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        addPortal = (ImageView) findViewById(R.id.addPortal);
        minusPortal = (ImageView) findViewById(R.id.minusPortal);
        dividePortal = (ImageView) findViewById(R.id.dividePortal);
        mulPortal = (ImageView) findViewById(R.id.mulPortal);
        powPortal = (ImageView) findViewById(R.id.powPortal);
        sqrtPortal = (ImageView) findViewById(R.id.sqrtPortal);

        addClickEventRedirectToOperation(addPortal, "suma");
        addClickEventRedirectToOperation(minusPortal, "resta");
        addClickEventRedirectToOperation(dividePortal, "division");
        addClickEventRedirectToOperation(mulPortal, "multiplicacion");
        addClickEventRedirectToOperation(powPortal, "potenciacion");

        // Para vistas de operaciones que requieran un solo número
        sqrtPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redireccion = new Intent(Menu.this, Operacion1Numero.class);
                redireccion.putExtra("operacion", "raiz cuadrada");
                startActivity(redireccion);
            }
        });
    }

    private void addClickEventRedirectToOperation(ImageView portal, String operation){

        portal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "¡Listo!", Toast.LENGTH_SHORT).show();
                Intent redirectActivity = new Intent(Menu.this, MainActivity.class);
                redirectActivity.putExtra("operacion", operation);
                startActivity(redirectActivity);
            }
        });
    }
}