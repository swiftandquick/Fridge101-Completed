package edu.wit.mobileapp.fridge101.grid_view;

import android.widget.CheckBox;
import android.widget.TextView;

public class ItemViewHolder {
    private CheckBox cb;
    private TextView tv;
    private TextView exp;

    public ItemViewHolder(){

    }

    public ItemViewHolder(CheckBox cb, TextView tv, TextView exp){
        this.cb = cb;
        this.tv = tv;
        this.exp = exp;
    }

    public CheckBox getCb() {
        return cb;
    }

    public TextView getTv() {
        return tv;
    }


    public TextView getExp(){
        return exp;
    }
    public void setTextView(TextView tv, TextView exp){
        this.tv = tv;
        this.exp = exp;
    }

}
