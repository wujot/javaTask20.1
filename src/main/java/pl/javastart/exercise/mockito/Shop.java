package pl.javastart.exercise.mockito;

import java.util.Map;

public class Shop {

    private int money;
    private Map<Item, Integer> stock;

    public Shop(int money, Map<Item, Integer> stock) {
        this.money = money;
        this.stock = stock;
    }

    void playCashSound() {
        /* zakładamy, że ta metoda odtwarza dźwięk https://www.youtube.com/watch?v=Wj_OmtqVLxY, nie musimy jej implementować,
        sprawdzamy tylko czy została uruchomiona */
    }

    public boolean hasItem(String itemName) {
        // TODO dodaj kod sprawdzający czy sklep na w asortymencie przedmot o danej nazwie
        for (Item item : stock.keySet()) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item findItemByName(String itemName) {
        // TODO dodaj kod wyszukujący przedmiot po jego nazwie
        for (Item item : stock.keySet()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Map<Item, Integer> getStock() {
        return stock;
    }
}
