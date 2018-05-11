package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

public interface BaseCrudService<T> {

    T create(T t);

    T get(int id) throws NotFoundException;

    List<T> getAll();

    void update(T t);

    void delete(int id) throws NotFoundException;

}
