package hu.restaurant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @Test
    public void testTotalPrice() throws InvalidMenuItemException {

        Order order = new Order();

        Food pizza = new Food("Pizza", 1500, 800);
        Drink cola = new Drink("Cola", 800, 0.5, 200);

        order.addItem(pizza);
        order.addItem(cola);

        assertEquals(2300, order.getTotalPrice());
    }

    @Test
    public void testNegativePrice() {

        assertThrows(InvalidMenuItemException.class, () -> {
            new Food("Pizza", -1500, 800);
        });

    }

    @Test
    public void testSingleton() {

        Restaurant r1 = Restaurant.getInstance();
        Restaurant r2 = Restaurant.getInstance();

        assertSame(r1, r2);
    }
}

