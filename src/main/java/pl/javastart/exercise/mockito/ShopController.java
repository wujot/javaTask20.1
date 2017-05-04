package pl.javastart.exercise.mockito;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();

    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            if (item.getAgeRestriction() > human.getAge()) {
                throw new TooYoungException();
            }

        } else {
            // TODO sklep nie ma danego przedmiotu, wyrzuć wyjątek OutOfStockException
        }

    }

}
