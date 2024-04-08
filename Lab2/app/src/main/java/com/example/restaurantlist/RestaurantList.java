package com.example.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {
    private EditText restaurantName;
    private RadioGroup restaurantTypes;
    private Button buttonSave;
    private EditText restaurantAddress;
    private EditText restaurantTel;

    private List<Restaurant> model = new ArrayList<Restaurant>();
    private RestaurantAdapter adapter = null;
    private ListView list;
    private TabHost host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize UI elements
        restaurantName = findViewById(R.id.restaurant_name);
        restaurantTypes = findViewById(R.id.restaurant_types);
        buttonSave = findViewById(R.id.button_save);
        restaurantAddress = findViewById(R.id.restaurant_address);
        restaurantTel = findViewById(R.id.restaurant_tel);
        list = findViewById(R.id.restaurants);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);

        host = findViewById(R.id.tabHost);
        host.setup();

        // tab 1
        TabHost.TabSpec spec = host.newTabSpec("List");
        spec.setContent(R.id.restaurants_tab);
        spec.setIndicator("List");
        host.addTab(spec);

        // tab 2
        spec = host.newTabSpec("Details");
        spec.setContent(R.id.details_tab);
        spec.setIndicator("Details");
        host.addTab(spec);
        host.setCurrentTab(0);
        list.setOnItemClickListener(onListClick);

        // Set onClickListener for the Save button
        buttonSave.setOnClickListener(onSave);
    }

    // OnClickListener for the Save button
    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Read data from UI elements
            String nameStr = restaurantName.getText().toString();
            String addressStr = restaurantAddress.getText().toString();
            String telStr = restaurantTel.getText().toString();
            String restType = "";
            int radioID = restaurantTypes.getCheckedRadioButtonId();

            // Determine the selected restaurant type
            if (radioID == R.id.chinese) {
                restType = "Chinese";
            } else if (radioID == R.id.western) {
                restType = "Western";
            } else if (radioID == R.id.indian) {
                restType = "Indian";
            } else if (radioID == R.id.indonesian) {
                restType = "Indonesian";
            } else if (radioID == R.id.korean) {
                restType = "Korean";
            } else if (radioID == R.id.japanese) {
                restType = "Japanese";
            } else if (radioID == R.id.thai) {
                restType = "Thai";
            }

            // Display a toast with combined information
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

            // Switch to the List tab
            host.setCurrentTab(0);
        }
    };

    // ViewHolder pattern for efficient ListView item creation
    static class RestaurantHolder {
        private TextView restName = null;
        private TextView addr = null;
        private ImageView icon = null;

        RestaurantHolder(View row) {
            restName = row.findViewById(R.id.restName);
            addr = row.findViewById(R.id.restAddr);
            icon = row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant r) {
            restName.setText(r.getName());
            addr.setText(r.getAddress() + ", " + r.getTelephone());
            if (r.getRestaurantType().equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (r.getRestaurantType().equals("Western")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
                icon.setImageResource(R.drawable.ball_green);
            }
        }
    }

    // Custom ArrayAdapter for the Restaurant class
    class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() { super(RestaurantList.this, R.layout.row, model); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RestaurantHolder holder;

            if (row == null) {
                // If convertView is null, create a new RestaurantHolder
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            } else {
                // If convertView is not null, reuse the existing RestaurantHolder
                holder = (RestaurantHolder) row.getTag();
            }

            // Get the current restaurant
            Restaurant currentRestaurant = model.get(position);
            // Populate the UI elements with restaurant data
            holder.populateFrom(currentRestaurant);

            return row;
        }
    }

    // Item click listener for the ListView
    AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get the selected restaurant
            Restaurant selectedRestaurant = model.get(position);

            // Populate the UI elements with the selected restaurant's data
            restaurantName.setText(selectedRestaurant.getName());
            restaurantAddress.setText(selectedRestaurant.getAddress());
            restaurantTel.setText(selectedRestaurant.getTelephone());

            // Check the appropriate radio button based on restaurant type
            if (selectedRestaurant.getRestaurantType().equals("Chinese")) {
                restaurantTypes.check(R.id.chinese);
            } else if (selectedRestaurant.getRestaurantType().equals("Western")) {
                restaurantTypes.check(R.id.western);
            } else if (selectedRestaurant.getRestaurantType().equals("Indian")) {
                restaurantTypes.check(R.id.indian);
            } else if (selectedRestaurant.getRestaurantType().equals("Indonesian")) {
                restaurantTypes.check(R.id.indonesian);
            } else if (selectedRestaurant.getRestaurantType().equals("Korean")) {
                restaurantTypes.check(R.id.korean);
            } else if (selectedRestaurant.getRestaurantType().equals("Japanese")) {
                restaurantTypes.check(R.id.japanese);
            } else {
                restaurantTypes.check(R.id.thai);
            }

            // Switch to the Details tab
            host.setCurrentTab(1);
        }
    };
}
