package ru.valuyskiy.chooseyourlunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;

import java.time.LocalDate;
import java.util.List;

public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService menuService;

//    @Test
//    public void getByRestaurant() {
//        List<Menu> menus = menuService.getByRestaurantId(100002);
//        System.out.println(menus);
//    }

    @Test
    public void getByDate() {
        List<MenuToWithDishes> menus = menuService.getToWithDishes(LocalDate.of(2018, 5, 20));
        System.out.println(menus);
    }

//    @Test
//    public void getByRestaurantAndDate() {
//        Menu menu = menuService.getWithDishesByRestaurantAndDate(100002, LocalDate.of(2018, 5, 20));
//        System.out.println(menu);
//    }
}