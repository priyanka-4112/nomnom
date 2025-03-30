package com.example.nomnom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class landing extends AppCompatActivity {

    private List<CartItem> cartList = new ArrayList<>();
    private Button viewCartButton;  // Button to go to cart

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing); // Ensure this layout contains ScrollView

        viewCartButton = findViewById(R.id.buttonvc);

        // Find all "ADD TO CART" buttons & handle clicks
        setupAddToCartButton(R.id.bfmomo, "Fried Momos", 120);
        setupAddToCartButton(R.id.bsmomo, "Steamed Momos", 100);
        setupAddToCartButton(R.id.bsan, "Sandwich", 80);
        setupAddToCartButton(R.id.bnod, "Noodles", 90);
        setupAddToCartButton(R.id.bman, "Manchurian", 110);
        setupAddToCartButton(R.id.bpf, "Paneer Frankie", 130);
        setupAddToCartButton(R.id.bdosa, "Dosa", 140);

        // Open CartActivity when clicking "View Cart"
        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(landing.this, cartactivity.class);
            intent.putExtra("cartList", new ArrayList<>(cartList)); // Pass cart items
            startActivity(intent);
        });
    }

    // Helper method to set up buttons
    private void setupAddToCartButton(int buttonId, String itemName, int price) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            cartList.add(new CartItem(itemName, price));
            Toast.makeText(this, itemName + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}
