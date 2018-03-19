package pl.javastart.exercise.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class ShopControllerTest {

    @Mock
    ShopRepository shopRepository;

    private ShopController shopController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        stock.put(new Item("Ziolo", 18, 30, false), 20);
        stock.put(new Item("Mleko", 0, 3, true), 4);
        stock.put(new Item("Kokaina", 18, 300, false), 1);

        Shop shop = new Shop(0, stock);

        when(shopRepository.findShop()).thenReturn(shop);

        shopController = new ShopController(shopRepository);
    }

    // too young exception test
    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoungCustomer() {
        // given
        Human teenager = new Human("Maciek", 15, "Uczen", 15);

        // when
        shopController.sellItem(teenager, "Piwo");
    }

    // illegal item for unexpected customer exception test
    @Test(expected = IllegalException.class)
    public void shouldNotSellWeedToPoliceman() {
        // given
        Human policjant = new Human("Marian" , 46, "Policjant", 150);

        // when
        shopController.sellItem(policjant, "Ziolo");
    }

    // not enough money exception test
    @Test(expected = NotEnoughMoneyException.class)
    public void shouldNotSellItemIfCustomerHasNotEnoughMoney() {
        // given
        Human pospolityDres = new Human("Waldemar" , 26, "Zlodziej", 120);

        // when
        shopController.sellItem(pospolityDres, "Kokaina");
    }

    // out of stock exception test
    @Test(expected = OutOfStockException.class)
    public void shouldNotSellItemIfItemIsOutOfStock() {
        // given
        Human proboszcz = new Human("Ireneusz" , 57, "Ksiadz", 2000);

        // when
        shopController.sellItem(proboszcz, "Twoj weekend");
    }

    // correct transactions tests

        // if customer bought item then customers money should be subtract by item price
        @Test
        public void afterBoughtItemByCustomerShouldReturn2() {
            // given
            Human paniBasia = new Human("Barbara", 68, "Emerytka", 5);

            // when
            shopController.sellItem(paniBasia, "Mleko");

            // then
            Assert.assertThat(paniBasia.getMoney(), CoreMatchers.is(2));
        }

        // if customer bought item then money should be transfer into the shop
        @Test
        public void afterBoughtItemByCustomerShouldReturn30() {
            // given
            Human hydraulik = new Human("Zbigniew", 44, "Hydraulik", 100);

            // when
            shopController.sellItem(hydraulik, "Ziolo");

            // then
            Assert.assertThat(shopRepository.findShop().getMoney(), CoreMatchers.is(30));
        }

        // if customer bought item then item should be subtract from the stock
        @Test
        public void afterBoughtItemByCustomerShouldReturn4() {
            // given
            Human policjant = new Human("Marian" , 46, "Policjant", 150);

            // when
            shopController.sellItem(policjant, "Piwo");

            // then
            Assert.assertThat(shopRepository.findShop().getStock().get(shopRepository.findShop().findItemByName("Piwo")), CoreMatchers.is(4));
        }

        // if quantity of item in stock after buy is equal 0 then remove item from stock
        @Test
        public void afterBoughtItemByCustomerShouldReturnFalse() {
            // given
            Human figotFagot = new Human("Alfred" , 38, "Adwokat", 500);

            // when
            shopController.sellItem(figotFagot, "Kokaina");

            // then
            Assert.assertThat(shopRepository.findShop().hasItem("Kokaina"), CoreMatchers.is(false));
        }

}
