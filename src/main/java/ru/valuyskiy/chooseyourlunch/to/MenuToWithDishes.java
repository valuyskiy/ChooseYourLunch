package ru.valuyskiy.chooseyourlunch.to;

import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class MenuToWithDishes extends AbstractTo {
    private LocalDate date;
    private Restaurant restaurant;
    private List<Dish> dishes;

    private int voteCounter;
    private boolean isVoting;

    private int totalPrice;

    public MenuToWithDishes(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes, int voteCounter, boolean isVoting, int totalPrice) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.voteCounter = voteCounter;
        this.isVoting = isVoting;
        this.totalPrice = totalPrice;
    }
}
