package ru.valuyskiy.chooseyourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.User;


@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

}