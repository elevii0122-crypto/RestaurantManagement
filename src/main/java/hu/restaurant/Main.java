
package hu.restaurant;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = Restaurant.getInstance();

        restaurant.loadMenu();
        restaurant.loadOrders();

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== ÉTTEREM KEZELŐ =====");
            System.out.println("1. Étlap megtekintése");
            System.out.println("2. Új étel vagy ital hozzáadása");
            System.out.println("3. Keresés az étlapon");
            System.out.println("4. Étlap rendezése ár szerint");
            System.out.println("5. Új rendelés");
            System.out.println("6. Rendelések megtekintése");
            System.out.println("0. Kilépés");

            System.out.print("Választás: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    restaurant.listMenu();
                    break;

                case 2:
                    System.out.println("1. Étel");
                    System.out.println("2. Ital");
                    System.out.print("Típus: ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type != 1 && type != 2) {
                        System.out.println("Érvénytelen típus!");
                        break;
                    }

                    System.out.print("Név: ");
                    String name = scanner.nextLine();

                    System.out.print("Ár (Ft): ");
                    int price = scanner.nextInt();

                    System.out.print("Kalória (kcal): ");
                    int calories = scanner.nextInt();

                    try {
                        if (type == 1) {
                            restaurant.addMenuItem(new Food(name, price, calories));
                        } else {
                            System.out.print("Térfogat (liter): ");
                            double volume = scanner.nextDouble();

                            restaurant.addMenuItem(new Drink(name, price, volume, calories));
                        }

                    } catch (InvalidMenuItemException e) {
                        System.out.println("Hiba: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Keresett étel vagy ital neve: ");
                    String searchName = scanner.nextLine();

                    restaurant.searchMenuItem(searchName);
                    break;

                case 4:
                    restaurant.sortMenuByPrice();
                    break;

                case 5:
                    Order newOrder = new Order();

                    System.out.println("\n===== ÚJ RENDELÉS =====");
                    restaurant.listMenu();

                    int itemNumber = -1;
                    int itemCount = 0;

                    while (itemNumber != 0) {
                        System.out.print("Válassz egy tételt (0 = lezárás): ");
                        itemNumber = scanner.nextInt();

                        if (itemNumber == 0) {
                            break;
                        }

                        MenuItem selectedItem = restaurant.getMenuItem(itemNumber - 1);

                        if (selectedItem != null) {
                            newOrder.addItem(selectedItem);
                            itemCount++;
                            System.out.println("Tétel hozzáadva!");
                        } else {
                            System.out.println("Érvénytelen sorszám!");
                        }
                    }

                    if (itemCount > 0) {
                        newOrder.displayOrder();
                        restaurant.addOrder(newOrder);
                    } else {
                        System.out.println("Üres rendelés nem került mentésre.");
                    }
                    break;

                case 6:
                    restaurant.listOrders();
                    break;

                case 0:
                    restaurant.saveMenu();
                    restaurant.saveOrders();
                    System.out.println("Program bezárása...");
                    break;

                default:
                    System.out.println("Ez a funkció még nincs kész!");
                    break;
            }
        }

        scanner.close();
    }
}
