package ru.valuyskiy.chooseyourlunch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "name"}, name = "dishes_unique_menu_name_idx"))
public class Dish extends AbstractNamedEntity {

    // TODO проверить JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public Dish(Integer id, String name, Menu menu, int price) {
        super(id, name);
        this.menu = menu;
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
