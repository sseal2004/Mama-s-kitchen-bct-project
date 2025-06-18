package com.example.myapplication_firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class test2 extends AppCompatActivity {  // Extended AppCompatActivity
    RatingBar ratingBar;
    EditText reviewInput;
    Button submitButton;
    TextView displayReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_review);  // Linking the XML layout

        // Initialize UI elements
        ratingBar = findViewById(R.id.ratingBar);
        reviewInput = findViewById(R.id.reviewInput);
        submitButton = findViewById(R.id.submitButton);
        displayReview = findViewById(R.id.displayReview);

        // Handle the Submit Button Click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();          // Get the rating
                String reviewText = reviewInput.getText().toString();  // Get the review text

                if (rating > 0 && !reviewText.isEmpty()) {
                    // Display the review
                    String result = "⭐ Rating: " + rating + "/5\n" + "📝 Review: " + reviewText;
                    displayReview.setText(result);

                    // Clear inputs after submission
                    ratingBar.setRating(0);
                    reviewInput.setText("");

                    Toast.makeText(test2.this, "Review Submitted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(test2.this, "Please provide both rating and review.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

