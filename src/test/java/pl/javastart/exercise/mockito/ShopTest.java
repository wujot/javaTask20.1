package pl.javastart.exercise.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class ShopTest {

    @Test
    public void shouldHaveItem() {

        Map<Item, Integer> stock = new HashMap<>();

        Item chleb = new Item("Chleb", 0, 2, true);
        stock.put(chleb, 5);

        Shop shop = new Shop(0, stock);

        boolean itemExist = shop.hasItem("Chleb");

        Assert.assertThat(itemExist, CoreMatchers.is(true));
    }

    @Test
    public void shouldNotHaveItem() {

        Map<Item, Integer> stock = new HashMap<>();

        Item chleb = new Item("Chleb", 0, 2, true);
        stock.put(chleb, 5);

        Shop shop = new Shop(0, stock);

        boolean itemExist = shop.hasItem("Piwo");

        Assert.assertThat(itemExist, CoreMatchers.is(false));
    }

    @Test
    public void shouldFindItemByName() {

        Map<Item, Integer> stock = new HashMap<>();

        Item mleko = new Item("Mleko", 0, 3, true);
        stock.put(mleko,6);

        Shop shop = new Shop(0,stock);

        Item itemExist = shop.findItemByName("Mleko");

        Assert.assertThat(itemExist, CoreMatchers.is(mleko));
    }

    @Test
    public void shouldNotFindItemByName() {

        Map<Item, Integer> stock = new HashMap<>();

        Item mleko = new Item("Mleko", 0, 3, true);
        stock.put(mleko,6);

        Shop shop = new Shop(0,stock);

        Item itemExist = shop.findItemByName("Chleb");

        Item notExist = null;

        Assert.assertThat(itemExist, CoreMatchers.is(notExist));
    }

    @Test public void shouldCallPlayCashSoundMethod() {

        Shop shop = Mockito.mock(Shop.class);

        shop.playCashSound();

        Mockito.verify(shop).playCashSound();
    }
}
