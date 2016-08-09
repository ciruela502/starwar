package com.marta.starwar2.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.marta.starwar2.R;
import com.marta.starwar2.models.StarwarsAllPeople;
import com.swapi.models.People;


/**
 * Created by marta on 06.07.2016.
 */
public class DetailsActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    private People people;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        textView = (TextView) findViewById(R.id.textView);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);


        String json = "";
        json = getIntent().getStringExtra("person");

        Gson gson = new Gson();
        people = gson.fromJson(json, People.class);
        String transitionName = getIntent().getStringExtra("transitionName");

        textView.setTransitionName(transitionName);
        textView.setText(people.name);

        if (people.isFavorite) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_favorite_border_white_24dp, 0);
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_delete_black_24dp));
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!people.isFavorite) {
                    StarwarsAllPeople.addToFavorite(people);
                    StarwarsAllPeople.replaceElemFromListAll(people);
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_delete_black_24dp));
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_favorite_border_white_24dp, 0);

                } else {
                    StarwarsAllPeople.deleteFromFavorite(people);
                    StarwarsAllPeople.replaceElemFromListAll(people);
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border_black_24dp));
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }

            }
        });

        TextView tvBirth = (TextView)findViewById(R.id.textViewBirth);
        tvBirth.setText(tvBirth.getText()+"  " + people.birthYear);

        TextView tvGender = (TextView)findViewById(R.id.textViewGender);
        tvGender.setText(tvGender.getText()+"  " + people.gender);

        TextView tvHair = (TextView)findViewById(R.id.textViewHair);
        tvHair.setText(tvHair.getText()+"  " + people.hairColor);

        TextView tvHeight = (TextView)findViewById(R.id.textViewHeight);
        tvHeight.setText(tvHeight.getText()+"  " + people.height);

        TextView tvHome = (TextView)findViewById(R.id.textViewHome);
        tvHome.setText(tvHome.getText()+"  " + people.homeWorldUrl);

        TextView tvMass = (TextView)findViewById(R.id.textViewMass);
        tvMass.setText(tvMass.getText()+"  " + people.mass);

        TextView tvSkin = (TextView)findViewById(R.id.textViewSkin);
        tvSkin.setText(tvSkin.getText()+"  " + people.skinColor);

        TextView tvId = (TextView)findViewById(R.id.textViewId);
        tvId.setText(tvId.getText()+"  " + String.valueOf(people.id));

    }
}

