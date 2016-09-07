package ar.edu.utn.frsf.isi.dam;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView texto;
    private TextView rendimiento;
    private TextView textoDias;
    private EditText monto;
    private EditText email;
    private EditText cuit;
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

        email = (EditText) findViewById(R.id.editTextEmail);
        cuit = (EditText) findViewById(R.id.editTextCuit);
        monto = (EditText) findViewById(R.id.editTextMontoAInvertir);
        texto = (TextView) findViewById(R.id.textView8);
        textoDias = (TextView) findViewById(R.id.textViewDias);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        rendimiento = (TextView) findViewById(R.id.textView7);

        t05menor30 = getResources().getString(R.string.t_0_5000_menor30);
        t05mayor30 = getResources().getString(R.string.t_0_5000_mayorIgual30);
        t59menor30 = getResources().getString(R.string.t_5000_99999_menor30);
        t59mayor30 = getResources().getString(R.string.t_mas_99999_mayorIgual30);
        tm9menor30 = getResources().getString(R.string.t_mas_99999_menor30);
        tm9mayor30 = getResources().getString(R.string.t_5000_99999_mayorIgual30);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((monto.getText().length() == 0) || (progress == 0) || (email.getText().length() == 0) || (cuit.getText().length() == 0)) {
                    int rojo = (ContextCompat.getColor(getApplicationContext(), R.color.error));
                    texto.setTextColor(rojo);

                    String textoMail = email.getText().toString();

                    if (validarMail(textoMail) == false) {
                        texto.setText("Email inválido");

                    } else if (cuit.getText().length() == 0)
                    {

                        texto.setText("Por favor, complete el CUIT");

                    }


                    else if (monto.getText().length() == 0)

                        texto.setText("Debe ingresar el monto a invertir.");

                    else if (progress == 0)

                        texto.setText("Debe ingresas la cantidad de días.");
                } else {
                    int monto2 = Integer.parseInt(monto.getText().toString());
                    double interes = calcuarInteres(progress, monto2);
                    int verde = (ContextCompat.getColor(getApplicationContext(), R.color.correcto));
                    texto.setTextColor(verde);

                    double total = monto2 + interes;
                    texto.setText("Plazo fijo realizado. Recibirá de interés $" + interes + " al vencimiento y un total de $ " + total);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                textoDias.setText("" + progress);


                mostrarInteres();
                /*int stepSize = 25;

                progress = (progress/stepSize)*stepSize;
                seekBar.setProgress(progress);

                sliderText.setText("" + progress);*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textoDias.setText("" + progress);
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textoDias.setText("");
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();

            }
        });

        monto.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                mostrarInteres();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }

    private double calcuarInteres(int dias, int monto) {

        double interes = 0;

        double tasa = getTasa(dias, monto);

        double valor1 = tasa / 100.0;
        double valor2 = dias / 360.0;
        double potencia = Math.pow((1 + valor1), valor2);

        interes = monto * Math.abs(potencia - 1);

        // Redondeamos el valor del interes

        double i_round = (double) Math.round(interes * 100) / 100;

        return i_round;

    }

    private void mostrarInteres()
    {
        if ((monto.getText().length() == 0) || (progress == 0)) {


            if (monto.getText().length() == 0) {
                rendimiento.setText("Los intereses no pueden ser calculados. Debe ingresar el monto a invertir.");
            }
            else{
                rendimiento.setText("Los intereses no pueden ser calculados. Elija la cantidad de días.");
            }
        } else {
            int monto2 = Integer.parseInt(monto.getText().toString());
            double interes = calcuarInteres(progress, monto2);

            rendimiento.setText("Intereses ganados: $" + interes);
        }
    }

    private double getTasa(int dias, int monto) {
        String tasa = "0";

        if (monto < 5000) {
            if (dias < 30)
                tasa = getResources().getString(R.string.t_0_5000_menor30);
            else
                tasa = getResources().getString(R.string.t_0_5000_mayorIgual30);
        } else if (monto < 99999) {
            if (dias < 30)
                tasa = getResources().getString(R.string.t_5000_99999_menor30);
            else
                tasa = getResources().getString(R.string.t_5000_99999_mayorIgual30);
        } else {
            if (dias < 30)
                tasa = getResources().getString(R.string.t_5000_99999_menor30);
            else
                tasa = getResources().getString(R.string.t_5000_99999_mayorIgual30);
        }


        double tasaFinal = Double.parseDouble(tasa);
        //double tasaFinal = Integer.parseInt(tasa);

        return tasaFinal;

    }

    public static boolean validarMail(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}

