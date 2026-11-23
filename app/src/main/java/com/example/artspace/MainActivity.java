package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int[] images = {
            R.drawable.art1, R.drawable.art2, R.drawable.art3,
            R.drawable.art4, R.drawable.art5, R.drawable.art6
    };
    String[] titles = {
            "Deep Field Stellar Deaths", "Blue Giant Crossing", "Hidden Galaxies",
            "Silent Dark Field", "Colors of the Cosmos", "Hubble Ngc Flat Final"
    };
    String[] artists = {
            "Smacs Carin", "Lena Orbit", "Omar Nasser",
            "Maya Stellan", "Karim Halley", "Sonay Billan"
    };
    String[] years = {
            "2021", "2020", "2020", "2019", "2018", "2019"
    };

    int index = 0;

    TextView counterText;
    TextView titleText;
    TextView artistText;
    ImageView artImage;
    Button previousButton;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.tvCounter);
        titleText = findViewById(R.id.tvTitle);
        artistText = findViewById(R.id.tvArtist);
        artImage = findViewById(R.id.imgArtwork);
        previousButton = findViewById(R.id.btnPrevious);
        nextButton = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index");
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < images.length - 1) {
                    index = index + 1;
                    showArtwork();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    index = index - 1;
                    showArtwork();
                }
            }
        });

        artImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
                intent.putExtra("title", titles[index]);
                intent.putExtra("artist", artists[index]);
                intent.putExtra("year", years[index]);
                startActivity(intent);
            }
        });

        showArtwork();
    }

    void showArtwork() {
        artImage.setImageResource(images[index]);
        titleText.setText(titles[index]);
        artistText.setText(artists[index] + "  (" + years[index] + ")");
        counterText.setText((index + 1) + " / " + images.length);

        if (index == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }

        if (index == images.length - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", index);
    }
}
