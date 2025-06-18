package com.example.myapplication_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityByProducts extends AppCompatActivity {

    private Button btnHome, btnMenu, btnRestaurant, btnPayment, btnReview;
    private Button btnBookRestaurant, btnOrderFood;
    private EditText editTextSearch;

    // 🔁 Auto-scroll slider variables
    private HorizontalScrollView horizontalScrollView;
    private Handler sliderHandler;
    private Runnable sliderRunnable;
    private int currentAdIndex = 0;
    private int[] adOffsets;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_products);

        // Initialize navigation buttons
        btnMenu = findViewById(R.id.btnMenu);
        btnRestaurant = findViewById(R.id.btnRestaurant);
        btnPayment = findViewById(R.id.btnPayment);
        btnReview = findViewById(R.id.btnReview);

        // Initialize "Book Now" and "Order Now" buttons
        btnBookRestaurant = findViewById(R.id.btnBookRestaurant);
        btnOrderFood = findViewById(R.id.btnOrderFood);

        // ✅ Search bar setup
        editTextSearch = findViewById(R.id.editTextSearch);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim().toLowerCase();

                switch (query) {
                    case "menu":
                    case "food":
                        Toast.makeText(ActivityByProducts.this, "Opening Menu...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityByProducts.this, Menu56act.class));
                        break;

                    case "review":
                    case "feedback":
                        Toast.makeText(ActivityByProducts.this, "Opening Review Page...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityByProducts.this, test2.class));
                        break;

                    case "payment":
                    case "pay":
                        Toast.makeText(ActivityByProducts.this, "Opening Payment...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityByProducts.this, paymentActivity.class));
                        break;

                    case "restaurant":
                    case "book":
                    case "booking":
                        Toast.makeText(ActivityByProducts.this, "Opening Restaurant...", Toast.LENGTH_SHORT).show();
                        loadFragment(new RestaurantFragment(), false);
                        break;

                    default:
                        if (!query.isEmpty()) {
                            Toast.makeText(ActivityByProducts.this, "No matching page found for: " + query, Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // ✅ Book Now → Restaurant Fragment
        btnBookRestaurant.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Restaurant Booking...", Toast.LENGTH_SHORT).show();
            loadFragment(new RestaurantFragment(), false);
        });

        // ✅ Order Now → Menu Activity
        btnOrderFood.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Food Ordering...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Menu56act.class);
            startActivity(intent);
        });

        // ✅ Navigation button logic
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Menu56act.class);
            startActivity(intent);
        });

        btnRestaurant.setOnClickListener(v -> {
            loadFragment(new RestaurantFragment(), false);
        });

        btnPayment.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), paymentActivity.class);
            startActivity(intent);
        });

        btnReview.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), test2.class);
            startActivity(intent);
        });

        // 🔁 Auto-scrolling ads setup
        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        LinearLayout adContainer = findViewById(R.id.adContainer);

        adContainer.post(() -> {
            int childCount = adContainer.getChildCount();
            adOffsets = new int[childCount];

            for (int i = 0; i < childCount; i++) {
                adOffsets[i] = adContainer.getChildAt(i).getLeft();
            }

            sliderHandler = new Handler();
            sliderRunnable = new Runnable() {
                @Override
                public void run() {
                    horizontalScrollView.smoothScrollTo(adOffsets[currentAdIndex], 0);
                    currentAdIndex = (currentAdIndex + 1) % adOffsets.length;
                    sliderHandler.postDelayed(this, 3000); // every 3 seconds
                }
            };
            sliderHandler.postDelayed(sliderRunnable, 3000);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sliderHandler != null && sliderRunnable != null) {
            sliderHandler.removeCallbacks(sliderRunnable);
        }
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialised) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialised) {
            fragmentTransaction.add(R.id.container, fragment);
        } else {
            fragmentTransaction.replace(R.id.container, fragment);
        }
        fragmentTransaction.commit();
    }
}
