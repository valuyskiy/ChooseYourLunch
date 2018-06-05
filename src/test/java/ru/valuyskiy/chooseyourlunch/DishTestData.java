package ru.valuyskiy.chooseyourlunch;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.to.DishTo;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.valuyskiy.chooseyourlunch.MenuTestData.*;
import static ru.valuyskiy.chooseyourlunch.model.AbstractBaseEntity.START_SEQ;
import static ru.valuyskiy.chooseyourlunch.web.json.JsonUtil.writeValue;

public class DishTestData {
    public static final int DISH1_MENU1_REST1_ID = START_SEQ + 5;
    public static final int DISH2_MENU1_REST1_ID = START_SEQ + 6;
    public static final int DISH3_MENU1_REST1_ID = START_SEQ + 7;
    public static final int DISH4_MENU1_REST1_ID = START_SEQ + 8;

    public static final Dish DISH1_MENU1_REST1 = new Dish(DISH1_MENU1_REST1_ID, "Сладкие томаты с крымским луком", MENU1_RESTAURANT1, 69000);
    public static final Dish DISH2_MENU1_REST1 = new Dish(DISH2_MENU1_REST1_ID, "Устрица Дальневосточная", MENU1_RESTAURANT1, 29000);
    public static final Dish DISH3_MENU1_REST1 = new Dish(DISH3_MENU1_REST1_ID, "Мидии в сливочном соусе", MENU1_RESTAURANT1, 89000);
    public static final Dish DISH4_MENU1_REST1 = new Dish(DISH4_MENU1_REST1_ID, "Окрошка на квасе", MENU1_RESTAURANT1, 38000);

    public static final List<Dish> DISHES_MENU1_REST1 = Arrays.asList(DISH1_MENU1_REST1, DISH2_MENU1_REST1, DISH3_MENU1_REST1, DISH4_MENU1_REST1);


    public static final int DISH1_MENU1_REST2_ID = START_SEQ + 10;
    public static final int DISH2_MENU1_REST2_ID = START_SEQ + 11;
    public static final int DISH3_MENU1_REST2_ID = START_SEQ + 12;
    public static final int DISH4_MENU1_REST2_ID = START_SEQ + 13;
    public static final int DISH5_MENU1_REST2_ID = START_SEQ + 14;

    public static final Dish DISH1_MENU1_REST2 = new Dish(DISH1_MENU1_REST2_ID, "Свекольный тартар с копченой грудкой цесарки", MENU1_RESTAURANT2, 48000);
    public static final Dish DISH2_MENU1_REST2 = new Dish(DISH2_MENU1_REST2_ID, "Салат с ягненком, запеченным перцем и подмаринованным баклажаном", MENU1_RESTAURANT2, 850000);
    public static final Dish DISH3_MENU1_REST2 = new Dish(DISH3_MENU1_REST2_ID, "Консоме из курицы с мини пельменями", MENU1_RESTAURANT2, 45000);
    public static final Dish DISH4_MENU1_REST2 = new Dish(DISH4_MENU1_REST2_ID, "Кальмары по-галисийски с запеченными перцами", MENU1_RESTAURANT2, 75000);
    public static final Dish DISH5_MENU1_REST2 = new Dish(DISH5_MENU1_REST2_ID, "Яблочный татин с мороженым из кленового сиропа", MENU1_RESTAURANT2, 38000);

    public static final List<Dish> DISHES_MENU1_REST2 = Arrays.asList(DISH1_MENU1_REST2, DISH2_MENU1_REST2, DISH3_MENU1_REST2, DISH4_MENU1_REST2, DISH5_MENU1_REST2);


    public static final int DISH1_MENU2_REST1_ID = START_SEQ + 16;
    public static final int DISH2_MENU2_REST1_ID = START_SEQ + 17;
    public static final int DISH3_MENU2_REST1_ID = START_SEQ + 18;
    public static final int DISH4_MENU2_REST1_ID = START_SEQ + 19;

    public static final Dish DISH1_MENU2_REST1 = new Dish(DISH1_MENU2_REST1_ID, "Тигровые креветки", MENU2_RESTAURANT1, 99000);
    public static final Dish DISH2_MENU2_REST1 = new Dish(DISH2_MENU2_REST1_ID, "Тартар из говядины", MENU2_RESTAURANT1, 138000);
    public static final Dish DISH3_MENU2_REST1 = new Dish(DISH3_MENU2_REST1_ID, "Мидии в томатном соусе", MENU2_RESTAURANT1, 89000);
    public static final Dish DISH4_MENU2_REST1 = new Dish(DISH4_MENU2_REST1_ID, "Щавеливый суп", MENU2_RESTAURANT1, 39000);

    public static final List<Dish> DISHES_MENU2_REST1 = Arrays.asList(DISH1_MENU2_REST1, DISH2_MENU2_REST1, DISH3_MENU2_REST1, DISH4_MENU2_REST1);

    public static final int DISH1_MENU2_REST2_ID = START_SEQ + 21;
    public static final int DISH2_MENU2_REST2_ID = START_SEQ + 22;
    public static final int DISH3_MENU2_REST2_ID = START_SEQ + 23;
    public static final int DISH4_MENU2_REST2_ID = START_SEQ + 24;
    public static final int DISH5_MENU2_REST2_ID = START_SEQ + 25;

