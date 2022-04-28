package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

import java.util.Date;

public class CustomersBetweenTwoDatesCriteria implements Criterias {
    private Date startDate;
    private Date endDate;

    public CustomersBetweenTwoDatesCriteria() {
    }

    public CustomersBetweenTwoDatesCriteria(Date startDate, Date enfDate) {
        this.startDate = startDate;
        this.endDate = enfDate;
    }

    public long daysBetween(){
        return  (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CustomersBetweenTwoDatesCriteria{" +
                "startDate=" + startDate +
                ", enfDate=" + endDate +
                '}';
    }
}
