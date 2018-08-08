package edu.wit.mobileapp.fridge101.add_items;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.wit.mobileapp.fridge101.R;
import edu.wit.mobileapp.fridge101.Refridgerator;

public class ReviewItem extends AppCompatActivity {
    String selectedItem, selectedLocation;
    String name, category, location, notes;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        String[] arraySpinner = new String[] {
                "All Items", "Vegetable", "Fruit", "Grain", "Meat",
                "Drink", "Other Food", "Non-Food" };
        Spinner s = (Spinner) findViewById(R.id.s_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                Log.v("Fridge101", selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] arraySpinner2 = new String[] {
                "Undecided", "Freezer", "Bottom" };
        Spinner s2 = (Spinner) findViewById(R.id.s_where);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s2.setAdapter(adapter2);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = parent.getItemAtPosition(position).toString();
                Log.v("Fridge101", selectedLocation);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Bundle bundle = this.getIntent().getExtras();

        String full_date = bundle.getString("year") + "/" + bundle.getString("month") + "/" + bundle.getString("date");

        name = bundle.getString("name");
        int noti_days = bundle.getInt("notification");
        int quantity = bundle.getInt("quantity");
        category = bundle.getString("category");
        location = bundle.getString("where");
        notes = bundle.getString("note");

        String path = "/data/data/" + getPackageName() + "/fridge.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        String sql = "CREATE TABLE IF NOT EXISTS fridge_data" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT, quantity INTEGER , date TEXT, notification_day INTEGER, note TEXT, category TEXT, location TEXT);";

        db.execSQL(sql);

        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("quantity", quantity);
        content.put("date", full_date);
        content.put("notification_day", noti_days );
        content.put("note", notes);
        content.put("category", category);
        content.put("location", location);
        db.insert("fridge_data", "null", content);

        Log.v("Fridge", "Name = " + name + " Quantity = " + quantity + " Date = " + full_date + " Expiration Date = " + noti_days + " Category = " + category + " Location = " + location);
        db.close();

        //Back to Main Menu//
        Button go_back = (Button) findViewById(R.id.b_return);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_back = new Intent(ReviewItem.this, Refridgerator.class);
                startActivity(go_back);
            }
        });

        //Add button Main Menu//

        Button add_item = (Button) findViewById(R.id.b_add);
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_item = new Intent(ReviewItem.this, Refridgerator.class);
                startActivity(add_item);
            }
        });

    }
}
