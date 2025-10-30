package ca.majesticsolutions.midterm_omar_mcintosh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity {

    protected ArrayList<Integer> inheritedList = new ArrayList<>();

    protected void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.common_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear_list) {
            // Settings action
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Clear All Items")
                .setMessage("Are you sure you want to clear all items?")
                .setPositiveButton("Yes",(d, i) -> {
                    inheritedList.clear();
                    Toast.makeText(this, "All items have been removed", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .create().show();
    }
}
