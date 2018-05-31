package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuService extends AbstractCrudService<Menu> {

    List<Menu> getByRestaurantId(int restaurantId);

    List<MenuTo> getTo(LocalDate date);

    Menu getWithDishesByRestaurantAndDate(int restaurantId, LocalDate date);

    MenuTo getTo(int id);
}
