package com.guzanov.deserialized_objects;

import com.guzanov.criterias.Criterias;

import java.util.ArrayList;
import java.util.List;

public class MyEntityOperationSearch<T> {
    private Criterias criteria;
    private List<T> results;

    public MyEntityOperationSearch() {
        results = new ArrayList<>();
    }

    public MyEntityOperationSearch(Criterias criteria) {
        this.criteria = criteria;
        results = new ArrayList<>();
    }


    public Criterias getCriteria() {
        return criteria;
    }

    public void setCriteria(Criterias criteria) {
        this.criteria = criteria;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void addAll(List<T> results) {
        this.results.addAll(results);
    }

}
