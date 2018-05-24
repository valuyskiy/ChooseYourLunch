package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService extends BaseCrudService<Menu>{

    List<Menu> getByRestaurantId(int restaurantId);

    List<Menu> getByDate(LocalDate date);

    Menu getWithDishesByRestaurantAndDate(int restaurantId, LocalDate date);

}
