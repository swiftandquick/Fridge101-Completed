package edu.wit.mobileapp.fridge101;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

// Keeping this page, not sure if it's needed.

public class Tab3ShoppingList extends Fragment {

    public Tab3ShoppingList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3shoppinglist, container, false);

        Button slList = (Button) rootView.findViewById(R.id.b_add);
        slList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingList_Add.class);
                startActivity(intent);
            }
        });

        Button slView = (Button) rootView.findViewById(R.id.b_edit);
        slView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingList_CurrentList.class);
                startActivity(intent);
            }
        });

        // Clear button resets all radio buttons on the page.

        return rootView;

    }

}