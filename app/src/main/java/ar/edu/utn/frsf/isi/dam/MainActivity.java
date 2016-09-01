package ar.edu.utn.frsf.isi.dam;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView texto;
    private EditText monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.botonPlazoFijo);

        EditText email = (EditText)findViewById(R.id.editTextEmail);
        EditText cuit = (EditText)findViewById(R.id.editTextCuit);
        monto = (EditText)findViewById(R.id.editTextMontoAInvertir);
        texto = (TextView)findViewById(R.id.textView8);
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                EditText email = (EditText)findViewById(R.id.editTextEmail);
                EditText cuit = (EditText)findViewById(R.id.editTextCuit);
                EditText monto = (EditText)findViewById(R.id.editTextMontoAInvertir);
                TextView texto = (TextView)findViewById(R.id.textView8);
                SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);*/

                //seekBar.set

                //texto.setText(monto.getText());

                if(monto.getText().length() == 0){
                   // Color rojo = ContextCompat.getColor(getBaseContext(),R.color.error);
                    texto.setTextColor(Color.RED);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                texto.setText("Covered: " + progress + "/" + seekBar.getMax());

                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();

            }

        });

    }

}

