package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.repository.UserRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.*;

@Service("userService")
public class UserServiceImpl implements AbstractCrudService<User>, UserService, UserDetailsService {

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "User must not be null");
        checkNew(user);
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "User must not be null");
        return checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @Override
    public User updateUserProfile(User user) {
        Assert.notNull(user, "User must not be null");

        User oldUser = get(user.getId());
        user.setRoles(oldUser.getRoles());      // block change roles

        return checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    private User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}