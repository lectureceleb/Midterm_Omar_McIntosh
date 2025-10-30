package ca.majesticsolutions.midterm_omar_mcintosh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText userNumber;
    private ArrayList<Integer> calculations = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;
    private ListView listView;
    Button switchButton;


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

        userNumber = findViewById(R.id.userInput);
        switchButton = findViewById(R.id.switchBtn);
        listView = findViewById(R.id.mulitplicationTable);

        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, calculations
        );
        listView.setAdapter(adapter);
    }

    public void calculateData(View view) {
        int[] tableValues = new int[10];
        calculations.clear();

        int userValue = Integer.parseInt(userNumber.getText().toString());

        if (userValue > 0) {
            for (int i = 0; i < 10; i++) {
                tableValues[i] = (i + 1) * userValue;
                calculations.add(tableValues[i]);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void switchActivity(View view) {
        Intent switchIntent = new Intent(this, OtherActivity.class);
        // Add data to transfer

        startActivity((switchIntent));
    }
}