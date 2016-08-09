package com.marta.starwar2.services;

import com.marta.starwar2.models.StarwarsAllPeople;
import com.swapi.models.People;

import java.util.List;

/**
 * Created by marta on 10.07.2016.
 */
public class PeopleSearcherService {

    private int id = -1;
    private String name="   ";

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public void searching() {
        List<People> allPeople = StarwarsAllPeople.getPersons();
        for (People people :allPeople){
            if(people.id == id ) {
                StarwarsAllPeople.addSearch(people);
            }
            else if(people.name.contains(name)) {
                StarwarsAllPeople.addSearch(people);
            }
        }
    }
}
