package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

import java.util.Date;

public class CustomersBetweenTwoDatesCriteria implements Criterias {
    private Date startDate;
    private Date enfDate;

    public CustomersBetweenTwoDatesCriteria() {
    }

    public CustomersBetweenTwoDatesCriteria(Date startDate, Date enfDate) {
        this.startDate = startDate;
        this.enfDate = enfDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEnfDate() {
        return enfDate;
    }

    public void setEnfDate(Date enfDate) {
        this.enfDate = enfDate;
    }
}
