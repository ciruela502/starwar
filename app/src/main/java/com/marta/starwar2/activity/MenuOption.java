package com.marta.starwar2.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.marta.starwar2.R;
import com.marta.starwar2.models.StarwarsAllPeople;
import com.marta.starwar2.services.MemoryService;
import com.marta.starwar2.services.PeopleSearcherService;
import com.swapi.models.People;

import java.util.List;
import java.util.Set;

public class MenuOption extends Activity {

    private static final String PREFS_NAME = "prefName";
    private static boolean isFirst = false;
    PeopleSearcherService service;
    EditText editId;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_option);
        Intent intent = getIntent();
        String layout = intent.getStringExtra("layout");

        if (layout.contains("all")) {

            findViewById(R.id.tv_favorite).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityFavorite();
                }
            });

            findViewById(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeDialog();

                }
            });
            findViewById(R.id.tv_view_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityViewAll();
                }
            });
        } else {
            findViewById(R.id.tv_favorite).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityFavorite();
                }
            });

            findViewById(R.id.tv_search).setVisibility(View.INVISIBLE);
            findViewById(R.id.tv_view_all).setVisibility(View.INVISIBLE);
        }
        //restore
        if (!isFirst) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            Set<String> favoriteSet = settings.getStringSet("favorite", null);

            if (favoriteSet != null) {
                List<People> favoriteList = MemoryService.setToList(favoriteSet);
                StarwarsAllPeople.addListToFavorite(favoriteList);
            }
            isFirst = true;
        }
    }

    void startActivityViewAll() {

        Intent intent = new Intent(this, RecyclerViewActivity.class);
        intent.putExtra("persons", "all");
        startActivity(intent);
    }

    void startActivityFavorite() {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        intent.putExtra("persons", "favorite");
        startActivity(intent);
    }

    void startActivitySearching() {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        intent.putExtra("persons", "searching");
        startActivity(intent);
    }

    void makeDialog() {
        final Dialog dialog = new Dialog(MenuOption.this);
        dialog.setContentView(R.layout.dialog_search);
        dialog.setTitle("Searching...");

        editId = (EditText) dialog.findViewById(R.id.edit_id);
        editName = (EditText) dialog.findViewById(R.id.edit_name);


        Button buttonConfirm = (Button) dialog.findViewById(R.id.btnConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service = new PeopleSearcherService();
                if (editId.getText().toString().length() > 0) {
                    try {
                        int num = Integer.parseInt(editId.getText().toString());
                        if (num < 1|| num >87) {
                            makeAlert();
                            return;
                        }
                        service.setId(num);
                    } catch (NumberFormatException e) {
                        makeAlert();
                        return;
                    }
                }
                if (editName.getText().toString().length() > 0) {
                    service.setName(editName.getText().toString());
                }
                service.searching();
                startActivitySearching();
            }
        });
        dialog.show();
    }

    public void makeAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MenuOption.this);
        alertDialogBuilder
                .setMessage("Invalid id\nput number from 1 to 87").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onDestroy();
            }
        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences favorite = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = favorite.edit();

        Set<String> favoriteSet = MemoryService.listToSet(StarwarsAllPeople.getFavoriteSet());

        editor.putStringSet("favorite", favoriteSet);
        editor.apply();

    }

}
