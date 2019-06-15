package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {
    private ImageView memeview;
    private ImageButton backtosearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        init();
    }
    private void init(){
        memeview = findViewById(R.id.imageView);
        backtosearch = findViewById(R.id.imageButton3);
    }

    public void Back2Search(View view) {
        Intent intent;
        intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
