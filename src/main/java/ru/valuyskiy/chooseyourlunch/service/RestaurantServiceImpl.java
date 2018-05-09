package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;
import ru.valuyskiy.chooseyourlunch.repository.RestaurantRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements BaseCrudService<Restaurant> {

    @Autowired
    RestaurantRepository repository;

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "user must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "user must not be null");
        repository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
