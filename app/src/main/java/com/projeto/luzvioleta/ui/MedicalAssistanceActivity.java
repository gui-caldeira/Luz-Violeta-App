package com.projeto.luzvioleta.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.luzvioleta.R;
import com.projeto.luzvioleta.data.MedicalAssistanceController;
import com.projeto.luzvioleta.data.model.MedicalAssistance;

public class MedicalAssistanceActivity extends AppCompatActivity {

    private EditText editType, editDate;
    private Button btnSaveAssistance, btnHelpSite;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_assistance);

        editType = findViewById(R.id.editType);
        editDate = findViewById(R.id.editDate);
        btnSaveAssistance = findViewById(R.id.btnSaveAssistance);
        btnHelpSite = findViewById(R.id.btnHelpSite);

        // Recebe o userId passado pela tela anterior
        userId = getIntent().getIntExtra("userId", -1);

        btnSaveAssistance.setOnClickListener(v -> {
            String type = editType.getText().toString().trim();
            String date = editDate.getText().toString().trim();

            if (type.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            MedicalAssistance assistance = new MedicalAssistance();
            assistance.setType(type);
            assistance.setDate(date);
            assistance.setUserId(userId);

            MedicalAssistanceController controller = new MedicalAssistanceController(this);
            long id = controller.insertMedicalAssistance(assistance);
            if (id > 0) {
                Toast.makeText(this, "Assistência médica salva", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao salvar assistência médica", Toast.LENGTH_SHORT).show();
            }
        });

        btnHelpSite.setOnClickListener(v -> {
            String url = "https://www.gov.br/mulheres/pt-br/ligue180";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}
