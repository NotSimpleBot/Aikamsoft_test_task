package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

public class CustomersByLastNameCriteria implements Criterias {
    private String lastName;

    public CustomersByLastNameCriteria() {
    }

    public CustomersByLastNameCriteria(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CustomersByLastNameCriteria{" + lastName + "}";
    }
}
