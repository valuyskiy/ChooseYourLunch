package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.repository.DishRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

@Service("dishesService")
public class DishServiceImpl implements DishService{

    @Autowired
    DishRepository repository;

    @Override
    public Dish create(Dish dish) {
        return null;
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Dish> getAll() {
        return null;
    }

    @Override
    public void update(Dish dish) {

    }

    @Override
    public void delete(int id) throws NotFoundException {

    }
}
