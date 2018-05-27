package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = UserAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = AbstractAdminRestController.REST_URL + "/user";

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }
}
