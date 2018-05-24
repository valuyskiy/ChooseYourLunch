package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.User;

public interface UserService extends BaseCrudService<User> {

    User getByEmail(String email);

}
