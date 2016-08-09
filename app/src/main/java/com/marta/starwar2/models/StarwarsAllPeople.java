package com.marta.starwar2.models;

import com.swapi.models.People;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marta on 09.07.2016.
 */
public class StarwarsAllPeople {

    private static List<People> personsSet;
    private static List<People> favoriteSet;
    private static List<People> searchingSet;

    private static int idNum = 1;

    public static List<People> getPersons() {
        initializePerson();
        return personsSet;
    }

    public static List<People> getFavoriteSet() {
        initializeFavorite();
        return favoriteSet;
    }

    public static List<People> getSerching() {
        initializeSearching();
        return searchingSet;
    }

    public static void addToFavorite(People people) {
        initializeFavorite();
        if (!contain(people)) {
            people.isFavorite = true;
            favoriteSet.add(people);
        }
    }

    private static boolean contain(People people) {
        for (People fav : favoriteSet) {
            if (fav.name.contains(people.name))
                return true;
        }
        return false;
    }

    public static void addListToFavorite(List<People> peoples) {
        initializeFavorite();
        for (People people : peoples) {
            addToFavorite(people);
        }
    }

    public static void addPeoples(List<People> set) {
        initializePerson();
        for (People people : set) {
            addPeople(people);
            idNum++;
        }
    }

    public static void addPeople(People people) {
        initializeFavorite();
        initializePerson();
        if (contain(people)) {
            people.isFavorite = true;
        }
        people.id = idNum;
        personsSet.add(people);
    }

    public static void addSearch(People people) {
        initializeSearching();
        searchingSet.add(people);
    }


    private static void initializePerson() {
        if (personsSet == null) {
            personsSet = new ArrayList<>();
        }
    }

    private static void initializeFavorite() {
        if (favoriteSet == null) {
            favoriteSet = new ArrayList<>();
        }
    }

    private static void initializeSearching() {
        if (searchingSet == null) {
            searchingSet = new ArrayList<>();
        }
    }

    public static void deleteFromFavorite(People people) {
        people.isFavorite = false;
        int position = -1;
        for (int i = 0; i < favoriteSet.size(); i++) {
            if (favoriteSet.get(i).id == people.id)
                position = i;
        }
        favoriteSet.remove(position);
    }

    public static void cleanSearching() {
        if (searchingSet != null) {
            searchingSet.clear();
        }
    }

    public static void replaceElemFromListAll(People people) {
        initializePerson();
        int position = -1;
        for (int i = 0; i < personsSet.size(); i++) {
            if (personsSet.get(i).id == people.id) {
                position = i;
                personsSet.set(position, people);
            }
        }
    }
}