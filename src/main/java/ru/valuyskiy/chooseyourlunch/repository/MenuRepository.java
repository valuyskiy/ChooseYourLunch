package ru.valuyskiy.chooseyourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
