package deserialized_objects;

import java.util.List;
import java.util.Objects;

public class MyEntryOperationStat<T> {
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
            totalExpenses += i.expenses;
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
        MyEntryOperationStat<?> that = (MyEntryOperationStat<?>) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

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
            return expenses == that.expenses && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, expenses);
        }
    }
}
