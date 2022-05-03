package com.guzanov.criterias.target;

import com.guzanov.criterias.Criterias;

/**
 * Класс для критерия 4):
 * <p>
 * Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров.
 * <p>
 * Возвращается не более, чем указанное число покупателей.
 */
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
