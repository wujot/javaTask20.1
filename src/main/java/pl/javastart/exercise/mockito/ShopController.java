package pl.javastart.exercise.mockito;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();
    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            // age restriction
            if (item.getAgeRestriction() > human.getAge()) {
                throw new TooYoungException();
                // illegal restriction
            } else if (!item.isLegal() && human.getJob().equals("Policjant")) {
                throw new IllegalException();
                // not enough money
            } else if (item.getPrice() > human.getMoney()) {
                throw new NotEnoughMoneyException();
                // otherwise sell item
            } else {
                // play sound
                shop.playCashSound();
                // charge customer
                int chargeCustomer = human.getMoney() - item.getPrice();
                human.setMoney(chargeCustomer);
                // cash into shop
                int cashIntoShop = shop.getMoney() + item.getPrice();
                shop.setMoney(cashIntoShop);
                // after transaction quantity of item is subtract from the stock
                int quantity = shop.getStock().get(item) - 1;
                Item tempItem = shop.findItemByName(itemName);
                shop.getStock().remove(itemName);
                shop.getStock().put(tempItem, quantity);
                // if no more quantity of item in stock then remove it
                if (shop.getStock().get(item) <= 0) {
                    shop.getStock().remove(item);
                }
            }

        } else {
            // TODO sklep nie ma danego przedmiotu, wyrzuć wyjątek OutOfStockException
            throw new OutOfStockException();
        }

    }

}
