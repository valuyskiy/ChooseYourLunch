package ru.valuyskiy.chooseyoulunch;

import ru.valuyskiy.chooseyourlunch.model.Role;
import ru.valuyskiy.chooseyourlunch.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.valuyskiy.chooseyourlunch.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "adminPassword", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER = new User(USER_ID, "User", "user@google.ru", "userPassword", Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }


}
