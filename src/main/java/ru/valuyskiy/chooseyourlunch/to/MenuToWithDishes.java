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

    public MenuToWithDishes(){

    }

    public MenuToWithDishes(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes, int voteCounter, boolean isVoting, int totalPrice) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.voteCounter = voteCounter;
        this.isVoting = isVoting;
        this.totalPrice = totalPrice;
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

    public int getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(int voteCounter) {
        this.voteCounter = voteCounter;
    }

    public boolean isVoting() {
        return isVoting;
    }

    public void setVoting(boolean voting) {
        isVoting = voting;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
