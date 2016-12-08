package edu.upc.eetac.dsa.calculadora2;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UI2 extends AppCompatActivity {

    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui2);
        Bundle dades = getIntent().getExtras();
        String llista = dades.getString("llista");
        String[] operacions;
        int j = 1;
        operacions = llista.split("!");
        StringBuffer total = new StringBuffer();

        System.out.println("tamany   " + operacions.length);
        for (int i = 0; i < operacions.length; i++) {
            list.add(operacions[i]);
        }
        System.out.println("mida   " + list.size());
        for (int i = 0; i < list.size(); i++) {
            total.append(j).append(": ").append(list.get(i)).append("\n");
            j++;
        }

        if (j > 12) {
            Context context = getApplicationContext();
            CharSequence text = "Només es poden mostrar 12 operacions.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        TextView view = (TextView) findViewById(R.id.tvOperacions);
        view.setText(total.toString());
        view.setTextSize(20);

    }

    public void onClickRetorna(View v) {
        Intent intent1 = new Intent();
        EditText et = (EditText) findViewById(R.id.BuscarOperacio);
        String oper = et.getText().toString();
        try {
            int i = Integer.parseInt(oper);
            intent1.putExtra("resultat", list.get(i-1).toString());

            setResult(RESULT_OK, intent1);
            finish();

        }
        catch (Exception e)
        {
            setResult(RESULT_CANCELED, intent1);
            if(list.size()==0) {
                Toast.makeText(getApplicationContext(), "Historial buit",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No has escollit cap operació",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Fora del marge de l'historial",
                            Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    public void onClickBorraHistorial (View v){
        Intent intent = new Intent(UI2.this, UI3.class);
        startActivity(intent);
    }
}
