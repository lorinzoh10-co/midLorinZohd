package com.example.lorinzohd_2320222600_artspace;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.TextView;

public class ArtworkDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// Connect Java variables to the views in activity_artwork_details.xml

        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailArtist = findViewById(R.id.tvDetailArtist);
        TextView tvDetailYear = findViewById(R.id.tvDetailYear);
        Button btnBack = findViewById(R.id.btnBack);
// Read the data that MainActivity sent with the Intent (keys must match putExtra)

        String title = getIntent().getStringExtra("title");
        String artist = getIntent().getStringExtra("artist");
        String year = getIntent().getStringExtra("year");
// Show the received data on the screen

        tvDetailTitle.setText(title);
        tvDetailArtist.setText(artist);
        tvDetailYear.setText(year);
// Close this screen and go back to MainActivity (same artwork as before)

        btnBack.setOnClickListener(v -> finish());

        // Open a web page with an implicit intent (the system picks the browser)
        Button btnWebsite = findViewById(R.id.btnWebsite);
        btnWebsite.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikiart.org"));
            startActivity(intent);
        });
    }
}
