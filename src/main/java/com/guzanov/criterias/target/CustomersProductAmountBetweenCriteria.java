package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

public class CustomersProductAmountBetweenCriteria implements Criterias {
    private int minExpenses, maxExpenses;

    public CustomersProductAmountBetweenCriteria() {
    }

    public CustomersProductAmountBetweenCriteria(int minExpenses, int maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public int getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(int minExpenses) {
        this.minExpenses = minExpenses;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(int maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    @Override
    public String toString() {
        return "CustomersProductAmountBetweenCriteria{" + minExpenses +
                ", " + maxExpenses +
                '}';
    }
}
