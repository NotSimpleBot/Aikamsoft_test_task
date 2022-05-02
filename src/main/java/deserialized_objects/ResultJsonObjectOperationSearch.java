package deserialized_objects;

import com.guzanov.Operation;

public class ResultJsonObjectOperationSearch<T> implements ResultJsonObjectMarker<T>{
    private Operation type;
    private MyEntityOperationSearch<T>[] results;
    private int count = 0;


    public ResultJsonObjectOperationSearch() {
    }

    public ResultJsonObjectOperationSearch(Operation type, int size) {
        this.type = type;
        results = new MyEntityOperationSearch[size];
    }

    public MyEntityOperationSearch<T>[] getResults() {
        return results;
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
}
