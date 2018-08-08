package edu.wit.mobileapp.fridge101.grid_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import edu.wit.mobileapp.fridge101.R;

public class GridViewAdapter extends ArrayAdapter<GridItemList> {
    private LayoutInflater mInflater;

    public GridViewAdapter(Context context, int rid, List<GridItemList> list) {
        super(context, rid, list);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridItemList itemList = (GridItemList) this.getItem(position);
        CheckBox cb;
        TextView tv;
        TextView exp;
        Button edit;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_current_list_content, null);

            tv = (TextView) convertView.findViewById(R.id.tv_item1);
            exp = (TextView)convertView.findViewById(R.id.tv_expiration_date1);
            cb = (CheckBox) convertView.findViewById(R.id.cb_item1);

            convertView.setTag(new ItemViewHolder(cb, tv, exp));
            cb.setTag(position);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    GridItemList list = (GridItemList) cb.getTag();
                    list.setChecked(cb.isChecked());
                    Log.v("Fridge101","CAN I GET THIS" + list.getName());


                    Bundle bundle = new Bundle();
                    Intent intent = new Intent("valueKey");

                    bundle.putString("value", list.getName());
                    bundle.putString("dates", list.getExp());
                    Log.v("Fridge101","CAN I GET THAT" + list.getExp());
                    intent.putExtras(bundle);
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

                }
            });
        } else {
            ItemViewHolder viewHolder = (ItemViewHolder) convertView.getTag();
            cb = viewHolder.getCb();
            tv = viewHolder.getCb();
            exp = viewHolder.getCb();


        }
        cb.setTag(itemList);

        cb.setChecked(itemList.isChecked());
        tv.setText(itemList.getName());
        Log.v("Fridge101", "TEST IT OUT: " + itemList.getName());


        return convertView;
    }
}


