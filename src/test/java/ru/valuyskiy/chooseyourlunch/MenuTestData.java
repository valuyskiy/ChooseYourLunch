package ru.valuyskiy.chooseyourlunch;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.valuyskiy.chooseyourlunch.DishTestData.*;
import static ru.valuyskiy.chooseyourlunch.RestaurantTestData.RESTAURANT1;
import static ru.valuyskiy.chooseyourlunch.RestaurantTestData.RESTAURANT2;
import static ru.valuyskiy.chooseyourlunch.model.AbstractBaseEntity.START_SEQ;
import static ru.valuyskiy.chooseyourlunch.web.json.JsonUtil.writeIgnoreProps;

public class MenuTestData {
    public static final int MENU1_RESTAURANT1_ID = START_SEQ + 4;
    public static final int MENU1_RESTAURANT2_ID = START_SEQ + 9;
    public static final int MENU2_RESTAURANT1_ID = START_SEQ + 15;
    public static final int MENU2_RESTAURANT2_ID = START_SEQ + 20;
    public static final int MENU3_RESTAURANT1_ID = START_SEQ + 26;
    public static final int MENU3_RESTAURANT2_ID = START_SEQ + 31;

    public static final Menu MENU1_RESTAURANT1 = new Menu(MENU1_RESTAURANT1_ID, RESTAURANT1, LocalDate.now().minusDays(2), DISHES_MENU1_REST1);
    public static final Menu MENU1_RESTAURANT2 = new Menu(MENU1_RESTAURANT2_ID, RESTAURANT2, LocalDate.now().minusDays(2), DISHES_MENU1_REST2);
    public static final Menu MENU2_RESTAURANT1 = new Menu(MENU2_RESTAURANT1_ID, RESTAURANT1, LocalDate.now().minusDays(1), DISHES_MENU2_REST1);
    public static final Menu MENU2_RESTAURANT2 = new Menu(MENU2_RESTAURANT2_ID, RESTAURANT2, LocalDate.now().minusDays(1), DISHES_MENU2_REST2);
    public static final Menu MENU3_RESTAURANT1 = new Menu(MENU3_RESTAURANT1_ID, RESTAURANT1, LocalDate.now(), DISHES_MENU3_REST1);
    public static final Menu MENU3_RESTAURANT2 = new Menu(MENU3_RESTAURANT2_ID, RESTAURANT2, LocalDate.now(), DISHES_MENU3_REST2);

    public static final List<Menu> MENUS_TODAY = Arrays.asList(MENU3_RESTAURANT1, MENU3_RESTAURANT2);
    public static final List<Menu> MENUS_YESTERDAY = Arrays.asList(MENU2_RESTAURANT1, MENU2_RESTAURANT2);

    public static final MenuToWithDishes MENU3_TO_WITH_DISHES_RESTAURANT1 = new MenuToWithDishes(
            MENU3_RESTAURANT1.getId(),
            MENU3_RESTAURANT1.getDate(),
            MENU3_RESTAURANT1.getRestaurant(),
            MENU3_RESTAURANT1.getDishes(),
            1,
            false,
            228000
    );

    public static final MenuToWithDishes MENU3_TO_WITH_DISHES_RESTAURANT2 = new MenuToWithDishes(
            MENU3_RESTAURANT2.getId(),
            MENU3_RESTAURANT2.getDate(),
            MENU3_RESTAURANT2.getRestaurant(),
            MENU3_RESTAURANT2.getDishes(),
            0,
            false,
            338000
    );

    public static final MenuToWithDishes MENU2_TO_WITH_DISHES_RESTAURANT1 = new MenuToWithDishes(
            MENU2_RESTAURANT1.getId(),
            MENU2_RESTAURANT1.getDate(),
            MENU2_RESTAURANT1.getRestaurant(),
            MENU2_RESTAURANT1.getDishes(),
            1,
            true,
            365000
    );

    public static final MenuToWithDishes MENU2_TO_WITH_DISHES_RESTAURANT2 = new MenuToWithDishes(
            MENU2_RESTAURANT2.getId(),
            MENU2_RESTAURANT2.getDate(),
            MENU2_RESTAURANT2.getRestaurant(),
            MENU2_RESTAURANT2.getDishes(),
            1,
            false,
            334000
    );

    public static Menu getCreated() {
        return new Menu(RESTAURANT1, LocalDate.now().plusDays(1));
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_RESTAURANT1_ID, RESTAURANT1, LocalDate.now().minusDays(5));
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "dishes");
    }

    public static void assertMatch(MenuTo actual, MenuTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "dishes");
    }

    public static void assertMatch(MenuToWithDishes actual, MenuToWithDishes expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "dishes").isEqualTo(expected);
    }


    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<MenuToWithDishes> actual, MenuToWithDishes... expected) {
//        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(expected));
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "dishes").isEqualTo(Arrays.asList(expected));
    }


    public static ResultMatcher contentJson(Menu... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "restaurant", "dishes"));
    }

    public static ResultMatcher contentJson(Menu expected) {
        return content().json(writeIgnoreProps(expected, "restaurant", "dishes"));
    }
}
