package com.marta.starwar2.services;

import com.google.gson.Gson;
import com.swapi.models.People;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by marta on 10.07.2016.
 */
public class MemoryService {


    private static final String TAG = "MemoryService";

    public static Set<String> listToSet(List<People> peoples) {

        Set<String> favoriteSet = new HashSet<String>();

        for (People people : peoples) {

            Gson gson = new Gson();
            String json = gson.toJson(people);
            favoriteSet.add(json);
        }
        return favoriteSet;
    }

    public static List<People> setToList(Set<String> favoriteSet) {

        List<People> peoplesFavorite = new ArrayList<People>();

        for (String peopleString : favoriteSet) {

            Gson gson = new Gson();
            People people = gson.fromJson(peopleString, People.class);
            peoplesFavorite.add(people);
        }
        return peoplesFavorite;
    }
}
