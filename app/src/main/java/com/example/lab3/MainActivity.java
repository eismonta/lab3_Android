package com.example.lab3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void passData(String data) {
        ResultFragment resFrag = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.container_result);
        if (resFrag != null) resFrag.updateLabel(data);
    }

    public void clearAll() {
        InputFragment inFrag = (InputFragment) getSupportFragmentManager().findFragmentById(R.id.container_input);
        ResultFragment resFrag = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.container_result);
        if (inFrag != null) inFrag.reset();
        if (resFrag != null) resFrag.updateLabel("Чекаю на вибір...");
    }
}