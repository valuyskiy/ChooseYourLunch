package ru.valuyskiy.chooseyoulunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.valuyskiy.chooseyourlunch.model.Role;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserServiceImpl;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyoulunch.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserServiceImpl service;

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        assertMatch(user, ADMIN);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void create() {
        User newUser = getCreated();
        User created = service.create(newUser);
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user@google.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void getAll() {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN, USER);
    }
}