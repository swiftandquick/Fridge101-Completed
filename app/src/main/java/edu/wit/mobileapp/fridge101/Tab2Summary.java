package edu.wit.mobileapp.fridge101;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.wit.mobileapp.fridge101.summary.RecyclerViewAdapter;

// import edu.wit.mobileapp.fridge101.R;


public class Tab2Summary extends Fragment implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;
    View view;
    Spinner spinner;

    RecyclerView rcvAllItems;
    RecyclerView rcvGrain;
    RecyclerView rcvFruit;
    RecyclerView rcvVegetable;
    RecyclerView rcvMeat;
    RecyclerView rcvDrink;
    RecyclerView rcvOtherFood;
    RecyclerView rcvNonFood;

    AdapterAllItems adapter1;
    AdapterGrain adapter2;
    AdapterFruit adapter3;
    AdapterVegetable adapter4;
    AdapterMeat adapter5;
    AdapterDrink adapter6;
    AdapterOtherFood adapter7;
    AdapterNonFood adapter8;

    public Tab2Summary() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.v("Fridge101","Item clicked");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2summary,container,false);
        spinner = view.findViewById(R.id.spinner);

        rcvAllItems = view.findViewById(R.id.rcvAllItems);
        rcvGrain = view.findViewById(R.id.rcvGrain);
        rcvFruit = view.findViewById(R.id.rcvFruit);
        rcvVegetable = view.findViewById(R.id.rcvVegetable);
        rcvMeat = view.findViewById(R.id.rcvMeat);
        rcvDrink = view.findViewById(R.id.rcvDrink);
        rcvOtherFood = view.findViewById(R.id.rcvOtherFood);
        rcvNonFood = view.findViewById(R.id.rcvNonFood);

        rcvAllItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvGrain.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFruit.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvVegetable.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvMeat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvDrink.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvOtherFood.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvNonFood.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter1 = new AdapterAllItems(getActivity());
        adapter2 = new AdapterGrain(getActivity());
        adapter3 = new AdapterFruit(getActivity());
        adapter4 = new AdapterVegetable(getActivity());
        adapter5 = new AdapterMeat(getActivity());
        adapter6 = new AdapterDrink(getActivity());
        adapter7 = new AdapterOtherFood(getActivity());
        adapter8 = new AdapterNonFood(getActivity());

        rcvAllItems.setAdapter(adapter1);
        rcvGrain.setAdapter(adapter2);
        rcvFruit.setAdapter(adapter3);
        rcvVegetable.setAdapter(adapter4);
        rcvMeat.setAdapter(adapter5);
        rcvDrink.setAdapter(adapter6);
        rcvOtherFood.setAdapter(adapter7);
        rcvNonFood.setAdapter(adapter8);


        String path = "/data/data/" + getActivity().getPackageName() + "/fridge.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        try {
            db = SQLiteDatabase.openOrCreateDatabase(path, null);

        } catch(SQLException e){
            if(e.getMessage().contains("No such table")){
                Log.e("Fridge101", "Creating table because it doesn't exist");
                db.execSQL("CREATE TABLE IF NOT EXISTS fridge_data" +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT, quantity INTEGER , date TEXT, notification_day INTEGER, note TEXT, category TEXT, location TEXT);");
            }

        }

        //Instantiate ArrayList for each category//
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dateAll = new ArrayList<>();

        ArrayList<String> grain = new ArrayList<>();
        ArrayList<String> grainDate = new ArrayList<>();

        ArrayList<String> fruit = new ArrayList<>();
        ArrayList<String> fruitDate = new ArrayList<>();

        ArrayList<String> vegetable = new ArrayList<>();
        ArrayList<String> vegetableDate = new ArrayList<>();

        ArrayList<String> meat = new ArrayList<>();
        ArrayList<String> meatDate = new ArrayList<>();

        ArrayList<String> drink = new ArrayList<>();
        ArrayList<String> drinkDate = new ArrayList<>();

        ArrayList<String> others = new ArrayList<>();
        ArrayList<String> othersDate = new ArrayList<>();

        ArrayList<String> nonFood = new ArrayList<>();
        ArrayList<String> nonFoodDate = new ArrayList<>();

        String[] column = {"name","date"};

        //Query for all//
        Cursor cursor = db.query("fridge_data", column, null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            names.add(name);
            dateAll.add(date);

        }

        if (cursor != null) {
            cursor.moveToFirst();
        }

        //Query for Grain//
        String[] grainName = {"Grain"};
        Cursor cursorGrain = db.query("fridge_data", column, "category ==?", grainName, null, null, null);
        while(cursorGrain.moveToNext()){
            String name = cursorGrain.getString(cursorGrain.getColumnIndex("name"));
            String date = cursorGrain.getString(cursorGrain.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            grain.add(name);
            grainDate.add(date);

        }
        if (cursorGrain != null) {
            cursorGrain.moveToFirst();
        }

        //Query for Fruit//
        String[] fruitName = {"Fruit"};
        Cursor cursorFruit = db.query("fridge_data", column, "category ==?", fruitName, null, null, null);
        while(cursorFruit.moveToNext()){
            String name = cursorFruit.getString(cursorFruit.getColumnIndex("name"));
            String date = cursorFruit.getString(cursorFruit.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            fruit.add(name);
            fruitDate.add(date);
        }
        if (cursorFruit != null) {
            cursorFruit.moveToFirst();
        }

        //Query for Vegetable//
        String[] vegetableName = {"Vegetable"};
        Cursor cursorVegetable = db.query("fridge_data", column, "category ==?", vegetableName, null, null, null);
        while(cursorVegetable.moveToNext()){
            String name = cursorVegetable.getString(cursorVegetable.getColumnIndex("name"));
            String date = cursorVegetable.getString(cursorVegetable.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            vegetable.add(name);
            vegetableDate.add(date);

        }

        if (cursorVegetable != null) {
            cursorVegetable.moveToFirst();
        }

        //Query for Meat//
        String[] meatName = {"Meat"};
        Cursor cursorMeat = db.query("fridge_data", column, "category ==?", meatName, null, null, null);
        while(cursorMeat.moveToNext()){
            String name = cursorMeat.getString(cursorMeat.getColumnIndex("name"));
            String date = cursorMeat.getString(cursorMeat.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            meat.add(name);
            meatDate.add(date);

        }
        if (cursorMeat != null) {
            cursorMeat.moveToFirst();
        }

        //Query for Drink//
        String[] drinkName = {"Drink"};
        Cursor cursorDrink = db.query("fridge_data", column, "category ==?", drinkName, null, null, null);
        while(cursorDrink.moveToNext()){
            String name = cursorDrink.getString(cursorDrink.getColumnIndex("name"));
            String date = cursorDrink.getString(cursorDrink.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            drink.add(name);
            drinkDate.add(date);

        }
        if (cursorDrink != null) {
            cursorDrink.moveToFirst();
        }

        //Query for Other Food//
        String[] othersName = {"Other Food"};
        Cursor cursorOthers = db.query("fridge_data", column, "category ==?", othersName, null, null, null);
        while(cursorOthers.moveToNext()){
            String name = cursorOthers.getString(cursorOthers.getColumnIndex("name"));
            String date = cursorOthers.getString(cursorOthers.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            others.add(name);
            othersDate.add(date);

        }
        if (cursorOthers != null) {
            cursorOthers.moveToFirst();
        }

        //Query for Non-Food//
        String[] nonName = {"Non-Food"};
        Cursor cursorNon = db.query("fridge_data", column, "category ==?", nonName, null, null, null);
        while(cursorNon.moveToNext()){
            String name = cursorNon.getString(cursorNon.getColumnIndex("name"));
            String date = cursorNon.getString(cursorNon.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            nonFood.add(name);
            nonFoodDate.add(date);

        }
        if (cursorNon != null) {
            cursorNon.moveToFirst();
        }

        Log.v("myApp", "end the printing");


        //Close the database
        db.close();

        RecyclerView recyclerView = view.findViewById(R.id.rcvAllItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), names, dateAll);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView1 = view.findViewById(R.id.rcvGrain);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), grain, grainDate);
        adapter.setClickListener(this);
        recyclerView1.setAdapter(adapter);

        RecyclerView recyclerView2 = view.findViewById(R.id.rcvMeat);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), meat, meatDate);
        adapter.setClickListener(this);
        recyclerView2.setAdapter(adapter);

        RecyclerView recyclerView3 = view.findViewById(R.id.rcvDrink);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), drink, drinkDate);
        adapter.setClickListener(this);
        recyclerView3.setAdapter(adapter);

        RecyclerView recyclerView4 = view.findViewById(R.id.rcvVegetable);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), vegetable, vegetableDate);
        adapter.setClickListener(this);
        recyclerView4.setAdapter(adapter);

        RecyclerView recyclerView5 = view.findViewById(R.id.rcvFruit);
        recyclerView5.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), fruit, fruitDate);
        adapter.setClickListener(this);
        recyclerView5.setAdapter(adapter);

        RecyclerView recyclerView6 = view.findViewById(R.id.rcvOtherFood);
        recyclerView6.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), others, othersDate);
        adapter.setClickListener(this);
        recyclerView6.setAdapter(adapter);

        RecyclerView recyclerView7 = view.findViewById(R.id.rcvNonFood);
        recyclerView7.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(this.getActivity(), nonFood, nonFoodDate);
        adapter.setClickListener(this);
        recyclerView7.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("All Items")){
                    rcvGrain.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvAllItems.setVisibility(View.VISIBLE);


                }
                else if(selectedItem.equals("Grain")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.VISIBLE);
                }
                else if(selectedItem.equals("Fruit")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.VISIBLE);
                }
                else if(selectedItem.equals("Vegetable")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.VISIBLE);
                }
                else if (selectedItem.equals("Meat")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.VISIBLE);
                }
                else if (selectedItem.equals("Drink")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.VISIBLE);
                }
                else if (selectedItem.equals("Other Food")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.VISIBLE);
                }
                else if (selectedItem.equals("Non Food")){
                    rcvAllItems.setVisibility(View.GONE);
                    rcvGrain.setVisibility(View.GONE);
                    rcvFruit.setVisibility(View.GONE);
                    rcvVegetable.setVisibility(View.GONE);
                    rcvMeat.setVisibility(View.GONE);
                    rcvDrink.setVisibility(View.GONE);
                    rcvOtherFood.setVisibility(View.GONE);
                    rcvNonFood.setVisibility(View.VISIBLE);
                }
                /**
                 else {
                 rcvAllItems.setVisibility(View.GONE);
                 rcvGrain.setVisibility(View.GONE);
                 rcvFruit.setVisibility(View.GONE);
                 rcvVegetable.setVisibility(View.GONE);
                 rcvMeat.setVisibility(View.GONE);
                 rcvDrink.setVisibility(View.GONE);
                 rcvOtherFood.setVisibility(View.GONE);
                 rcvNonFood.setVisibility(View.GONE);
                 }
                 */
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}