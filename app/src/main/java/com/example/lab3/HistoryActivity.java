package com.example.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HistoryActivity extends AppCompatActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvContent = findViewById(R.id.tvFileContent);
        Button back = findViewById(R.id.btnBack);
        Button clear = findViewById(R.id.btnClearHistory);

        loadData();

        back.setOnClickListener(v -> finish());

        clear.setOnClickListener(v -> {
            deleteFile("lab_storage.txt");
            tvContent.setText("Сховище порожнє");
            Toast.makeText(this, "Історію видалено", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadData() {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = openFileInput("lab_storage.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            tvContent.setText(sb.toString().isEmpty() ? "Сховище порожнє" : sb.toString());
        } catch (Exception e) {
            tvContent.setText("Сховище порожнє");
        }
    }
}