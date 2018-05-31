package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Dish;

import java.util.List;

public interface DishService extends AbstractCrudService<Dish> {

    List<Dish> getByMenuId(int menuId);

}
