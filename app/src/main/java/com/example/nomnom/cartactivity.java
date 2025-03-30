package com.example.nomnom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class cartactivity extends AppCompatActivity {
    private LinearLayout cartContainer;
    private TextView totalAmountTextView;
    private Button checkoutButton;
    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity);

        cartContainer = findViewById(R.id.cart_container);
        totalAmountTextView = findViewById(R.id.total_amount);
        checkoutButton = findViewById(R.id.checkout_button);

        // Get cart list from intent
        ArrayList<CartItem> cartList = (ArrayList<CartItem>) getIntent().getSerializableExtra("cartList");

        if (cartList != null && !cartList.isEmpty()) {
            for (CartItem item : cartList) {
                // Create a TextView for each item
                TextView textView = new TextView(this);
                textView.setText(item.getName() + " - ₹" + item.getPrice());
                textView.setTextSize(18);
                cartContainer.addView(textView);
                totalAmount += item.getPrice();
            }
        } else {
            // If no items in the cart
            TextView emptyText = new TextView(this);
            emptyText.setText("Your cart is empty.");
            emptyText.setTextSize(18);
            cartContainer.addView(emptyText);
        }

        totalAmountTextView.setText("Total: ₹" + totalAmount);

        checkoutButton.setOnClickListener(v -> {
            // Move to PaymentActivity with the total amount
            Intent intent = new Intent(cartactivity.this, payment.class);
            intent.putExtra("totalAmount", totalAmount);
            startActivity(intent);

    });
    }
}
