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
    private TextView textoDias;
    private EditText monto;
    private int progress = 0;

    private String t05menor30;
    private String t05mayor30;
    private String t59menor30;
    private String t59mayor30;
    private String tm9menor30;
    private String tm9mayor30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.botonPlazoFijo);

        EditText email = (EditText)findViewById(R.id.editTextEmail);
        EditText cuit = (EditText)findViewById(R.id.editTextCuit);
        monto = (EditText)findViewById(R.id.editTextMontoAInvertir);
        texto = (TextView)findViewById(R.id.textView8);
        textoDias = (TextView)findViewById(R.id.textViewDias);
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        t05menor30 = getResources().getString(R.string.t_0_5000_menor30);
        t05mayor30 = getResources().getString(R.string.t_0_5000_mayorIgual30);
        t59menor30 = getResources().getString(R.string.t_5000_99999_menor30);
        t59mayor30 = getResources().getString(R.string.t_mas_99999_mayorIgual30);
        tm9menor30 = getResources().getString(R.string.t_mas_99999_menor30);
        tm9mayor30 = getResources().getString(R.string.t_5000_99999_mayorIgual30);

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(monto.getText().length() == 0){
                    int rojo = (ContextCompat.getColor(getApplicationContext(), R.color.error));
                    texto.setTextColor(rojo);
                    texto.setText("Debe ingresar un monto");
                }else{
                    int monto2= Integer.parseInt(monto.getText().toString());
                    int interes = calcuarInteres(progress,monto2);
                    int verde = (ContextCompat.getColor(getApplicationContext(), R.color.correcto));
                    texto.setTextColor(verde);
                    texto.setText("Plazo fijo realizado. Recibirá $"+interes+" al vencimiento!");
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                textoDias.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textoDias.setText(""+progress);
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textoDias.setText("");
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int calcuarInteres(int dias, int monto){
        Integer interes=0;



        return interes;
    }
}

