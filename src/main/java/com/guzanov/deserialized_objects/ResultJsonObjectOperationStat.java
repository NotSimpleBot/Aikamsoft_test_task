package com.guzanov.deserialized_objects;

import com.guzanov.Operation;

import java.util.List;
import java.util.Objects;

public class ResultJsonObjectOperationStat<T> implements ResultJsonObjectMarker<T> {
    private Operation type;
    private int totalDays;
    private List<MyEntryOperationStat<T>> customers;
    private int totalExpenses = 0;
    private double avgExpenses = 0;

    public ResultJsonObjectOperationStat() {
    }

    public ResultJsonObjectOperationStat(Operation type, List<MyEntryOperationStat<T>> customers) {
        this.type = type;
        this.customers = customers;
    }

    public ResultJsonObjectOperationStat(Operation type, int totalDays) {
        this.type = type;
        this.totalDays = totalDays;
    }

    public ResultJsonObjectOperationStat(Operation type, int totalDays, List<MyEntryOperationStat<T>> customers) {
        this.type = type;
        this.totalDays = totalDays;
        this.customers = customers;
    }

    public String getType() {
        return type.getStat();
    }

    public void setType(Operation type) {
        this.type = type;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public List<MyEntryOperationStat<T>> getCustomers() {
        return customers;
    }

    public void setCustomers(List<MyEntryOperationStat<T>> customers) {
        this.customers = customers;
    }

    public int getTotalExpenses() {
        totalExpenses = 0;
        for (MyEntryOperationStat<T> m : customers) {
            totalExpenses += m.getTotalExpenses();
        }
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getAvgExpenses() {
        avgExpenses = getTotalExpenses()/customers.size();
        return avgExpenses;
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultJsonObjectOperationStat<?> that = (ResultJsonObjectOperationStat<?>) o;
        return totalDays == that.totalDays && type == that.type && Objects.equals(customers, that.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, totalDays, customers);
    }
}
