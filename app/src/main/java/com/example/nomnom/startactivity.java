package com.example.nomnom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class startactivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView imageView = findViewById(R.id.dinner);
        imageView.setImageResource(R.drawable.dinner);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textView = findViewById(R.id.textView);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.kalam_light);
        textView.setTypeface(typeface);

        Button loginButton = findViewById(R.id.button);

        // Set OnClickListener to navigate to loginactivity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to loginactivity
                Intent intent = new Intent(startactivity.this, loginactivity.class);
                startActivity(intent);
            }
        }); // Missing closing bracket fixed here
    } // Missing closing bracket fixed here
}
