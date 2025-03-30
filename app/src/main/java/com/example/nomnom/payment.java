package com.example.nomnom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class payment extends AppCompatActivity {
    private TextView totalAmountText;
    private Button upiPayButton;
    private int totalAmount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalAmountText = findViewById(R.id.total_amount);
        upiPayButton = findViewById(R.id.upiPayButton);

        // Get total amount dynamically from Intent
        totalAmount = getIntent().getIntExtra("totalAmount", 0);
        totalAmountText.setText("Total: ₹" + totalAmount);

        upiPayButton.setOnClickListener(v -> makeUpiPayment());
    }

    private void makeUpiPayment() {
        String upiId = "merchant@upi"; // Replace with actual UPI ID
        String name = "NomNom Café";
        String note = "Order Payment";
        String amount = String.valueOf(totalAmount);

        Uri uri = Uri.parse("upi://pay")
                .buildUpon()
                .appendQueryParameter("pa", upiId) // UPI ID
                .appendQueryParameter("pn", name) // Payee Name
                .appendQueryParameter("tn", note) // Transaction Note
                .appendQueryParameter("am", amount) // Amount
                .appendQueryParameter("cu", "INR") // Currency
                .build();

        Log.d("UPI Payment", "Intent URI: " + uri.toString());

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);

        // Try Google Pay first, then other apps if not found
        intent.setPackage("com.google.android.apps.nbu.paisa.user"); // Google Pay

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        } else {
            // If Google Pay is not available, allow any UPI app
            intent.setPackage(null);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "No UPI app found, please install one!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Handle UPI Payment Response
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                String response = data.getStringExtra("response");
                Log.d("UPI Payment", "Response: " + response);
                if (response != null && response.toLowerCase().contains("success")) {
                    Toast.makeText(this, "Payment Successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Payment Failed!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Payment Cancelled!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
