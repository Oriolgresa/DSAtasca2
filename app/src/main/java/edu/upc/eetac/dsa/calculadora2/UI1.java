package edu.upc.eetac.dsa.calculadora2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class UI1 extends AppCompatActivity {

    Integer numero1, numero2;
    Double resultatOperacio;
    StringBuffer llista = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui1);
    }

    public void onClickResultat(View v) {
        RadioButton botoSuma = (RadioButton) findViewById(R.id.rbSuma);
        RadioButton botoResta = (RadioButton) findViewById(R.id.rbResta);
        RadioButton botoMult = (RadioButton) findViewById(R.id.rbMult);
        RadioButton botoDiv = (RadioButton) findViewById(R.id.rbDiv);
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        TextView resultat = (TextView) findViewById(R.id.resultat);

        try {
            numero1 = Integer.parseInt(num1.getText().toString());
            numero2 = Integer.parseInt(num2.getText().toString());

            Context context = getApplicationContext();
            CharSequence text = "Operació resolta";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            if (botoSuma.isChecked()) {
                resultatOperacio = (double) numero1 + (double) numero2;
                llista.append(numero1).append("+").append(numero2);
            } else if (botoResta.isChecked()) {
                resultatOperacio = (double) numero1 - (double) numero2;
                llista.append(numero1).append("-").append(numero2);
            } else if (botoDiv.isChecked()) {
                resultatOperacio = (double) numero1 / (double) numero2;
                llista.append(numero1).append("/").append(numero2);
            } else if (botoMult.isChecked()) {
                resultatOperacio = (double) numero1 * (double) numero2;
                llista.append(numero1).append("*").append(numero2);
            }

            DecimalFormat format = new DecimalFormat("#.###");
            String res = format.format(resultatOperacio);
            llista.append("=").append(res).append("!");
            resultat.setText(res.toString());

        }
        catch (Exception e) {

            Context context = getApplicationContext();
            CharSequence text2 = "Cal indicar els dos valors numèrics";
            int duration = Toast.LENGTH_SHORT;

            Toast toast2 = Toast.makeText(context, text2, duration);
            toast2.show();

        }
    }

    public void onClickEsborra(View v) {
        numero1 = 0;
        numero2 = 0;
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        TextView resultat = (TextView) findViewById(R.id.resultat);

        num1.setText("0");
        num2.setText("0");
        resultat.setText("0.0");
    }

    public void onClickHistorial(View v){

        if (llista.toString().equals("")) {
            Context context = getApplicationContext();
            CharSequence text = "Fes primer algun càlcul";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            Intent intent1 = new Intent(UI1.this, UI2.class);
            intent1.putExtra("llista", llista.toString());
            startActivityForResult(intent1, 100);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if((requestCode==100) && (resultCode== Activity.RESULT_OK)){
            Bundle rebut = data.getExtras();
            String res = rebut.getString("resultat");
            EditText num1 = (EditText) findViewById(R.id.num1);
            EditText num2 = (EditText) findViewById(R.id.num2);
            TextView resultat = (TextView) findViewById(R.id.resultat);
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

            String[] fresult=null;
            String[] stresult1= res.split("=");
            if(stresult1[0].contains("+")){
                fresult= stresult1[0].split("\\+");
                rg.check(R.id.rbSuma);

            }
            if(stresult1[0].contains("-")){
                fresult= stresult1[0].split("-");
                rg.check(R.id.rbResta);

            }
            if(stresult1[0].contains("*")){
                fresult= stresult1[0].split("\\*");
                rg.check(R.id.rbMult);

            }
            if(stresult1[0].contains("/")){
                fresult= stresult1[0].split("/");
                rg.check(R.id.rbDiv);

            }
            num1.setText(fresult[0]);
            num2.setText(fresult[1]);
            resultat.setText("0");

        }
    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();
        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.rbSuma:
                if (checked)
                    break;
            case R.id.rbResta:
                if (checked)
                    break;
            case R.id.rbMult:
                if (checked)
                    break;
            case R.id.rbDiv:
                if (checked)
                    break;
        }
    }
}
