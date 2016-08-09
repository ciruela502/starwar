package com.marta.starwar2.callbacks;

import com.swapi.models.People;
import com.swapi.models.SWModelList;

public interface DownloaderCallback{

    void onDownloadPage(SWModelList<People> peopleSWModelList);

    void endDownloadAllPeople();

    void onFailure();
}
