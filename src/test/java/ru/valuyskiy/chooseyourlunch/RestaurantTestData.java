package ru.valuyskiy.chooseyourlunch;

import ru.valuyskiy.chooseyourlunch.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.valuyskiy.chooseyourlunch.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Обломов");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "The Сад");

    public static Restaurant getCreated() {
        return new Restaurant("Lari");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1.getId(), "Lari");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
