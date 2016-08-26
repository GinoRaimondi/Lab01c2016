package ar.edu.utn.frsf.isi.dam;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.botonPlazoFijo);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView texto = (TextView)findViewById(R.id.textView8);
                    texto.setTextColor(Color.RED);
            }
        });
    }


}
