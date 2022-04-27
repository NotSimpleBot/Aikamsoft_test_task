package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

public class CustomersProductCountMoreCriteria implements Criterias {
    private String productName;
    private int minTimes;

    public CustomersProductCountMoreCriteria() {
    }

    public CustomersProductCountMoreCriteria(String productName, int minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(int minTimes) {
        this.minTimes = minTimes;
    }

    @Override
    public String toString() {
        return "CustomersProductCountMoreCriteria{" + productName + '\'' +
                ", " + minTimes +
                '}';
    }
}
