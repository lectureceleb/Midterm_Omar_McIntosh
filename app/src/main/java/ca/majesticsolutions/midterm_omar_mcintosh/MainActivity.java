package ca.majesticsolutions.midterm_omar_mcintosh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends BaseActivity  {

    EditText userNumber;
    private ArrayList<Integer> calculations = new ArrayList<>();
    private ArrayList<Integer> calculationsHistory = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;
    private ListView listView;
    Button switchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar and click listener
        Toolbar toolbar = findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.clear_list) {
                String message = "There are currently no items to clear";
                if (!calculations.isEmpty()) {
                    calculations.clear();
                    adapter.notifyDataSetChanged();
                    message = "You have cleared all items from the list";
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            return false;
        });

        initializeViews();
    }

    // Set up Views
    private void initializeViews () {
        userNumber = findViewById(R.id.userInput);
        switchButton = findViewById(R.id.switchBtn);

        // Bind data to list view
        listView = findViewById(R.id.mulitplicationTable);

        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, calculations
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Remove Item")
                    .setMessage("Are you sure you want to remove " + parent.getItemAtPosition(position) + " from the list?")
                    .setPositiveButton("Yes", (d, i) -> {
                        // Notify user what number has been removed.
                        Toast.makeText(this, parent.getItemAtPosition(position).toString() + " has been removed", Toast.LENGTH_SHORT).show();

                        // Remove clicked item and update ListView
                        calculations.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .create().show();
        });
    }

    public void calculateData(View view) {
        int[] products = new int[10];
        calculations.clear();   // Clear list to only show latest calculation

        // TODO: Add validation
        int userValue = Integer.parseInt(userNumber.getText().toString());
        calculationsHistory.add(userValue);

        if (userValue > 0) {
            for (int i = 0; i < 10; i++) {
                products[i] = (i + 1) * userValue;
                calculations.add(products[i]);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void switchActivity(View view) {
        Intent switchIntent = new Intent(this, OtherActivity.class);
        switchIntent.putExtra("history", calculationsHistory);
        startActivity((switchIntent));
    }
}