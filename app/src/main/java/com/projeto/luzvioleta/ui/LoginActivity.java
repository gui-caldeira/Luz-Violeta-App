package com.projeto.luzvioleta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.luzvioleta.R;
import com.projeto.luzvioleta.data.UserController;

public class LoginActivity extends AppCompatActivity {

    private EditText editUser, editPassword;
    private Button btnLogin, btnRegister;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        userController = new UserController(this);

        btnLogin.setOnClickListener(v -> {
            String email = editUser.getText().toString().trim();
            String senha = editPassword.getText().toString().trim();

            int userId = userController.autenticarUsuario(email, senha);

            if (userId != -1) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
