package edu.wit.mobileapp.fridge101;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.wit.mobileapp.fridge101.grid_view.GridItemList;
import edu.wit.mobileapp.fridge101.grid_view.GridViewAdapter;
import edu.wit.mobileapp.fridge101.grid_view.ItemViewHolder;

public class ItemCurrentList extends AppCompatActivity {

    private ListView mainList = null;
    private GridItemList[] list = null;
    private ArrayAdapter<GridItemList> listAdapter = null;
    String value;
    Context mContext;
    Button but;

    public BroadcastReceiver getValue = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            value = intent.getStringExtra("value");
            Log.v("Fridge101", "WAIT WAT " + value);
            but = findViewById(R.id.btn_delete);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), EditItem.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("editValue", value);
                    getApplicationContext().startActivity(i);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_current_list);

        LocalBroadcastManager.getInstance(this).registerReceiver(getValue,
                new IntentFilter("valueKey"));


        Log.v("Fridge101", "I GOT IT " + value);





        mainList=(ListView)findViewById(R.id.list_view);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridItemList list = listAdapter.getItem(position);
                list.toggleChecked();
                ItemViewHolder vh = (ItemViewHolder)view.getTag();

                vh.getCb().setChecked(list.isChecked());


            }
        });

        //Instantiate ArrayList for each category//
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dateAll = new ArrayList<>();

        ArrayList<GridItemList> list = new ArrayList<GridItemList>();

        String[] column = {"name","date"};

        //Query for all//
        String path = "/data/data/" + getPackageName() + "/fridge.db";

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
        Cursor cursor = db.query("fridge_data", column, null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Log.v("Fridge101", "date = " + dateAll);
            Log.v("Fridge101", "items = " + names);
            String result = name;
            Log.v("Fridge101", "LOL" + result);

            GridItemList item1 = new GridItemList();
            item1.name = result;

            list.add(item1);

        }

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        listAdapter = new GridViewAdapter(this, 0,list);
        mainList.setAdapter(listAdapter);





        Button done = (Button) findViewById(R.id.btn_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent done = new Intent(ItemCurrentList.this, Refridgerator.class);
                startActivity(done);
            }
        });


    }

}
