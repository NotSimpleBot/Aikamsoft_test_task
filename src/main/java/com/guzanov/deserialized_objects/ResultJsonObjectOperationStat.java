package com.guzanov.deserialized_objects;

import com.guzanov.helpers.Operation;

import java.util.List;
import java.util.Objects;

/**
 * Экземпляр данного класса записывается в output json файл,
 * содержит вложенный статический класс с набором данных, актуальным для определенного промежутка времени.
 * <p>
 * Класс актуален для операции поиска - Operation.STAT.
 */
public class ResultJsonObjectOperationStat implements ResultJsonObjectMarker {
    private Operation type;
    private int totalDays;
    private List<MyEntryOperationStat> customers;
    private int totalExpenses = 0;
    private double avgExpenses = 0;

    public ResultJsonObjectOperationStat() {
    }

    public ResultJsonObjectOperationStat(Operation type, int totalDays, List<MyEntryOperationStat> customers) {
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

    public List<MyEntryOperationStat> getCustomers() {
        return customers;
    }

    public void setCustomers(List<MyEntryOperationStat> customers) {
        this.customers = customers;
    }

    public int getTotalExpenses() {
        totalExpenses = 0;
        for (MyEntryOperationStat m : customers) {
            totalExpenses += m.getTotalExpenses();
        }
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getAvgExpenses() {
        avgExpenses = getTotalExpenses() / customers.size();
        return avgExpenses;
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultJsonObjectOperationStat that = (ResultJsonObjectOperationStat) o;
        return totalDays == that.totalDays && type == that.type && Objects.equals(customers, that.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, totalDays, customers);
    }


    /**
     * Вложенный статический класс, содержит еще 1 вложенный статический класс.
     * <p>
     * Данный класс содержит полное имя покупателя,
     * список покупок и суммарную стоимость покупок,
     * все данные актуальны для определенного промежутка времени для одного покупателя.
     */
    public static class MyEntryOperationStat implements Comparable<MyEntryOperationStat> {
        private String name;
        private List<InnerEntryProductInfo> purchases;
        private int totalExpenses;

        public MyEntryOperationStat() {
        }


        public MyEntryOperationStat(String name, List<InnerEntryProductInfo> purchases, int totalExpenses) {
            this.name = name;
            this.purchases = purchases;
            this.totalExpenses = totalExpenses;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<InnerEntryProductInfo> getPurchases() {
            return purchases;
        }

        public void setPurchases(List<InnerEntryProductInfo> purchases) {
            this.purchases = purchases;
        }


        public int getTotalExpenses() {
            totalExpenses = 0;
            for (InnerEntryProductInfo i : purchases) {
                totalExpenses += i.getExpenses();
            }
            return totalExpenses;
        }

        public void setTotalExpenses(int totalExpenses) {
            this.totalExpenses = totalExpenses;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MyEntryOperationStat that = (MyEntryOperationStat) o;
            return Objects.equals(name, that.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public int compareTo(MyEntryOperationStat o) {
            return this.getTotalExpenses() - o.getTotalExpenses();
        }

        @Override
        public String toString() {
            return "MyEntryOperationStat{" +
                    "name='" + name + '\'' +
                    ", totalExpenses=" + getTotalExpenses() +
                    '}';
        }


        /**
         * Вложенный статический класс.
         * <p>
         * Содержит данные о названии продукта и его стоимости,
         * экземпляры данного класса хранятся в списке внешнего класса.
         */
        public static class InnerEntryProductInfo {
            private String name;
            private int expenses;

            public InnerEntryProductInfo() {
            }

            public InnerEntryProductInfo(String name, int expenses) {
                this.name = name;
                this.expenses = expenses;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getExpenses() {
                return expenses;
            }

            public void setExpenses(int expenses) {
                this.expenses = expenses;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                InnerEntryProductInfo that = (InnerEntryProductInfo) o;
                return expenses == that.getExpenses() && Objects.equals(name, that.getName());
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, expenses);
            }

        }
    }
}
