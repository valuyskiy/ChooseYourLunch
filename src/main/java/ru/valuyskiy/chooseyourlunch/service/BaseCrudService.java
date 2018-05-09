package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

public interface BaseCrudService<T> {

    T create(T t);

    void delete(int id) throws NotFoundException;

    T get(int id) throws NotFoundException;

    void update(T t);

    List<T> getAll();

}