    public static final Dish DISH1_MENU2_REST2 = new Dish(DISH1_MENU2_REST2_ID, "Паштет из нерки с морскими водорослями и маслом из апельсина", MENU2_RESTAURANT2, 55000);
    public static final Dish DISH2_MENU2_REST2 = new Dish(DISH2_MENU2_REST2_ID, "Салат из моцареллы с ростбифом и маринованным перцем", MENU2_RESTAURANT2, 106000);
    public static final Dish DISH3_MENU2_REST2 = new Dish(DISH3_MENU2_REST2_ID, "Старомодный Картофельный суп", MENU2_RESTAURANT2, 55000);
    public static final Dish DISH4_MENU2_REST2 = new Dish(DISH4_MENU2_REST2_ID, "Наша котлета по-киевски со свекольным ризотто", MENU2_RESTAURANT2, 78000);
    public static final Dish DISH5_MENU2_REST2 = new Dish(DISH5_MENU2_REST2_ID, "Сырное пирожное с карамелью", MENU2_RESTAURANT2, 40000);

    public static final List<Dish> DISHES_MENU2_REST2 = Arrays.asList(DISH1_MENU2_REST2, DISH2_MENU2_REST2, DISH3_MENU2_REST2, DISH4_MENU2_REST2, DISH5_MENU2_REST2);


    public static final int DISH1_MENU3_REST1_ID = START_SEQ + 27;
    public static final int DISH2_MENU3_REST1_ID = START_SEQ + 28;
    public static final int DISH3_MENU3_REST1_ID = START_SEQ + 29;
    public static final int DISH4_MENU3_REST1_ID = START_SEQ + 30;

    public static final Dish DISH1_MENU3_REST1 = new Dish(DISH1_MENU3_REST1_ID, "Печень трески с тёплым картофелем", MENU3_RESTAURANT1, 67000);
    public static final Dish DISH2_MENU3_REST1 = new Dish(DISH2_MENU3_REST1_ID, "Рапан запечёный под сыром", MENU3_RESTAURANT1, 58000);
    public static final Dish DISH3_MENU3_REST1 = new Dish(DISH3_MENU3_REST1_ID, "Свежие овощи", MENU3_RESTAURANT1, 69000);
    public static final Dish DISH4_MENU3_REST1 = new Dish(DISH4_MENU3_REST1_ID, "Свекольник", MENU3_RESTAURANT1, 34000);

    public static final List<Dish> DISHES_MENU3_REST1 = Arrays.asList(DISH1_MENU3_REST1, DISH2_MENU3_REST1, DISH3_MENU3_REST1, DISH4_MENU3_REST1);

    public static final int DISH1_MENU3_REST2_ID = START_SEQ + 32;
    public static final int DISH2_MENU3_REST2_ID = START_SEQ + 33;
    public static final int DISH3_MENU3_REST2_ID = START_SEQ + 34;
    public static final int DISH4_MENU3_REST2_ID = START_SEQ + 35;
    public static final int DISH5_MENU3_REST2_ID = START_SEQ + 36;

    public static final Dish DISH1_MENU3_REST2 = new Dish(DISH1_MENU3_REST2_ID, "Тёплый салат с куриной печенью", MENU3_RESTAURANT2, 66000);
    public static final Dish DISH2_MENU3_REST2 = new Dish(DISH2_MENU3_REST2_ID, "Салат из моцареллы с ростбифом и маринованным перцем", MENU3_RESTAURANT2, 106000);
    public static final Dish DISH3_MENU3_REST2 = new Dish(DISH3_MENU3_REST2_ID, "Наш борщ с дикой копченой уткой", MENU3_RESTAURANT2, 55000);
    public static final Dish DISH4_MENU3_REST2 = new Dish(DISH4_MENU3_REST2_ID, "Куриный стейк с овощами и легким муссом из картофеля", MENU3_RESTAURANT2, 75000);
    public static final Dish DISH5_MENU3_REST2 = new Dish(DISH5_MENU3_REST2_ID, "Медовая панна котта с сорбетом из смородины", MENU3_RESTAURANT2, 36000);

    public static final List<Dish> DISHES_MENU3_REST2 = Arrays.asList(DISH1_MENU3_REST2, DISH2_MENU3_REST2, DISH3_MENU3_REST2, DISH4_MENU3_REST2, DISH5_MENU3_REST2);

    public static DishTo getCreated() {
        return new DishTo("Блинчики со сметаной", 42000, MENU1_RESTAURANT1_ID);
    }

    public static DishTo getUpdated() {
        return new DishTo(DISH1_MENU1_REST1_ID, "Новый Оливье с магре", 65000, MENU1_RESTAURANT1_ID);
    }

    public static void assertMatch(DishTo actual, DishTo expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<DishTo> actual, Iterable<DishTo> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<DishTo> actual, DishTo... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(DishTo... expected) {
        return content().json(writeValue(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(DishTo expected) {
        return content().json(writeValue(expected));
    }
}