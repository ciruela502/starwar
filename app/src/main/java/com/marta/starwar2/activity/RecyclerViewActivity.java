package com.marta.starwar2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.marta.starwar2.R;
import com.marta.starwar2.adapters.RVAdapter;
import com.marta.starwar2.callbacks.MyListener;
import com.marta.starwar2.models.StarwarsAllPeople;
import com.swapi.models.People;

import java.util.List;

public class RecyclerViewActivity extends Activity {

    private static final String TAG = "RecyclerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        Intent intent = getIntent();
        String optionSet = intent.getStringExtra("persons");

        List<People> persons;
        if(optionSet.contains("all")){
            persons = StarwarsAllPeople.getPersons();
        }else if (optionSet.contains("favorite")) {
            persons = StarwarsAllPeople.getFavoriteSet();
        }
        else{
            persons = StarwarsAllPeople.getSerching();
        }

        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);

        adapter.setMyListener(new MyListener() {
            @Override
            public void onItemSelect(People people, View view) {
                transition(people, view);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        StarwarsAllPeople.cleanSearching();
    }

    public void transition(People people, View view) {

        Intent intent = new Intent(view.getContext(), DetailsActivity.class);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, view.getTransitionName());

        Gson gson = new Gson();
        String json = gson.toJson(people);

        intent.putExtra("person", json);
        intent.putExtra("transitionName", view.getTransitionName());

        startActivity(intent, options.toBundle());
    }


}