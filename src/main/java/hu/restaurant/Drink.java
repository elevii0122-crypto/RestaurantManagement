
package hu.restaurant;

public class Drink extends MenuItem {

    private double volume;
    private int calories;

    public Drink(String name, int price, double volume, int calories) throws InvalidMenuItemException {
        super(name, price);

        if (volume <= 0) {
            throw new InvalidMenuItemException("A térfogatnak pozitívnak kell lennie!");
        }

        if (calories < 0) {
            throw new InvalidMenuItemException("A kalória nem lehet negatív!");
        }

        this.volume = volume;
        this.calories = calories;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Térfogat: " + volume + " liter");
        System.out.println("Kalória: " + calories + " kcal");
    }

    public double getVolume() {
        return volume;
    }

    public int getCalories() {
        return calories;
    }
}
