package com.projeto.luzvioleta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.luzvioleta.R;
import com.projeto.luzvioleta.data.ReportController;
import com.projeto.luzvioleta.data.model.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportListActivity extends AppCompatActivity {

    private ListView listViewReports;
    private Button btnAddReport;
    private Button btnMedicalAssistance;
    private ReportController reportController;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_report_list); //

        userId = getIntent().getIntExtra("userId", -1);

        listViewReports = findViewById(R.id.listViewReports);
        btnAddReport = findViewById(R.id.btnAddReport);
        btnMedicalAssistance = findViewById(R.id.btnMedicalAssistance);

        reportController = new ReportController(this);

        loadReports();

        btnAddReport.setOnClickListener(v -> {
            Intent intent = new Intent(ReportListActivity.this, AddReportActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        btnMedicalAssistance.setOnClickListener(v -> {
            Intent intent = new Intent(ReportListActivity.this, MedicalAssistanceActivity.class);
            intent.putExtra("userId", userId); // ✅ importante passar userId corretamente
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReports();
    }

    private void loadReports() {
        List<Report> reports = reportController.getReportsByUserId(userId);
        if (reports.isEmpty()) {
            Toast.makeText(this, "Nenhum relatório encontrado", Toast.LENGTH_SHORT).show();
        }

        List<String> reportDescriptions = new ArrayList<>();
        for (Report r : reports) {
            reportDescriptions.add(r.getDate() + " - " + r.getDescription());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reportDescriptions);
        listViewReports.setAdapter(adapter);
    }
}
