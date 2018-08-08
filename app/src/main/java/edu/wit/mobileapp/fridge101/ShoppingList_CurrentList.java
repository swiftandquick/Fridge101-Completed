package edu.wit.mobileapp.fridge101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShoppingList_CurrentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list__current_list);

        Button done = (Button) findViewById(R.id.btn_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent done = new Intent(ShoppingList_CurrentList.this, Refridgerator.class);
                startActivity(done);
            }
        });

        Button remove = (Button) findViewById(R.id.btn_delete);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent remove = new Intent(ShoppingList_CurrentList.this, Refridgerator.class);
                startActivity(remove);
            }
        });

        Button edit = (Button) findViewById(R.id.b_edit1);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(ShoppingList_CurrentList.this, ShoppingList_Edit.class);
                startActivity(edit);
            }
        });

        // Clear button resets all radio buttons on the page on click, we need that method.
    }
}
