package com.projeto.luzvioleta.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.luzvioleta.R;
import com.projeto.luzvioleta.data.UserController;
import com.projeto.luzvioleta.data.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUser, editPassword, editConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String userEmail = editUser.getText().toString().trim();
            String pass = editPassword.getText().toString().trim();
            String confirmPass = editConfirmPassword.getText().toString().trim();

            if (userEmail.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cria e salva o usuário no banco
            UserController userController = new UserController(this);
            User newUser = new User();
            newUser.setName("Usuário"); // Pode trocar para um campo separado se quiser nome
            newUser.setEmail(userEmail);
            newUser.setPassword(pass);

            long id = userController.insertUser(newUser);
            if (id > 0) {
                Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish(); // Volta pra tela de login
            } else {
                Toast.makeText(this, "Erro ao registrar usuário. Email pode já estar cadastrado.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
