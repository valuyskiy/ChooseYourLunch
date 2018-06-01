package ru.valuyskiy.chooseyourlunch;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.service.*;

import java.util.List;

public class SpringMain {

    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            UserServiceImpl userService = appCtx.getBean(UserServiceImpl.class);
            RestaurantServiceImpl restaurantService = appCtx.getBean(RestaurantServiceImpl.class);
            MenuServiceImpl menuService = appCtx.getBean(MenuServiceImpl.class);
            DishServiceImpl dishService = appCtx.getBean(DishServiceImpl.class);
            VotingServiceImpl votingService = appCtx.getBean(VotingServiceImpl.class);

//            User ADMIN = userService.get(100000);
//            User USER = userService.get(100001);
//
//            Restaurant RESR1 = restaurantService.create(new Restaurant("Обломов"));
//            Restaurant RESR2 = restaurantService.create(new Restaurant("The Сад"));
//
//            Menu MENU1_REST1 = menuService.create(new Menu(RESR1, LocalDate.of(2018,5,19)));
//            Dish DISH1_MENU1_REST1 = dishService.create(new Dish(MENU1_REST1, "Сладкие томаты с крымским луком", 69000));
//            Dish DISH2_MENU1_REST1 = dishService.create(new Dish(MENU1_REST1, "Устрица \"Дальневосточная\"", 290000));
//            Dish DISH3_MENU1_REST1 = dishService.create(new Dish(MENU1_REST1, "Мидии в сливочном соусе", 89000));
//            Dish DISH4_MENU1_REST1 = dishService.create(new Dish(MENU1_REST1, "Окрошка на квасе", 38000));
//
//            Menu MENU1_REST2 = menuService.create(new Menu(RESR2, LocalDate.of(2018,5,19)));
//            Dish DISH1_MENU1_REST2 = dishService.create(new Dish(MENU1_REST2, "Свекольный тартар с копченой грудкой цесарки", 48000));
//            Dish DISH2_MENU1_REST2 = dishService.create(new Dish(MENU1_REST2, "Салат с ягненком, запеченным перцем и подмаринованным баклажаном", 850000));
//            Dish DISH3_MENU1_REST2 = dishService.create(new Dish(MENU1_REST2, "Консоме из курицы с мини пельменями", 45000));
//            Dish DISH4_MENU1_REST2 = dishService.create(new Dish(MENU1_REST2, "Кальмары по-галисийски с запеченными перцами", 75000));
//            Dish DISH5_MENU1_REST2 = dishService.create(new Dish(MENU1_REST2, "Яблочный татин с мороженым из кленового сиропа", 38000));
//
//            Menu MENU2_REST1 = menuService.create(new Menu(RESR1, LocalDate.of(2018,5,20)));
//            Dish DISH1_MENU2_REST1 = dishService.create(new Dish(MENU2_REST1, "Тигровые креветка", 99000));
//            Dish DISH2_MENU2_REST1 = dishService.create(new Dish(MENU2_REST1, "Тартар из говядины", 138000));
//            Dish DISH3_MENU2_REST1 = dishService.create(new Dish(MENU2_REST1, "Мидии в томатном соусе", 89000));
//            Dish DISH4_MENU2_REST1 = dishService.create(new Dish(MENU2_REST1, "Щавеливый суп", 39000));
//
//            Menu MENU2_REST2 = menuService.create(new Menu(RESR2, LocalDate.of(2018,5,20)));
//            Dish DISH1_MENU2_REST2 = dishService.create(new Dish(MENU2_REST2, "Паштет из нерки с морскими водорослями и маслом из апельсина", 55000));
//            Dish DISH2_MENU2_REST2 = dishService.create(new Dish(MENU2_REST2, "Салат из моцареллы с ростбифом и маринованным перцем", 106000));
//            Dish DISH3_MENU2_REST2 = dishService.create(new Dish(MENU2_REST2, "Старомодный Картофельный суп", 55000));
//            Dish DISH4_MENU2_REST2 = dishService.create(new Dish(MENU2_REST2, "Наша котлета по-киевски со свекольным ризотто", 78000));
//            Dish DISH5_MENU2_REST2 = dishService.create(new Dish(MENU2_REST2, "Сырное пирожное с карамелью", 40000));
//
//            Menu MENU3_REST1 = menuService.create(new Menu(RESR1, LocalDate.of(2018,5,21)));
//            Dish DISH1_MENU3_REST1 = dishService.create(new Dish(MENU3_REST1, "Печень трески с тёплым картофелем", 67000));
//            Dish DISH2_MENU3_REST1 = dishService.create(new Dish(MENU3_REST1, "Рапан запечёный под сыром", 58000));
//            Dish DISH3_MENU3_REST1 = dishService.create(new Dish(MENU3_REST1, "Свежие овощи", 69000));
//            Dish DISH4_MENU3_REST1 = dishService.create(new Dish(MENU3_REST1, "Свекольник", 34000));
//
//            Menu MENU3_REST2 = menuService.create(new Menu(RESR2, LocalDate.of(2018,5,  21)));
//            Dish DISH1_MENU3_REST2 = dishService.create(new Dish(MENU3_REST2, "Тёплый салат с куриной печенью", 66000));
//            Dish DISH2_MENU3_REST2 = dishService.create(new Dish(MENU3_REST2, "Салат из моцареллы с ростбифом и маринованным перцем", 106000));
//            Dish DISH3_MENU3_REST2 = dishService.create(new Dish(MENU3_REST2, "Наш борщ с дикой копченой уткой", 55000));
//            Dish DISH4_MENU3_REST2 = dishService.create(new Dish(MENU3_REST2, "Куриный стейк с овощами и легким муссом из картофеля", 75000));
//            Dish DISH5_MENU3_REST2 = dishService.create(new Dish(MENU3_REST2, "Медовая панна котта с сорбетом из смородины", 36000));
//
//            Vote VOTE1_DAY1 = votingService.create(new Vote(ADMIN,MENU1_REST2));
//            Vote VOTE2_DAY1 = votingService.create(new Vote(USER,MENU1_REST2));
//
//            Vote VOTE1_DAY2 = votingService.create(new Vote(ADMIN,MENU2_REST2));
//            Vote VOTE2_DAY2 = votingService.create(new Vote(USER,MENU2_REST1));
//
//            Vote VOTE1_DAY3 = votingService.create(new Vote(ADMIN,MENU3_REST1));
//            Vote VOTE2_DAY3 = votingService.create(new Vote(USER,MENU3_REST1));

            List<Menu> menus = menuService.getAll();
            System.out.println(menus);

        }
    }
}
