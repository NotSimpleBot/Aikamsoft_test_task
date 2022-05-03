package com.guzanov.deserialized_objects;

import com.guzanov.helpers.Operation;
import com.guzanov.criterias.Criterias;

import java.util.ArrayList;
import java.util.List;

public class ResultJsonObjectOperationSearch<T> implements ResultJsonObjectMarker{
    private Operation type;
    private MyEntityOperationSearch<T>[] results;
    private int count = 0;


    public ResultJsonObjectOperationSearch() {
    }

    public ResultJsonObjectOperationSearch(Operation type, int size) {
        this.type = type;
        results = new MyEntityOperationSearch[size];
    }

    public void setResults(MyEntityOperationSearch<T>[] results) {
        this.results = results;
    }

    public String getType() {
        return type.getStat();
    }

    public void setType(Operation type) {
        this.type = type;
    }

    public void addMyEntity(MyEntityOperationSearch<T> entity) {
        if (count < results.length) {
            results[count] = entity;
            count++;
        }
    }

    public static class MyEntityOperationSearch<T> {
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

        public MyEntityOperationSearch<T>[] getResults() {
        return results;
    }
}
