package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.repository.DishRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("dishesService")
public class DishServiceImpl implements DishService{

    @Autowired
    DishRepository repository;

    @Override
    public Dish create(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        return repository.save(dish);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Dish> getByMenuId(int menuId) {
        return repository.getByMenu_Id(menuId);
    }

    @Override
    public Dish update(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
