package edu.wit.mobileapp.fridge101;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// import edu.wit.mobileapp.fridge101.R;

public class AdapterAllItems extends RecyclerView.Adapter<AdapterAllItems.ViewHolder> {

    private Context context;

    private ArrayList<String > allItemsName,expDate;

    public AdapterAllItems(Context context) {
        this.context = context;
        allItemsName = new ArrayList<>();
        allItemsName.add("All Items");

        expDate = new ArrayList<>();
        expDate.add("YYYY-MM-DD");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_items,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(i==0){
            // Add image here.
            viewHolder.tvDate.setText("Expiration date");
            viewHolder.tvName.setText("Item");
        }
        else {
            // Add image here.
            viewHolder.tvDate.setText(expDate.get(i-1));
            viewHolder.tvName.setText(allItemsName.get(i-1));
        }
    }

    @Override
    public int getItemCount() {
        return allItemsName.size()+1;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

}