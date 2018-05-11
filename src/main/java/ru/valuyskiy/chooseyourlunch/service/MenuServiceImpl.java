package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.repository.MenuRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository repository;

    @Override
    public Menu create(Menu menu) {
        Assert.notNull(menu, "Menu must not be null");
        return repository.save(menu);
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.findAll();
    }

    public List<Menu> getByUser() {
        return repository.findAll();
    }

    @Override
    public void update(Menu menu) {

    }

    @Override
    public void delete(int id) throws NotFoundException {

    }
}
