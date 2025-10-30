package ca.majesticsolutions.midterm_omar_mcintosh;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OtherActivity extends AppCompatActivity {

    Button returnButton;

    private ArrayList<Integer> history = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_other);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        returnButton = findViewById(R.id.returnBtn);
        listView = findViewById(R.id.userHistory);

        // Get ArrayList from other View
        if (getIntent().getIntegerArrayListExtra("history") != null) {
            history = getIntent().getIntegerArrayListExtra("history");
        } else {
            history = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, history
        );
        listView.setAdapter(adapter);

    }

    public void returnToMain (View view) {
        finish();
    }
}