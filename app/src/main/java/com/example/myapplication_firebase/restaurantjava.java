package com.example.myapplication_firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class restaurantjava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_restaurant);

        RecyclerView recyclerView = findViewById(R.id.recyclerVie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creating the list of restaurants
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("The Gourmet Spot", "123 Street, City", R.drawable.restaurant6, true, "10:00 AM - 10:00 PM"));
        restaurantList.add(new Restaurant("Urban Bites", "456 Avenue, City", R.drawable.restaurant4, false, "9:00 AM - 9:00 PM"));
        restaurantList.add(new Restaurant("Classic Diner", "789 Boulevard, City", R.drawable.restaurant5, true, "8:00 AM - 11:00 PM"));
        restaurantList.add(new Restaurant("Spice Heaven", "101 Food Lane, City", R.drawable.restaurant3, false, "11:00 AM - 11:00 PM"));
        restaurantList.add(new Restaurant("Green Vegan", "202 Healthy Ave, City", R.drawable.restaurant1, true, "7:00 AM - 10:00 PM"));
        restaurantList.add(new Restaurant("Ocean Breeze", "303 Seaside Road, City", R.drawable.restaurant7, true, "12:00 PM - 12:00 AM"));
        restaurantList.add(new Restaurant("Mountain Feast", "404 Highland Way, City", R.drawable.restaurant2, false, "6:00 AM - 9:00 PM"));

        // Set the adapter
        RestaurantAdapter adapter = new RestaurantAdapter(this, restaurantList);
        recyclerView.setAdapter(adapter);
    }

    private static class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
        private final List<Restaurant> restaurantList;
        private final Context context;

        public RestaurantAdapter(Context context, List<Restaurant> restaurantList) {
            this.context = context;
            this.restaurantList = restaurantList != null ? restaurantList : new ArrayList<>();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Restaurant restaurant = restaurantList.get(position);
            holder.name.setText(restaurant.name + "\n" + restaurant.address + "\n🕒 " + restaurant.time);
            holder.image.setImageResource(restaurant.imageResId);

            // Set the text color based on open/close status
            int color = ContextCompat.getColor(context, restaurant.isOpen ? android.R.color.holo_blue_dark : android.R.color.holo_red_dark);
            holder.name.setTextColor(color);

            // Button visibility and functionality
            holder.btnOpen.setVisibility(restaurant.isOpen ? View.GONE : View.VISIBLE);
            holder.btnClose.setVisibility(restaurant.isOpen ? View.VISIBLE : View.GONE);

            holder.btnOpen.setOnClickListener(v -> {
                restaurant.isOpen = true;
                notifyItemChanged(position);
                Toast.makeText(context, restaurant.name + " is now Open", Toast.LENGTH_SHORT).show();
            });

            holder.btnClose.setOnClickListener(v -> {
                restaurant.isOpen = false;
                notifyItemChanged(position);
                Toast.makeText(context, restaurant.name + " is now Closed", Toast.LENGTH_SHORT).show();
            });
        }

        @Override
        public int getItemCount() {
            return restaurantList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView name;
            final ImageView image;
            final Button btnOpen, btnClose;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.restaurant_name);
                image = itemView.findViewById(R.id.restaurant_image);
                btnOpen = itemView.findViewById(R.id.btn_open);
                btnClose = itemView.findViewById(R.id.btn_close);
            }
        }
    }

    public static class Restaurant {
        final String name;
        final String address;
        final int imageResId;
        boolean isOpen;
        final String time;

        public Restaurant(String name, String address, int imageResId, boolean isOpen, String time) {
            this.name = name;
            this.address = address;
            this.imageResId = imageResId;
            this.isOpen = isOpen;
            this.time = time;
        }
    }
}
