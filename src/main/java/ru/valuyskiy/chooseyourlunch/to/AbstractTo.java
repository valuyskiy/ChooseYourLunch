package ru.valuyskiy.chooseyourlunch.to;

import ru.valuyskiy.chooseyourlunch.HasId;

public class AbstractTo implements HasId{
    protected Integer id;

    AbstractTo() {
    }

    AbstractTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}