package ru.valuyskiy.chooseyourlunch.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MenuTo extends AbstractTo {

    @JsonProperty(value = "restaurant_id")
    private int restaurantId;

    private LocalDate date;

    public MenuTo() {
    }

    public MenuTo(int restaurantId, LocalDate date) {
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public MenuTo(Integer id, int restaurantId, LocalDate date) {
        super(id);
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "restaurantId=" + restaurantId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
