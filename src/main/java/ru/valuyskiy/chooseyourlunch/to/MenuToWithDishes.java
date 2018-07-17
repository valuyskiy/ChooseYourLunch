package ru.valuyskiy.chooseyourlunch.to;

import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class MenuToWithDishes extends AbstractTo {
    private LocalDate date;
    private Restaurant restaurant;
    private List<Dish> dishes;

    public MenuToWithDishes() {

    }

    public MenuToWithDishes(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
