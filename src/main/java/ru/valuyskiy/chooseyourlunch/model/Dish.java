package ru.valuyskiy.chooseyourlunch.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Dish extends AbstractNamedEntity{
//    private Menu menu;
    private LocalDate date;
    private int price;
}
