package edu.wit.mobileapp.fridge101.grid_view;

import android.widget.Button;

public class GridItemList {
    public String name;
    public String exp;
    public Button edit;
    public boolean checked = false;

    public GridItemList(){

    }

    public GridItemList(String finalize, String exp, Button edit){
        this.name = finalize;
        this.exp = exp;
        this.edit = edit;
    }

    public GridItemList(String finalize, String exp, boolean checked, Button edit){
        this.name = finalize;
        this.checked = checked;
        this.exp  = exp;
        this.edit = edit;

    }

    public String getName(){
        return name;
    }

    public String getExp(){
        return exp;
    }

    public Button getEdit() {
        return edit;
    }

    public void setName(String finalize){
        this.name = finalize;
    }

    public void setExp(String exp){
        this.exp = exp;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public String toString(){
        return name;
    }

    public String toStringExp(){
        return exp;
    }
    public void toggleChecked(){
        checked = !checked;
    }

}
