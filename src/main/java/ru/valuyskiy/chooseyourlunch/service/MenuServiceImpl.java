package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.repository.MenuRepository;
import ru.valuyskiy.chooseyourlunch.repository.RestaurantRepository;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.Assert.notNull;
import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNew;
import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public Menu create(Menu menu) {
        notNull(menu, "Menu must not be null");
        checkNew(menu);
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
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

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public Menu update(Menu menu) {
        notNull(menu, "Menu must not be null");
        return checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }

    @CacheEvict(value = "menus", allEntries = true)
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
    @Cacheable(cacheNames = "menus", key = "#date")
    @Override
    public List<MenuToWithDishes> getToWithDishes(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return menuRepository.getWithDishesByDate(date).stream()
                .map(menu -> new MenuToWithDishes(
                        menu.getId(),
                        menu.getDate(),
                        menu.getRestaurant(),
                        menu.getDishes())
                )
                .collect(toList());
    }

    @CacheEvict(value = "menus", allEntries = true)
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
