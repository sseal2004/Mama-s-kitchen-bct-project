package com.example.myapplication_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser, etPad, etRepad;
    Button btnRegister, btnGoToLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = findViewById(R.id.etUsername);
        etPad = findViewById(R.id.etPassword);
        etRepad = findViewById(R.id.etRepassword);
        btnRegister = findViewById(R.id.btnRegister);
        dbHelper = new DBHelper(this);
        btnGoToLogin = findViewById(R.id.btnLogin);

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent); // Fixed startActivity()
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, pad, rePad;
                user = etUser.getText().toString();
                pad = etPad.getText().toString();
                rePad = etRepad.getText().toString();

                if (user.equals("") || pad.equals("") || rePad.equals("")) {
                    Toast.makeText(ActivityRegister.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    if (pad.equals(rePad)) {
                        // Proceed with Registration
                        if (dbHelper.checkUsername(user)) {
                            Toast.makeText(ActivityRegister.this, "User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }

                        boolean registeredSuccess = dbHelper.insertData(user, pad);
                        if (registeredSuccess) { // Fixed condition check
                            Toast.makeText(ActivityRegister.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ActivityRegister.this, "User Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(ActivityRegister.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

