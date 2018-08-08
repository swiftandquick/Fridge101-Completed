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

public class AdapterDrink extends RecyclerView.Adapter<AdapterDrink.ViewHolder> {

    private Context context;

    private ArrayList<String > drinkName,expDate;

    public AdapterDrink(Context context) {
        this.context = context;
        drinkName = new ArrayList<>();
        drinkName.add("Drink");

        expDate = new ArrayList<>();
        expDate.add("YYYY-MM-DD");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drink,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(i==0){
            viewHolder.tvDate.setText("Expiration date");
            viewHolder.tvName.setText("Item");
        }
        else {
            viewHolder.tvDate.setText(expDate.get(i-1));
            viewHolder.tvName.setText(drinkName.get(i-1));
        }
    }

    @Override
    public int getItemCount() {
        return drinkName.size()+1;
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


