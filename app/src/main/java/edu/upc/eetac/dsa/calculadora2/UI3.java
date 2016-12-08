package edu.upc.eetac.dsa.calculadora2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UI3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui3);
    }

    public void onClickNo (View v){
        finish();
    }

    public void onClickSi (View v){
        Intent intent = new Intent(UI3.this, UI1.class);
        startActivity(intent);
    }
}
