package com.marta.starwar2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.marta.starwar2.R;
import com.marta.starwar2.callbacks.DownloaderCallback;
import com.marta.starwar2.models.StarwarsAllPeople;
import com.marta.starwar2.services.DownloaderService;
import com.swapi.models.People;
import com.swapi.models.SWModelList;

public class LoadingScreenActivity extends AppCompatActivity {

    TextView point;
    TextView loading;
    FloatingActionButton floatingActionButton;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        point = (TextView) findViewById(R.id.points);
        loading = (TextView) findViewById(R.id.textViewLoading);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityFavorite();
            }
        });

        DownloaderService service = new DownloaderService();
        service.setCallback(new DownloaderCallback() {
            @Override
            public void onDownloadPage(SWModelList<People> peopleSWModelList) {
                StarwarsAllPeople.addPeoples(peopleSWModelList.results);

                updatePoints();
            }

            @Override
            public void endDownloadAllPeople() {
                startNewActivity();
            }

            @Override
            public void onFailure() {
                loading.setText(R.string.offline);
                floatingActionButton.setVisibility(View.VISIBLE);

            }
        });
        service.downloadAllPeople();
    }


    private void updatePoints() {
        if (i < 3) {
            point.setText(point.getText() + ". ");
            i++;
        } else {
            point.setText(". ");
            i = 1;
        }
    }

    void startNewActivity() {

        Intent intent = new Intent(this, MenuOption.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("layout", "all");
        startActivity(intent);
        finish();
    }

    private void startActivityFavorite() {

        Intent intent = new Intent(this, MenuOption.class);
        intent.putExtra("layout", "favorite");
        startActivity(intent);
    }

}


