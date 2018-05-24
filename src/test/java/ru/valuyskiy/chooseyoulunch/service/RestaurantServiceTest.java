package ru.valuyskiy.chooseyoulunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.valuyskiy.chooseyoulunch.RestaurantTestData;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;
import ru.valuyskiy.chooseyourlunch.service.RestaurantServiceImpl;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyoulunch.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantServiceImpl service;

    @Test
    public void get() {
        Restaurant user = service.get(RestaurantTestData.RESTAURANT1_ID);
        assertMatch(user, RestaurantTestData.RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        service.get(1);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void create() {
        Restaurant newRestaurant = getCreated();
        Restaurant created = service.create(newRestaurant);
        assertMatch(service.getAll(), created, RESTAURANT2, RESTAURANT1);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void getAll() {
        List<Restaurant> restaurants = service.getAll();
        assertMatch(restaurants, RESTAURANT2, RESTAURANT1);
    }
}