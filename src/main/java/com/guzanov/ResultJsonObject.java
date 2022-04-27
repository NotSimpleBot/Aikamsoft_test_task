package com.guzanov;

public class ResultJsonObject<T> {
    private Operation type;
    private MyEntity<T>[] results;
    private int count = 0;


    public ResultJsonObject() {
    }

    public ResultJsonObject(Operation type, int size) {
        this.type = type;
        results = new MyEntity[size];
    }

    public MyEntity<T>[] getResults() {
        return results;
    }

    public void setResults(MyEntity<T>[] results) {
        this.results = results;
    }

    public String getType() {
        return type.getStat();
    }

    public void setType(Operation type) {
        this.type = type;
    }



    public void addMyEntity(MyEntity<T> entity) {
        if (count < results.length) {
            results[count] = entity;
            count++;
        }
    }
}
