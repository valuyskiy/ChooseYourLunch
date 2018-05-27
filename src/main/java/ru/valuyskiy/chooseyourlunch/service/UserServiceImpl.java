package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.repository.UserRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFound;
import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements BaseCrudService<User>, UserService {

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private UserRepository repository;

    // TODO Configure cache
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "User must not be null");
        return repository.save(user);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "User must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public User getByEmail(String email){
        Assert.notNull(email, "E-mail must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);

    }
}
