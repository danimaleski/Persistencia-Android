package com.example.udesc.persistencia_application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.udesc.persistencia_application.validator.Validator;

import java.io.InputStream;

public class PersMainActivity extends AppCompatActivity {
    EditText usuario;
    EditText senha;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pers_main);

        InputStream is = getResources().openRawResource(R.raw.super_users);
        validator = new Validator(is);

        usuario = findViewById(R.id.user);
        senha = findViewById(R.id.pass);

        Button confirmar = findViewById(R.id.confirmar);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = validator.validateCredentials(usuario.getText().toString(), senha.getText().toString());

                if (valid) {
                    Toast.makeText(PersMainActivity.this, "Deu certo!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PersMainActivity.this, "Deu errado!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        validator.criarArquivo(getFilesDir(), validator.getList());
    }


}
