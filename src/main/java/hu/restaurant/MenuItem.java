
package hu.restaurant;

public class MenuItem implements Comparable<MenuItem>, Priced {

    private String name;
    private int price;

    public MenuItem(String name, int price) throws InvalidMenuItemException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMenuItemException("A név nem lehet üres!");
        }

        if (price < 0) {
            throw new InvalidMenuItemException("Az ár nem lehet negatív!");
        }

        this.name = name;
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Név: " + name);
        System.out.println("Ár: " + price + " Ft");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(MenuItem other) {
        return Integer.compare(this.price, other.price);
    }
}
