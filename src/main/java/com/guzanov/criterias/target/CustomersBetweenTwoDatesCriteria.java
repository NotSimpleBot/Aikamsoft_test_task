package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

import java.util.Date;

/**
 * Класс для сбора статистики (Операция STAT) за указанный период
 */
public class CustomersBetweenTwoDatesCriteria implements Criterias {
    private Date startDate;
    private Date endDate;

    public CustomersBetweenTwoDatesCriteria() {
    }

    public CustomersBetweenTwoDatesCriteria(Date startDate, Date enfDate) {
        this.startDate = startDate;
        this.endDate = enfDate;
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
