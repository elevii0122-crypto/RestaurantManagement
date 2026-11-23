
package hu.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Restaurant {


    private ArrayList<MenuItem> menu;
    private ArrayList<Order> orders;

    private static Restaurant instance;

    private Restaurant() {
        menu = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public static Restaurant getInstance() {
        if (instance == null) {
            instance = new Restaurant();
        }

        return instance;
    }


    public void addMenuItem(MenuItem item) {
        menu.add(item);
        System.out.println("Sikeres hozzáadás!");
    }

    public void listMenu() {
        if (menu.isEmpty()) {
            System.out.println("Az étlap üres!");
            return;
        }

        for (int i = 0; i < menu.size(); i++) {
            System.out.print((i + 1) + ". ");
            menu.get(i).displayInfo();
            System.out.println();
        }
    }

    public void searchMenuItem(String name) {
        boolean found = false;

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().equalsIgnoreCase(name)) {
                menu.get(i).displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nincs ilyen étel vagy ital!");
        }
    }

    public void sortMenuByPrice() {
        Collections.sort(menu);
        System.out.println("Étlap ár szerint rendezve:");
        listMenu();
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Rendelés sikeresen felvéve!");
    }

    public void listOrders() {
        if (orders.isEmpty()) {
            System.out.println("Még nincs rendelés!");
            return;
        }

        for (int i = 0; i < orders.size(); i++) {
            System.out.println("\nRendelés #" + (i + 1));
            orders.get(i).displayOrder();
        }
    }

    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        }

        return null;
    }

    public void saveMenu() {
        System.out.println("Mentés elindult!");

        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter("menu.txt"));

            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.get(i);

                if (item instanceof Food) {
                    Food food = (Food) item;

                    writer.println("FOOD;" + food.getName()
                            + ";" + food.getPrice()
                            + ";" + food.getCalories());

                } else if (item instanceof Drink) {
                    Drink drink = (Drink) item;

                    writer.println("DRINK;" + drink.getName()
                            + ";" + drink.getPrice()
                            + ";" + drink.getVolume()
                            + ";" + drink.getCalories());
                }
            }

            writer.close();
            System.out.println("Étlap sikeresen mentve!");

        } catch (IOException e) {
            System.out.println("Hiba történt a mentés során!");
        }
    }

    public void loadMenu() {
        try {
            Scanner fileScanner = new Scanner(new File("menu.txt"));

            menu.clear();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");

                if (data[0].equals("FOOD")) {
                    String name = data[1];
                    int price = Integer.parseInt(data[2]);
                    int calories = Integer.parseInt(data[3]);

                    menu.add(new Food(name, price, calories));

                } else if (data[0].equals("DRINK")) {
                    String name = data[1];
                    int price = Integer.parseInt(data[2]);
                    double volume = Double.parseDouble(data[3]);
                    int calories = Integer.parseInt(data[4]);

                    menu.add(new Drink(name, price, volume, calories));
                }
            }

            fileScanner.close();
            System.out.println("Étlap sikeresen betöltve!");

        } catch (FileNotFoundException e) {
            System.out.println("Nincs mentett étlap, az alapértelmezett marad.");

        } catch (InvalidMenuItemException e) {
            System.out.println("Hibás étlapadat: " + e.getMessage());
        }
    }

    public void saveOrders() {
        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter("orders.txt"));

            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);

                for (int j = 0; j < order.getItems().size(); j++) {
                    MenuItem item = order.getItems().get(j);

                    if (item instanceof Food) {
                        Food food = (Food) item;

                        writer.println("FOOD;" + food.getName()
                                + ";" + food.getPrice()
                                + ";" + food.getCalories());

                    } else if (item instanceof Drink) {
                        Drink drink = (Drink) item;

                        writer.println("DRINK;" + drink.getName()
                                + ";" + drink.getPrice()
                                + ";" + drink.getVolume()
                                + ";" + drink.getCalories());
                    }
                }

                writer.println("END");
            }

            writer.close();
            System.out.println("Rendelések sikeresen mentve!");

        } catch (IOException e) {
            System.out.println("Hiba történt a rendelések mentésekor!");
        }
    }

    public void loadOrders() {
        try {
            Scanner fileScanner = new Scanner(new File("orders.txt"));

            orders.clear();
            Order order = new Order();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.equals("END")) {
                    orders.add(order);
                    order = new Order();

                } else {
                    String[] data = line.split(";");

                    if (data[0].equals("FOOD")) {
                        String name = data[1];
                        int price = Integer.parseInt(data[2]);
                        int calories = Integer.parseInt(data[3]);

                        order.addItem(new Food(name, price, calories));

                    } else if (data[0].equals("DRINK")) {
                        String name = data[1];
                        int price = Integer.parseInt(data[2]);
                        double volume = Double.parseDouble(data[3]);
                        int calories = Integer.parseInt(data[4]);

                        order.addItem(new Drink(name, price, volume, calories));
                    }
                }
            }

            fileScanner.close();
            System.out.println("Rendelések sikeresen betöltve!");

        } catch (FileNotFoundException e) {
            System.out.println("Nincs mentett rendelés.");

        } catch (InvalidMenuItemException e) {
            System.out.println("Hibás rendelési adat: " + e.getMessage());
        }
    }
}
