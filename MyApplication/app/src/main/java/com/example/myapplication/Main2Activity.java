package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button btn_agree;
    private Button btn_refuse;
    private Button btn_search;
    private TextView txt_dialog;
    private EditText edt_keyword;
    private int statenum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindID();

    }
    private void bindID() {
        btn_agree = findViewById(R.id.button);
        btn_refuse = findViewById(R.id.button3);
        btn_search = findViewById(R.id.button4);
        txt_dialog = findViewById(R.id.textView);
        edt_keyword = findViewById(R.id.editText);
        statenum = 0;
        state0();
    }
    private void state0(){
        txt_dialog.setText("state0");
        btn_agree.setVisibility(View.VISIBLE);
        btn_refuse.setVisibility(View.VISIBLE);
        btn_search.setVisibility(View.INVISIBLE);
        edt_keyword.setVisibility(View.INVISIBLE);
    }
    private void state1(){
        txt_dialog.setText("state1");
    }
    private void state2(){
        txt_dialog.setText("state2");
    }
    private void state3(){
        txt_dialog.setText("state4");
        edt_keyword.setText("visible");
        btn_agree.setVisibility(View.INVISIBLE);
        btn_refuse.setVisibility(View.INVISIBLE);
        btn_search.setVisibility(View.VISIBLE);
        edt_keyword.setVisibility(View.VISIBLE);
    }
    public void refusefunc(View view){
        statenum++;
        switch (statenum){
            case 1: state1();
                break;
            case 2: state2();
                break;
            case 3: state3();
                break;
        }
        Toast.makeText(this, "refuse", Toast.LENGTH_LONG).show();
    }
    public void agreefunc(View view){

        Toast.makeText(this, "agree", Toast.LENGTH_LONG).show();
    }
    public void searchfunc(View view){
        Toast.makeText(this, "search", Toast.LENGTH_LONG).show();
    }
}
