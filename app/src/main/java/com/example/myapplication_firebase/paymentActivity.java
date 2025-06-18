package com.example.myapplication_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class paymentActivity extends AppCompatActivity {
    private Button move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_payment); // Ensure this layout exists

        move = findViewById(R.id.re);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(paymentActivity.this, test2.class);
                startActivity(intent);
            }
        });

        // Initialize buttons
        Button btnGooglePay = findViewById(R.id.btnGooglePay);
        Button btnPhonePe = findViewById(R.id.btnPhonePe);
        Button btnQRCode = findViewById(R.id.btnQRCode);
        Button btnNetBanking = findViewById(R.id.btnNetBanking);

        // Set click listeners
        btnGooglePay.setOnClickListener(v -> showToast("Google Pay selected"));
        btnPhonePe.setOnClickListener(v -> showToast("PhonePe selected"));
        btnQRCode.setOnClickListener(v -> showToast("QR Code selected"));
        btnNetBanking.setOnClickListener(v -> showToast("Net Banking selected"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
