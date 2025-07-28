package com.projeto.luzvioleta.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.luzvioleta.R;
import com.projeto.luzvioleta.data.ReportController;
import com.projeto.luzvioleta.data.model.Report;

public class AddReportActivity extends AppCompatActivity {

    private EditText editDescription, editDate;
    private Button btnSaveReport;

    private ReportController reportController;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);
        btnSaveReport = findViewById(R.id.btnSaveReport);

        reportController = new ReportController(this);

        userId = getIntent().getIntExtra("userId", -1);

        btnSaveReport.setOnClickListener(v -> {
            String description = editDescription.getText().toString().trim();
            String date = editDate.getText().toString().trim();

            if(description.isEmpty() || date.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Report report = new Report();
            report.setUserId(userId);
            report.setDescription(description);
            report.setDate(date);

            long id = reportController.insertReport(report);
            if(id > 0){
                Toast.makeText(this, "Relatório adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                finish(); // fecha a tela e volta para a lista
            } else {
                Toast.makeText(this, "Erro ao adicionar relatório", Toast.LENGTH_SHORT).show();
            }
        });
    }
}