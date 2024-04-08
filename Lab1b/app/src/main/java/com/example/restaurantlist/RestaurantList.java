package com.example.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {
    private EditText restaurantName;
    private RadioGroup restaurantTypes;
    private Button buttonSave;
    private EditText restaurantAddress;
    private EditText restaurantTel;

    private List<Restaurant> model = new ArrayList<Restaurant>();
    private ArrayAdapter<Restaurant> adapter = null;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        restaurantName = findViewById(R.id.restaurant_name);
        restaurantTypes = findViewById(R.id.restaurant_types);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(onSave);

        restaurantAddress = findViewById(R.id.restaurant_address);
        restaurantTel = findViewById(R.id.restaurant_tel);

        list = findViewById(R.id.restaurants);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, model);
        list.setAdapter(adapter);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // To read data from restaurantName EditText
            String nameStr = restaurantName.getText().toString();
            // To read data from restaurantAddress EditText
            String addressStr = restaurantAddress.getText().toString();
            // To read data from restaurantTel EditText
            String telStr = restaurantTel.getText().toString();
            String restType = "";
            // To read selection of restaurantTypes RadioGroup
            int radioID = restaurantTypes.getCheckedRadioButtonId();
            if (radioID == R.id.chinese) {
                restType = "Chinese";
            } else
            if (radioID == R.id.western) {
                restType = "Western";
            } else
            if (radioID == R.id.indian) {
                restType = "Indian";
            } else
            if (radioID == R.id.indonesian) {
                restType = "Indonesian";
            } else
            if (radioID == R.id.korean) {
                restType = "Korean";
            } else
            if (radioID == R.id.japanese) {
                restType = "Japanese";
            } else
            if (radioID == R.id.thai) {
                restType = "Thai";
            }
            String combineStr = nameStr + "\n" + addressStr + "\n" + telStr + "\n" + restType;
            Toast.makeText(getApplicationContext(), combineStr, Toast.LENGTH_LONG).show();
            // Create Restaurant Data Model
            Restaurant restaurant = new Restaurant();
            // Update the Restaurant Data Model
            restaurant.setName(nameStr);
            restaurant.setAddress(addressStr);
            restaurant.setTelephone(telStr);
            restaurant.setRestaurantType(restType);
            // Pass the record to the ArrayAdapter
            // It will update the ListArray and the ListView
            adapter.add(restaurant);
        }
    };
}