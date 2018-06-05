package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = UsersAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = AbstractAdminRestController.REST_URL + "/users";

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAll() {

        List<User> users = userService.getAll();
        log.info("User id:{} get all Users", AuthorizedUser.id());
        return users;
    }

    @GetMapping("/{userId}")
    public User getTo(@PathVariable("userId") int userId) {

        User user = userService.get(userId);
        log.info("User id:{} get User id:{}", AuthorizedUser.id(), user.getId());
        return user;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@Valid @RequestBody User user) {

        User created = userService.create(user);
        log.info("User id:{} add new User id:{}", AuthorizedUser.id(), created.getId());
        return created;
    }

    @PutMapping(value = "/{userId}",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@Valid @RequestBody User user, @PathVariable("userId") int userId) {
        assureIdConsistent(user, userId);
        User updated = userService.update(user);
        log.info("User id:{} updated User id:{}", AuthorizedUser.id(), updated.getId());
        return updated;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") int userId) {

        userService.delete(userId);
        log.info("User id:{} deleted User id:{}", AuthorizedUser.id(), userId);
    }
}
