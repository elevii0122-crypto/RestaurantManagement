# RestaurantManagement

## A projekt bemutatása

A RestaurantManagement egy Java nyelven készült, konzolos étteremkezelő alkalmazás, amely lehetővé teszi az étlap és a rendelések kezelését.

## Funkciók

- Ételek és italok hozzáadása az étlaphoz
- Az étlap megjelenítése
- Menüelemek keresése név alapján
- Menüelemek rendezése ár szerint
- Új rendelések létrehozása
- Rendelések és végösszegek megjelenítése
- Adatok mentése és betöltése szöveges fájlokból

## Tervezési minta – Singleton

A projekt a Singleton tervezési mintát használja a `Restaurant` osztályban.

A program egyetlen éttermet kezel, ezért nincs szükség több különálló `Restaurant` objektumra. A Singleton biztosítja, hogy az alkalmazás minden része ugyanazt az étlapot és rendeléslistát használja.

A `Restaurant` konstruktora privát, az objektum pedig a `getInstance()` metóduson keresztül érhető el.

A Singleton működését a `testSingleton()` JUnit-teszt ellenőrzi, amely az `assertSame()` segítségével igazolja, hogy két `getInstance()` hívás ugyanazt az objektumot adja vissza.

## Tesztelés

A projekt JUnit teszteket tartalmaz, amelyek ellenőrzik a rendelés végösszegének kiszámítását és a negatív árak kezelését.

## Futtatás

1. A projekt megnyitása IntelliJ IDEA-ban.
2. A Maven-függőségek betöltése.
3. A Main.java osztály futtatása.
4. A konzolos menü használata.

## Megvalósított követelmények

- **Encapsulation:** Az osztályok adattagjai privátok, elérésük metódusokon keresztül történik.
- **Abstraction:** A `Priced` interfész meghatározza az árral rendelkező objektumok közös működését.
- **Inheritance:** A `Food` és `Drink` osztályok a `MenuItem` osztályból öröklődnek.
- **Polymorphism:** A `displayInfo()` metódust az ételek és italok eltérően valósítják meg.
- **Relationships:** A `Restaurant` étlapot és rendeléseket, az `Order` menüelemeket tárol.
- **Collections and Generics:** `ArrayList<MenuItem>` és `ArrayList<Order>` használata.
- **Sorting:** A `Comparable<MenuItem>` interfész segítségével ár szerinti rendezés.
- **Exceptions:** Saját `InvalidMenuItemException` kivételosztály.
- **Persistence:** Étlap és rendelések mentése, illetve betöltése szöveges fájlokból.
- **Design Pattern:** Singleton a `Restaurant` osztályban.
- **Testing:** Három sikeres JUnit 5 teszt.

## Fordítás és tesztelés

A projekt főmappájában terminálból a következő parancsok használhatók.

**Fordítás:**

```bash
mvn clean compile
```

**JUnit-tesztek futtatása:**

```bash
mvn test
```

**A teljes projekt összeállítása:**

```bash
mvn clean package
```

## A program futtatása

1. Nyissuk meg a projektet IntelliJ IDEA-ban.
2. Várjuk meg, amíg a Maven betölti a függőségeket.
3. Nyissuk meg a `src/main/java/hu/restaurant/Main.java` fájlt.
4. Futtassuk a `Main` osztály `main()` metódusát.

A program konzolos menüjében a felhasználó megtekintheti az étlapot, hozzáadhat új ételeket és italokat, kereshet és rendezhet, rendeléseket hozhat létre, valamint kilépéskor mentheti az adatokat.
## UML-osztálydiagram

![UML-osztálydiagram](docs/UML.png)
