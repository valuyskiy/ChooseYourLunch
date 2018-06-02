package ru.valuyskiy.chooseyourlunch.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DishTo extends AbstractTo {

    private String name;

    private int price;

    @JsonProperty("menu_id")
    private int menuId;

    public DishTo() {
    }

    public DishTo(String name, int price, int menuId) {
        this.name = name;
        this.price = price;
        this.menuId = menuId;
    }

    public DishTo(Integer id, String name, int price, int menuId) {
        super(id);
        this.name = name;
        this.price = price;
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", menuId=" + menuId +
                ", id=" + id +
                '}';
    }
}
