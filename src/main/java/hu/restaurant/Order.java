package hu.restaurant;


import java.util.ArrayList;

public class Order {

    private ArrayList<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public int getTotalPrice() {
        int total = 0;

        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice();
        }

        return total;
    }

    public void displayOrder() {
        System.out.println("--- RENDELÉS ---");

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". "
                    + items.get(i).getName()
                    + " - " + items.get(i).getPrice() + " Ft");
        }

        System.out.println("Végösszeg: " + getTotalPrice() + " Ft");
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

}
