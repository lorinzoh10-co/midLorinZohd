package com.example.lorinzohd_2320222600_artspace;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    ImageView imgArt;
    TextView tvCounter, tvTitle, tvArtist;
    Button btnPrev, btnNext;


    int[] images = {R.drawable.img, R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5};

    String[] titles = {"Color Blocks", "Cosmic Dawn", "Blue Currents",
            "Midnight Peaks", "Circles of Time", "Golden Horizon"};

    String[] artists = {"Karim Odeh", "Nour Saleh", "Yousef Amin",
            "Sara Khalil", "Omar Nasser", "Lina Haddad"};

    String[] years = {"2020", "2025", "2021", "2024", "2022", "2023"};

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgArt = findViewById(R.id.imgArt);
        tvCounter = findViewById(R.id.tvCounter);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtist = findViewById(R.id.tvArtist);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            index++;
            Update_UI();
        });
        btnPrev.setOnClickListener(v -> {
            index--;
            Update_UI();
        });
        // لما الـ Activity تنعمل من جديد بعد التدوير، الـ Bundle بيرجع هون
// Restore the saved artwork index after rotation (null on first launch)
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index", 0);
        }
        // When the image is clicked, open ArtworkDetailsActivity (Explicit Intent)
        imgArt.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("title", titles[index]);   // send current artwork title
            intent.putExtra("artist", artists[index]); // send current artist
            intent.putExtra("year", years[index]);     // send current year
            startActivity(intent);
        });

        Update_UI();
    }

    // Called by Android before the Activity is destroyed (e.g. on rotation)
// Saves the current artwork index in the Bundle
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", index);
    }

    private void Update_UI() {
        imgArt.setImageResource(images[index]);
        tvTitle.setText(titles[index]);
        tvArtist.setText(artists[index] + " (" + years[index] + ")");
        tvCounter.setText((index + 1) + " / " + images.length);
        btnPrev.setEnabled(index > 0);
        btnNext.setEnabled(index < images.length - 1);
    }
}