package com.example.nomnom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

@SuppressLint("CustomSplashScreen")
public class splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen); // xml file

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.taco);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textView = findViewById(R.id.logo);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.bagel_fat_one);
        textView.setTypeface(typeface);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView1 = findViewById(R.id.tagline);
        Typeface typeface1 = ResourcesCompat.getFont(this, R.font.allerta_stencil);
        textView1.setTypeface(typeface);

        TextView textView2 = findViewById(R.id.textView3);
        Typeface typeface2 = ResourcesCompat.getFont(this, R.font.alkatra);
        textView2.setTypeface(typeface);



        // Delay the splash screen for 2 seconds (2000ms)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashscreen.this, startactivity.class);
                startActivity(intent);
                finish(); // Close SplashScreen so user can't go back to it
            }
        }, 2000); // 2000 milliseconds = 2 seconds
    }
}
