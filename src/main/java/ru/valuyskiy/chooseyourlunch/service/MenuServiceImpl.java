package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.repository.MenuRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu create(Menu menu) {
        notNull(menu, "Menu must not be null");
        return menuRepository.save(menu);
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
    public List<Menu> getByRestaurantId(int restaurantId) {
        return menuRepository.getByRestaurant(restaurantId);
    }

    @Override
    public List<Menu> getByDate(LocalDate date) {
        return menuRepository.getByDate(date);
    }

    @Override
    public Menu getWithDishesByRestaurantAndDate(int restaurantId, LocalDate date) {
        return menuRepository.getWithDishesByRestaurantAndDate(restaurantId, date);
    }

    @Override
    public void update(Menu menu) {
        notNull(menu, "Menu must not be null");
        checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }
}
