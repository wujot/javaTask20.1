package pl.javastart.exercise.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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

        Shop shop = new Shop(0, stock);

        when(shopRepository.findShop()).thenReturn(shop);

        shopController = new ShopController(shopRepository);
    }

    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoungling() {
        // given
        Human human = new Human();

        // when
        shopController.sellItem(human, "Piwo");
    }

}
