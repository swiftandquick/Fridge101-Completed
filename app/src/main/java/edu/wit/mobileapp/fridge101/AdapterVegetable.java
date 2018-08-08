package edu.wit.mobileapp.fridge101;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// import edu.wit.mobileapp.fridge101.R;

import java.util.ArrayList;

public class AdapterVegetable extends RecyclerView.Adapter<AdapterVegetable.ViewHolder> {

    private Context context;

    private ArrayList<String > vegetableName,expDate;

    public AdapterVegetable(Context context) {
        this.context = context;
        vegetableName = new ArrayList<>();
        vegetableName.add("Vegetable");

        expDate = new ArrayList<>();
        expDate.add("YYYY-MM-DD");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vegetable,viewGroup,false);
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
            viewHolder.tvName.setText(vegetableName.get(i-1));
        }
    }

    @Override
    public int getItemCount() {
        return vegetableName.size()+1;
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


