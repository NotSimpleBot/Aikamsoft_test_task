package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

public class BadCustomersLessCriteria implements Criterias {
    private int badCustomers;

    public BadCustomersLessCriteria() {
    }

    public BadCustomersLessCriteria(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public String toString() {
        return "BadCustomersLessCriteria{" + badCustomers +
                '}';
    }
}
