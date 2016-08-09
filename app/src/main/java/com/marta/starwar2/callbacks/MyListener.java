package com.marta.starwar2.callbacks;

import android.view.View;

import com.swapi.models.People;

public interface MyListener {
    void onItemSelect(People people, View view);

}
