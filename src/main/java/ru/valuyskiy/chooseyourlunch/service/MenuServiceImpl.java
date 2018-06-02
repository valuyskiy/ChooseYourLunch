package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.repository.MenuRepository;
import ru.valuyskiy.chooseyourlunch.repository.RestaurantRepository;
import ru.valuyskiy.chooseyourlunch.repository.VoteRepository;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.Assert.notNull;
import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Menu create(Menu menu) {
        notNull(menu, "Menu must not be null");
        return menuRepository.save(menu);
    }

    @Override
    public Menu createByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return create(new Menu(restaurantRepository.getOne(restaurantId), date));
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return checkNotFoundWithId(menuRepository.findById(id).orElse(null), id);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu update(Menu menu) {
        notNull(menu, "Menu must not be null");
        return checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }


    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }


    @Override
    public List<MenuTo> getToByRestaurantId(int restaurantId) {
        return menuRepository.getByRestaurant(restaurantId).stream()
                .map(this::toTo)
                .collect(toList());
    }

    @Override
    public MenuTo toTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getRestaurant().getId(), menu.getDate());
    }

    @Transactional
    @Override
    public List<MenuToWithDishes> getToWithDishes(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return menuRepository.getWithDishesByDate(date).stream()
                .map(this::getToWithDishes)
                .collect(toList());
    }

    @Transactional
    @Override
    public MenuToWithDishes getToWithDishes(Menu menu) {

        int totalPrice = menu.getDishes().stream()
                .mapToInt(Dish::getPrice)
                .sum();

        int voteCounter = voteRepository.countByMenu_Id(menu.getId());

        boolean isVoting = voteRepository.countByUser_IdAndMenu_Id(AuthorizedUser.id(), menu.getId()) > 0;

        return new MenuToWithDishes(
                menu.getId(),
                menu.getDate(),
                menu.getRestaurant(),
                menu.getDishes(),
                voteCounter,
                isVoting,
                totalPrice);
    }

    @Transactional
    @Override
    public Menu update(MenuTo menuTo) {
        notNull(menuTo, "Menu must not be null");
        Menu menu = checkNotFoundWithId(menuRepository.findById(menuTo.getId()).orElse(null), menuTo.getId());
        menu.setDate(menuTo.getDate());
        menu.setRestaurant(restaurantRepository.getOne(menuTo.getRestaurantId()));
        return menuRepository.save(menu);
    }

}
