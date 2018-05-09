package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

public interface UserService {

    User getByEmail(String email) throws NotFoundException;

}
