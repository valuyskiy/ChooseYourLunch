package ru.valuyskiy.chooseyourlunch.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "restaurants", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "restaurant_unique_name_idx"))
public class Restaurant extends AbstractNamedEntity{

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(String name) {
        super(null, name);
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
