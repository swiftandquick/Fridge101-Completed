package edu.wit.mobileapp.fridge101;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1Home extends Fragment {

    Button mEditItemsBottom;

    public Tab1Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1home, container, false);

        String path = "/data/data/" + getActivity().getPackageName() + "/fridge.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS fridge_data" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT, quantity INTEGER , date TEXT, notification_day INTEGER, note TEXT, category TEXT, location TEXT);");



        // Format to direct activity within a fragment.
        Button edit = (Button) rootView.findViewById(R.id.btn_edit_2);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ItemCurrentList.class);
                startActivity(intent);
            }
        });

        Button add = (Button) rootView.findViewById(R.id.btn_add_2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddItem.class);
                startActivity(intent);
            }
        });

        // Place return at the end.
        return rootView;
    }

}