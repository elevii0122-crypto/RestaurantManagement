package hu.restaurant;

public class Food extends MenuItem {

    private int calories;

    public Food(String name, int price, int calories) throws InvalidMenuItemException {
        super(name, price);

        if (calories < 0) {
            throw new InvalidMenuItemException("A kalória nem lehet negatív!");
        }

        this.calories = calories;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Kalória: " + calories + " kcal");
    }

    public int getCalories() {
        return calories;
    }
}
