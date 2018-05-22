package ru.valuyskiy.chooseyourlunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu", uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "menu_unique_restaurant_date_idx"))
public class Menu extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", columnDefinition = "date default now()")
    @NotNull
    private LocalDate date = LocalDate.now();

    public Menu() {
    }

    public Menu(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public Menu(Restaurant restaurant, LocalDate date) {
        this.restaurant = restaurant;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "restaurant ID = " + restaurant.getId()+
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
