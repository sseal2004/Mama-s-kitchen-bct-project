package com.example.myapplication_firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class Menu56act extends AppCompatActivity {

    private Map<String, Integer> cart = new HashMap<>();
    private TextView totalCostTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu56act);

        // Initialize the total cost TextView
        totalCostTextView = findViewById(R.id.totalCostTextView);

        // Set up button listeners for each menu item
        setupButton(R.id.button2, "Chicken Burger", 99);
        setupButton(R.id.button18, "White Sauce Pasta", 129);
        setupButton(R.id.button12, "Dal Makhni", 99);
        setupButton(R.id.button11, "Chicken Momo", 59);
        setupButton(R.id.button10, "Fried Chicken", 119);
        setupButton(R.id.button9, "Veg Hakka Noodles", 139);
        setupButton(R.id.button8, "Veg Momo", 49);
        setupButton(R.id.button7, "Chicken Wrap", 89);
        setupButton(R.id.button6, "Mixed Hakka Noodles", 179);
        setupButton(R.id.button5, "Veg Wrap", 79);
        setupButton(R.id.button4, "Chicken Pizza", 289);
        setupButton(R.id.button3, "Veg Burger", 79);
    }

    private void setupButton(int buttonId, String itemName, int price) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(itemName, price);
            }
        });
    }

    private void addToCart(String itemName, int price) {
        // Add the item to the cart or update its quantity
        if (cart.containsKey(itemName)) {
            cart.put(itemName, cart.get(itemName) + 1);
        } else {
            cart.put(itemName, 1);
        }

        // Calculate the total cost
        int totalCost = calculateTotalCost();
        totalCostTextView.setText("Total Cost: ₹" + totalCost);
    }

    private int calculateTotalCost() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int price = getPriceForItem(itemName);
            total += price * quantity;
        }
        return total;
    }

    private int getPriceForItem(String itemName) {
        // Return the price for the given item
        switch (itemName) {
            case "Chicken Burger":
                return 99;
            case "White Sauce Pasta":
                return 129;
            case "Dal Makhni":
                return 99;
            case "Chicken Momo":
                return 59;
            case "Fried Chicken":
                return 119;
            case "Veg Hakka Noodles":
                return 139;
            case "Veg Momo":
                return 49;
            case "Chicken Wrap":
                return 89;
            case "Mixed Hakka Noodles":
                return 179;
            case "Veg Wrap":
                return 79;
            case "Chicken Pizza":
                return 289;
            case "Veg Burger":
                return 79;
            default:
                return 0;
        }
    }
}