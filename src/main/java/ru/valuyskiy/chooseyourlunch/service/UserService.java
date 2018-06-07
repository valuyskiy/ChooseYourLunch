package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.User;

public interface UserService extends AbstractCrudService<User> {

    User getByEmail(String email);

    User updateUserProfile(User user);

}
