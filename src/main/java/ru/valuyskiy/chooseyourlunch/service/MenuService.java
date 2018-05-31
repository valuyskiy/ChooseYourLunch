package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;

import java.time.LocalDate;
import java.util.List;

public interface MenuService extends AbstractCrudService<Menu> {

    List<Menu> getByRestaurantId(int restaurantId);

    List<MenuToWithDishes> getTo(LocalDate date);

    Menu getWithDishesByRestaurantAndDate(int restaurantId, LocalDate date);

    MenuToWithDishes getTo(int id);
}
