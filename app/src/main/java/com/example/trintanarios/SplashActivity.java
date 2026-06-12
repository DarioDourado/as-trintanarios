package com.example.trintanarios;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2500; // 2.5 seconds delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            View footer = findViewById(R.id.footer_container);
            if (footer != null) {
                footer.setPadding(0, 0, 0, systemBars.bottom);
            }
            return insets;
        });

        ImageView splashLogo = findViewById(R.id.splash_logo);
        TextView splashTitle = findViewById(R.id.splash_title);
        TextView splashSubtitle = findViewById(R.id.splash_subtitle);
        View footerContainer = findViewById(R.id.footer_container);

        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.anim_splash_logo);
        Animation textAnim = AnimationUtils.loadAnimation(this, R.anim.anim_splash_text);

        if (splashLogo != null) {
            splashLogo.startAnimation(logoAnim);
        }
        if (splashTitle != null) {
            splashTitle.startAnimation(textAnim);
        }
        if (splashSubtitle != null) {
            splashSubtitle.startAnimation(textAnim);
        }
        if (footerContainer != null) {
            footerContainer.startAnimation(textAnim);
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            
            finish();
        }, SPLASH_DELAY);
    }
}
