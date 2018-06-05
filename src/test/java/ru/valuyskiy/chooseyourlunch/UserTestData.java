package ru.valuyskiy.chooseyourlunch;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.valuyskiy.chooseyourlunch.model.Role;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.web.json.JsonUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.valuyskiy.chooseyourlunch.model.AbstractBaseEntity.START_SEQ;
import static ru.valuyskiy.chooseyourlunch.web.json.JsonUtil.writeIgnoreProps;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "adminPassword", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER = new User(USER_ID, "User", "user@google.ru", "userPassword", Role.ROLE_USER);

    public static User getCreated() {
        return new User(null, "Egor", "egor@gmail.com", "egorNewPass", Role.ROLE_USER);
    }

    public static User getUpdated() {
        return new User(USER.getId(), "Egor", "egor@google.com", USER.getPassword(), Role.ROLE_ADMIN, Role.ROLE_USER);
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "password");
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "password"));
    }

    public static String jsonWithPassword(User user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}
