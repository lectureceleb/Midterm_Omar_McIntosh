package ca.majesticsolutions.midterm_omar_mcintosh;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class OtherActivity extends BaseActivity {

    Button returnButton;

    private ArrayList<Integer> history = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_other);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setupToolbar(toolbar);

        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.clear_list) {

                String message = "There are currently no items to clear";
                if (!history.isEmpty()) {
                    history.clear();
                    adapter.notifyDataSetChanged();
                    message = "You have cleared all items from the list";
                }
                Toast.makeText(OtherActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            return false;
        });

        returnButton = findViewById(R.id.returnBtn);
        listView = findViewById(R.id.userHistory);

        // Get ArrayList from other View
        if (!Objects.requireNonNull(getIntent().getIntegerArrayListExtra("history")).isEmpty()) {
            history = getIntent().getIntegerArrayListExtra("history");

            // Reverse order so latest search shows first
            if (!history.isEmpty()) Collections.reverse(history);
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