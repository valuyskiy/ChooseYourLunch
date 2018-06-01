package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;

import java.time.LocalDate;
import java.util.List;

public interface MenuService extends AbstractCrudService<Menu> {

    List<MenuTo> getToByRestaurantId(int restaurantId);

    MenuTo getTo(Menu menu);

    List<MenuToWithDishes> getToWithDishes(LocalDate date);

    MenuToWithDishes getToWithDishes(Menu menu);

    List<Menu> getWithDishesByDate(LocalDate date);

    Menu createByRestaurantIdAndDate(int restaurantId, LocalDate date);

    Menu update(MenuTo menuTo);
}
