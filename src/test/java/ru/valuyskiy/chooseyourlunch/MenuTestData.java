package ru.valuyskiy.chooseyourlunch;

import ru.valuyskiy.chooseyourlunch.model.Menu;

import java.time.LocalDate;

import static ru.valuyskiy.chooseyourlunch.RestaurantTestData.*;
import static ru.valuyskiy.chooseyourlunch.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static final int MENU1_RESTAURANT1_ID = START_SEQ + 4;
    public static final int MENU1_RESTAURANT2_ID = START_SEQ + 9;
    public static final int MENU2_RESTAURANT1_ID = START_SEQ + 15;
    public static final int MENU2_RESTAURANT2_ID = START_SEQ + 20;
    public static final int MENU3_RESTAURANT1_ID = START_SEQ + 26;
    public static final int MENU3_RESTAURANT2_ID = START_SEQ + 31;

    public static final Menu MENU1_RESTAURANT1 = new Menu(RESTAURANT1_ID, RESTAURANT1, LocalDate.of(2018, 5, 19));
    public static final Menu MENU1_RESTAURANT2 = new Menu(RESTAURANT2_ID, RESTAURANT2, LocalDate.of(2018, 5, 19));
    public static final Menu MENU2_RESTAURANT1 = new Menu(RESTAURANT1_ID, RESTAURANT1, LocalDate.of(2018, 5, 20));
    public static final Menu MENU2_RESTAURANT2 = new Menu(RESTAURANT2_ID, RESTAURANT2, LocalDate.of(2018, 5, 20));
    public static final Menu MENU3_RESTAURANT1 = new Menu(RESTAURANT1_ID, RESTAURANT1, LocalDate.of(2018, 5, 21));
    public static final Menu MENU3_RESTAURANT2 = new Menu(RESTAURANT2_ID, RESTAURANT2, LocalDate.of(2018, 5, 21));

}
