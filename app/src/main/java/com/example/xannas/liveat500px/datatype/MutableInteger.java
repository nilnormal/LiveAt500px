package com.example.xannas.liveat500px.datatype;

import android.os.Bundle;

/**
 * Created by xannas on 1/21/2017 AD.
 */

public class MutableInteger {
    private int value;

    public MutableInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public Bundle onSaveInstanceState (){
        Bundle bundle = new Bundle();
        bundle.putInt("value",value);
        return bundle;
    }
    public void onRestoreInstanceState (Bundle saveInstanceState){
        value =  saveInstanceState.getInt("value");
    }
}
