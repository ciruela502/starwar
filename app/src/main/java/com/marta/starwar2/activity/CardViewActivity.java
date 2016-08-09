package com.marta.starwar2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.marta.starwar2.R;

public class CardViewActivity extends Activity {

    TextView personName;
    TextView personAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_view);
        personName = (TextView)findViewById(R.id.person_name);
        personAge = (TextView)findViewById(R.id.person_age);
    }
}
