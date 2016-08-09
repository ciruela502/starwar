package com.marta.starwar2.services;

import com.marta.starwar2.callbacks.DownloaderCallback;
import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWarsApi;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by marta on 09.07.2016.
 */
public class DownloaderService {

    DownloaderCallback callback;
    int page = 1;

    public void setCallback(DownloaderCallback callback) {
        this.callback = callback;
    }

    public void downloadAllPeople() {
        StarWarsApi.init();
        downloadPage();
    }

    private void downloadPage() {
        StarWarsApi.getApi().getAllPeople(page, new Callback<SWModelList<People>>() {
            @Override
            public void success(SWModelList<People> peopleSWModelList, Response response) {
                callback.onDownloadPage(peopleSWModelList);
                if (peopleSWModelList.hasMore()) {
                    page += 1;
                    downloadPage();
                } else{
                   callback.endDownloadAllPeople();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onFailure();
            }
        });
    }

}
