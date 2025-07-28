package com.projeto.luzvioleta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.luzvioleta.R;

public class MainActivity extends AppCompatActivity {

    private Button btnReportList, btnLogout;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = getIntent().getIntExtra("userId", -1);

        btnReportList = findViewById(R.id.btnReportList);
        btnLogout = findViewById(R.id.btnLogout);

        btnReportList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportListActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            finish();
        });
    }
}
