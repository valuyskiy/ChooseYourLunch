package ru.valuyskiy.chooseyoulunch;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;
import ru.valuyskiy.chooseyourlunch.model.Role;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.MenuServiceImpl;
import ru.valuyskiy.chooseyourlunch.service.RestaurantServiceImpl;
import ru.valuyskiy.chooseyourlunch.service.UserServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpringMain {

    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()){
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserServiceImpl userService = appCtx.getBean(UserServiceImpl.class);

            userService.create(new User(null,"Vasya","e@mail.com", "password", Collections.singleton(Role.ROLE_USER)));
            userService.create(new User(null,"Vasya1","e1@mail.com", "password", Collections.singleton(Role.ROLE_ADMIN)));

            RestaurantServiceImpl restaurantService = appCtx.getBean(RestaurantServiceImpl.class);
            Restaurant restaurant1 = new Restaurant("Restaurant 1");
            restaurantService.create(restaurant1);
            System.out.println(restaurantService.getAll());

            MenuServiceImpl menuService = appCtx.getBean(MenuServiceImpl.class);
            menuService.create(new Menu(restaurant1));
            List<Menu> menus = menuService.getAll();
            System.out.println(menus);

        }
    }
}
