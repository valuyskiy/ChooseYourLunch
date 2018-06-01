package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.repository.DishRepository;
import ru.valuyskiy.chooseyourlunch.repository.MenuRepository;
import ru.valuyskiy.chooseyourlunch.to.DishTo;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("dishesService")
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Dish create(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        return dishRepository.save(dish);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(dishRepository.findById(id).orElse(null), id);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish update(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        return checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.delete(id) != 0, id);
    }

    @Override
    public List<DishTo> getToByMenuId(int menuId) {
        return dishRepository.getByMenu_Id(menuId).stream()
                .map(this::toTo)
                .collect(Collectors.toList());
    }

    @Override
    public DishTo getToById(int dishId) {
        return toTo(get(dishId));
    }

    @Override
    public DishTo updateFromTo(DishTo dishTo) {
        return toTo(update(fromTo(dishTo)));
    }

    @Override
    public DishTo createFromTo(DishTo dishTo) {
        return toTo(create(fromTo(dishTo)));
    }

    @Override
    public DishTo toTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice(), dish.getMenu().getId());
    }

    @Override
    public Dish fromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), menuRepository.getOne(dishTo.getMenuId()), dishTo.getPrice());
    }
}
