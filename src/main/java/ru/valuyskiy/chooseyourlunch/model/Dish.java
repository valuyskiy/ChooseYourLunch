package ru.valuyskiy.chooseyourlunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "name"}, name = "dishes_unique_menu_name_idx"))
public class Dish extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Menu menu;

    @Column(name = "price", nullable = false)
    @Range(min = 0, max = 10_000_00)
    private int price;

    public Dish() {
    }

    public Dish(Menu menu, String name, int price) {
        super(name);
        this.menu = menu;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "menu=" + menu +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
