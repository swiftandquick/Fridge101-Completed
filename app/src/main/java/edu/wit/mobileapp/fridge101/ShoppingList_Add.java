package edu.wit.mobileapp.fridge101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShoppingList_Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list__add);

        Button go_back = (Button) findViewById(R.id.b_return);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingList_Add.this, Refridgerator.class);
                startActivity(intent);
            }
        });

        Button confirm = (Button) findViewById(R.id.b_add);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingList_Add.this, Refridgerator.class);
                startActivity(intent);
            }
        });

        // Clear button resets all radio buttons on the page on click, we need that method.

    }
}
