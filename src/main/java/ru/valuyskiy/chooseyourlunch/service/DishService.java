package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.to.DishTo;

import java.util.List;

public interface DishService extends AbstractCrudService<Dish> {

    List<DishTo> getToByMenuId(int menuId);

    DishTo getToById(int dishId);

    DishTo updateFromTo(DishTo dishTo);

    DishTo createFromTo(DishTo dishTo);

    DishTo toTo(Dish dish);

    Dish fromTo(DishTo dishTo);

}
