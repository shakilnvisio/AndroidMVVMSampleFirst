package com.nvisio.mvvm.androidmvvmsamplefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.ui.BasicSampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BasicClicked(View view) {
        startActivity(new Intent(MainActivity.this, BasicSampleActivity.class));
        finish();
    }
}
